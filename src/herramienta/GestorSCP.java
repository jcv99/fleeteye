package herramienta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import constante.ConstantesExcepciones;
import constante.Messages;
import exception.ErrorConexionServidorException;
public class GestorSCP implements AutoCloseable {

	private Channel canal;
	private FileInputStream fis;
	private FileOutputStream fos;
	private InputStream in;
	private JSch jsch;
	private OutputStream out;
	private Session sesion;

	public GestorSCP() {
		this.jsch = new JSch();
		this.sesion = null;
		this.canal = null;
		this.out = null;
		this.in = null;
		this.fis = null;
		this.fos = null;
	}

	public File bajarFichero(String pathFicheroRemoto)
			throws JSchException, IOException, ErrorConexionServidorException {
		conectar();

		String comando = Messages.getString("GestorSCP.0") + pathFicheroRemoto; //$NON-NLS-1$
		this.canal = this.sesion.openChannel(Messages.getString("GestorSCP.1")); //$NON-NLS-1$
		((ChannelExec) this.canal).setCommand(comando);

		this.out = this.canal.getOutputStream();
		this.in = this.canal.getInputStream();

		this.canal.connect();

		byte[] buf = new byte[1024];

		buf[0] = 0;
		out.write(buf, 0, 1);
		out.flush();

		while (true) {
			if (checkConexion(this.in) != 'C')
				throw new ErrorConexionServidorException(ConstantesExcepciones.SCP_ERROR_CONEXION_INTERRUMPIDA);

			in.read(buf, 0, 5);

			long tamanoFichero = 0L;

			while (true) {
				if (in.read(buf, 0, 1) < 0)
					break;
				if (buf[0] == ' ')
					break;
				tamanoFichero = tamanoFichero * 10L + buf[0] - '0';
			}

			String file = null;
			for (int i = 0;; i++) {
				in.read(buf, i, 1);
				if (buf[i] == (byte) 0x0a) {
					file = new String(buf, 0, i);
					break;
				}
			}

			buf[0] = 0;
			out.write(buf, 0, 1);
			out.flush();

			File ficherolocal = new File(new File(pathFicheroRemoto).getName());

			fos = new FileOutputStream(ficherolocal);
			int foo;
			while (true) {
				if (buf.length < tamanoFichero)
					foo = buf.length;
				else
					foo = (int) tamanoFichero;

				foo = in.read(buf, 0, foo);
				if (foo < 0)
					break;
				fos.write(buf, 0, foo);
				tamanoFichero -= foo;
				if (tamanoFichero == 0L)
					break;
			}
			fos.close();
			fos = null;

			if (checkConexion(this.in) != 0)
				throw new ErrorConexionServidorException(ConstantesExcepciones.SCP_ERROR_CONEXION_INTERRUMPIDA);

			buf[0] = 0;
			out.write(buf, 0, 1);
			out.flush();
			
			return ficherolocal;
		}
	}

	private int checkConexion(InputStream in) throws IOException {
		int b = in.read();

		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
		}
		return b;
	}

	@Override
	public void close() throws Exception {
		if (this.sesion != null)
			this.sesion.disconnect();
		if (this.canal != null)
			this.canal.disconnect();
		if (this.out != null)
			this.out.close();
		if (this.in != null)
			this.out.close();
		if (this.fis != null)
			this.fis.close();
		if (this.fos != null)
			this.fos.close();
	}

	private void conectar() throws JSchException {

		this.sesion = jsch.getSession(ConfiguracionServidor.USUARIO, ConfiguracionServidor.HOST, ConfiguracionServidor.PUERTO);
		this.sesion.setPassword(ConfiguracionServidor.PASSWORD);
		Properties config = new Properties();
		config.put(Messages.getString("GestorSCP.2"), Messages.getString("GestorSCP.3")); //$NON-NLS-1$ //$NON-NLS-2$
		this.sesion.setConfig(config);
		this.sesion.connect();
	}

	public void subirFichero(String ficheroLocal, String pathRemoto)
			throws JSchException, IOException, ErrorConexionServidorException {
		conectar();

		String comando = Messages.getString("GestorSCP.4") + pathRemoto; //$NON-NLS-1$

		this.canal = this.sesion.openChannel(Messages.getString("GestorSCP.5")); //$NON-NLS-1$
		((ChannelExec) this.canal).setCommand(comando);

		this.out = this.canal.getOutputStream();
		this.in = this.canal.getInputStream();

		this.canal.connect();

		if (checkConexion(this.in) != 0)
			throw new ErrorConexionServidorException(ConstantesExcepciones.SCP_ERROR_CONEXION_INTERRUMPIDA);

		File lfichero = new File(ficheroLocal);

		long filesize = lfichero.length();

		String comando2 = Messages.getString("GestorSCP.6") + filesize + Messages.getString("BARRAINVERTIDA"); //$NON-NLS-1$ //$NON-NLS-2$

		if (ficheroLocal.lastIndexOf('/') > 0)
			comando2 += ficheroLocal.substring(ficheroLocal.lastIndexOf('/') + 1);
		else
			comando2 += ficheroLocal;

		comando2 += Messages.getString("GestorSCP.8"); //$NON-NLS-1$

		this.out.write(comando2.getBytes());
		this.out.flush();

		if (checkConexion(this.in) != 0)
			throw new ErrorConexionServidorException(ConstantesExcepciones.SCP_ERROR_CONEXION_INTERRUMPIDA);

		this.fis = new FileInputStream(ficheroLocal);
		byte[] buf = new byte[1024];
		while (true) {
			int len = this.fis.read(buf, 0, buf.length);
			if (len <= 0)
				break;
			this.out.write(buf, 0, len);
		}

		this.fis.close();
		this.fis = null;

		buf[0] = 0;
		this.out.write(buf, 0, 1);
		this.out.flush();

		if (checkConexion(this.in) != 0)
			throw new ErrorConexionServidorException(ConstantesExcepciones.SCP_ERROR_CONEXION_INTERRUMPIDA);

		this.out.close();
		this.out = null;

		this.canal.disconnect();
		this.sesion.disconnect();
	}
}