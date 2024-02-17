package panel.vista.guardar;

import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
import herramienta.DateLabelFormatter;
public class GuardarEncargoDialogo extends JDialog {

	private JButton btnGuardar;

	private JComboBox<Object> buscaCamion;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JDatePickerImpl entrega;
	private JPanel fechaentrega;

	public GuardarEncargoDialogo() {
		setResizable(false);
		setModal(true);
		setBounds(50, 50, 352, 332);
		getContentPane().setLayout(null);
		setTitle(Messages.getString("GuardarEncargoDialogo.0")); //$NON-NLS-1$

		JLabel lblAsignarCamion = new JLabel(Messages.getString("GuardarEncargoDialogo.1")); //$NON-NLS-1$
		lblAsignarCamion.setBounds(23, 45, 96, 31);
		getContentPane().add(lblAsignarCamion);

		buscaCamion = new JComboBox<Object>();
		buscaCamion.setBounds(23, 80, 300, 27);
		getContentPane().add(buscaCamion);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put(Messages.getString("GuardarEncargoDialogo.2"), Messages.getString("GuardarEncargoDialogo.3")); //$NON-NLS-1$ //$NON-NLS-2$
		p.put(Messages.getString("GuardarEncargoDialogo.4"), Messages.getString("GuardarEncargoDialogo.5")); //$NON-NLS-1$ //$NON-NLS-2$
		p.put(Messages.getString("GuardarEncargoDialogo.6"), Messages.getString("GuardarEncargoDialogo.7")); //$NON-NLS-1$ //$NON-NLS-2$
		JDatePanelImpl datePanelEntrega = new JDatePanelImpl(model, p);

		fechaentrega = new JPanel();
		fechaentrega.setBounds(23, 161, 300, 35);
		getContentPane().add(fechaentrega);

		entrega = new JDatePickerImpl(datePanelEntrega, new DateLabelFormatter());
		entrega.setBounds(0, 59, 66, -59);
		fechaentrega.add(entrega);

		btnGuardar = new JButton(Messages.getString("GuardarEncargoDialogo.8")); //$NON-NLS-1$
		btnGuardar.setBounds(119, 235, 91, 31);
		getContentPane().add(btnGuardar);

		JLabel lblFechaDeEntrega = new JLabel(Messages.getString("GuardarEncargoDialogo.9")); //$NON-NLS-1$
		lblFechaDeEntrega.setBounds(23, 134, 115, 15);
		getContentPane().add(lblFechaDeEntrega);

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JComboBox<Object> getBuscaCamion() {
		return buscaCamion;
	}

	public JDatePickerImpl getEntrega() {
		return entrega;
	}

	public JPanel getFechaentrega() {
		return fechaentrega;
	}
}
