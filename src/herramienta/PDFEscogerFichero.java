package herramienta;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;

import constante.Messages;
public class PDFEscogerFichero extends JFileChooser{
	
	public PDFEscogerFichero() {}
	
	public void guardarDocumento(PDDocument documento) 
	{
		try {
			File documentoGuardado = new File(obtenerDestinacion().getAbsolutePath() +
					Messages.getString("BARRA") + documento.getDocumentInformation().getTitle()); //$NON-NLS-1$
			documento.save(documentoGuardado);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private File obtenerDestinacion() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
	IllegalAccessException {
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.setDialogTitle(Messages.getString("PDFEscogerFichero.1")); //$NON-NLS-1$
		Field f =  this.getUI().getClass().getDeclaredField(Messages.getString("PDFEscogerFichero.2")); //$NON-NLS-1$
		f.setAccessible(true);
	    JTextField jtf = (JTextField)f.get(this.getUI());
        jtf.setEditable(false);		
		int returnVal = this.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return this.getSelectedFile();
		}
		return null;
	}
	
	public File obtenerOrigen() {
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.setDialogTitle(Messages.getString("PDFEscogerFichero.3")); //$NON-NLS-1$
		this.setAcceptAllFileFilterUsed(false);
		this.addChoosableFileFilter(new FileNameExtensionFilter(Messages.getString("PDFEscogerFichero.4"), Messages.getString("PDFEscogerFichero.5"))); //$NON-NLS-1$ //$NON-NLS-2$
		int returnVal = this.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return this.getSelectedFile();
		}
		return null;
	}
}
