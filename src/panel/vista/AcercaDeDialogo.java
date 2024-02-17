package panel.vista;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import constante.ConstantesStrings;
import constante.Messages;

public class AcercaDeDialogo extends JDialog {

	private JButton btnCredit;
	private JButton btnExit;
	private JButton btnLicencia;
	private CardLayout casJorcl;
	private final JPanel contentPanel = new JPanel();
	private JPanel midpanel;

	public AcercaDeDialogo() {
		setTitle(Messages.getString("AcercaDeDialogo.0")); //$NON-NLS-1$
		setModal(true);
		setResizable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setBounds(100, 100, 450, 300);
		setContentPane(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		JPanel toppanel = new JPanel();
		contentPanel.add(toppanel);
		toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_2 = new JLabel(Messages.getString("VACIO=")); //$NON-NLS-1$
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		toppanel.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel(Messages.getString("AcercaDeDialogo.2")); //$NON-NLS-1$
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		toppanel.add(lblNewLabel_1);

		midpanel = new JPanel();
		contentPanel.add(midpanel);
		midpanel.setName(ConstantesStrings.NOMBRESCREADORES);

		contentPanel.add(midpanel);
		casJorcl = new CardLayout(0, 0);
		midpanel.setLayout(casJorcl);

		JPanel first = new JPanel();
		midpanel.add(first, Messages.getString("AcercaDeDialogo.3")); //$NON-NLS-1$
		first.setLayout(new BoxLayout(first, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel(ConstantesStrings.FRASEDESCRIPTIVA);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		first.add(lblNewLabel);

		Component verticalGlue = Box.createVerticalGlue();
		first.add(verticalGlue);

		JLabel lblNewLabel_3 = new JLabel(ConstantesStrings.ANYONOMBRE);
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_3.setBounds(new Rectangle(10, 10, 10, 10));
		first.add(lblNewLabel_3);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		first.add(rigidArea);

		JScrollPane scrolllicense = new JScrollPane();
		midpanel.add(scrolllicense, Messages.getString("AcercaDeDialogo.4")); //$NON-NLS-1$

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		scrolllicense.setViewportView(editorPane);

		JEditorPane editorCredit = new JEditorPane();
		midpanel.add(editorCredit, Messages.getString("AcercaDeDialogo.5")); //$NON-NLS-1$

		URL UrlCredit = AcercaDeDialogo.class.getResource(Messages.getString("AcercaDeDialogo.6")); //$NON-NLS-1$
		if (UrlCredit != null) {
			try {
				editorCredit.setPage(UrlCredit);
			} catch (IOException e) {
				System.err.println(Messages.getString("AcercaDeDialogo.7") + UrlCredit); //$NON-NLS-1$
			}
		} else {
			System.err.println(Messages.getString("AcercaDeDialogo.8")); //$NON-NLS-1$
		}

		URL UrlLicen = AcercaDeDialogo.class.getResource(Messages.getString("AcercaDeDialogo.9")); //$NON-NLS-1$
		if (UrlLicen != null) {
			try {
				editorPane.setPage(UrlLicen);
			} catch (IOException e) {
				System.err.println(Messages.getString("AcercaDeDialogo.10") + UrlLicen); //$NON-NLS-1$
			}
		} else {
			System.err.println(Messages.getString("AcercaDeDialogo.11")); //$NON-NLS-1$
		}

		JPanel botpanel = new JPanel();
		contentPanel.add(botpanel);
		botpanel.setLayout(new BoxLayout(botpanel, BoxLayout.X_AXIS));

		btnLicencia = new JButton(Messages.getString("AcercaDeDialogo.12")); //$NON-NLS-1$

		botpanel.add(btnLicencia);

		btnCredit = new JButton(Messages.getString("AcercaDeDialogo.13")); //$NON-NLS-1$

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		botpanel.add(rigidArea_1);
		botpanel.add(btnCredit);

		Component glue = Box.createGlue();
		botpanel.add(glue);

		btnExit = new JButton(Messages.getString("AcercaDeDialogo.14")); //$NON-NLS-1$
		botpanel.add(btnExit);

	}

	public JButton getBtnCredito() {
		return btnCredit;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnLicencia() {
		return btnLicencia;
	}

	public CardLayout getCL() {
		return casJorcl;
	}

	public JPanel getMidPanel() {
		return midpanel;
	}
}
