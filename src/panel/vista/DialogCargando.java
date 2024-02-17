package panel.vista;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constante.Messages;

public class DialogCargando extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public DialogCargando() {
		setBounds(100, 100, 499, 285);
		getContentPane().setLayout(null);
		
		JLabel lblCargandoDatos = new JLabel("Cargando datos");
		lblCargandoDatos.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCargandoDatos.setBounds(167, 83, 150, 41);
		getContentPane().add(lblCargandoDatos);
		
		JLabel labelCargando = new JLabel();
		labelCargando.setIcon(
				new ImageIcon(this.getClass().getResource(Messages.getString("LoginFrameControl.0")))); //$NON-NLS-1$
		labelCargando.setBounds(126, 136, 228, 41);
		getContentPane().add(labelCargando);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}
