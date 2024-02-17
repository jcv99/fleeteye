package panel.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import constante.Messages;
public class FinalizarEncargoPanel extends JPanel {
	private JButton btnFinalizar;
	private JComboBox comboBoxEncargosCurso;
	private JTextArea textArea;

	
	public FinalizarEncargoPanel() {
		setLayout(null);
		
		JLabel lblidEncargos = new JLabel(Messages.getString("FinalizarEncargoPanel.0")); //$NON-NLS-1$
		lblidEncargos.setBounds(72, 64, 114, 15);
		add(lblidEncargos);
		
		btnFinalizar = new JButton(Messages.getString("FinalizarEncargoPanel.1")); //$NON-NLS-1$
		btnFinalizar.setEnabled(false);
		btnFinalizar.setBounds(45, 282, 172, 25);
		add(btnFinalizar);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(269, 25, 216, 305);
		add(textArea);
		
		comboBoxEncargosCurso = new JComboBox<String>();
		comboBoxEncargosCurso.setBounds(54, 114, 150, 24);
		add(comboBoxEncargosCurso);

	}
	public JButton getBtnFinalizar() {
		return btnFinalizar;
	}
	public JComboBox getComboBoxEncargosCurso() {
		return comboBoxEncargosCurso;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
}
