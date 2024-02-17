package panel.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import constante.Messages;
public class VisorPDF extends JDialog {

	private JButton btnGuardar;
	private JButton btnImprimir;
	private JButton bttnAnterior;
	private JButton bttnSiguiente_1;
	private JLabel lblnumeroPaginas;
	private JLabel lblPage;
	private final JPanel panelPagina = new JPanel();
	private JTextField textFieldPaginaActual;

	public VisorPDF() {
		setResizable(false);
		setMinimumSize(new Dimension(0, 800));
		setPreferredSize(new Dimension(0, 800));
		setLocation(new Point(100, 0));
		setAlwaysOnTop(true);
		setBounds(100, 100, 764, 950);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		panelPagina.setBackground(SystemColor.menu);
		panelPagina.setLayout(new FlowLayout());
		panelPagina.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPagina, BorderLayout.CENTER);
		{
			lblPage = new JLabel(Messages.getString("VACIO")); //$NON-NLS-1$
			panelPagina.add(lblPage);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
			buttonPane.setPreferredSize(new Dimension(10, 60));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(260, 32767));
				buttonPane.add(horizontalStrut);
			}
			{
				bttnAnterior = new JButton(Messages.getString("VisorPDF.1")); //$NON-NLS-1$
				bttnAnterior.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				bttnAnterior.setActionCommand(Messages.getString("VisorPDF.2")); //$NON-NLS-1$
				buttonPane.add(bttnAnterior);
				getRootPane().setDefaultButton(bttnAnterior);
			}
			{
				Component bttnSiguiente = Box.createHorizontalStrut(20);
				buttonPane.add(bttnSiguiente);
			}
			{
				textFieldPaginaActual = new JTextField();
				textFieldPaginaActual.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				textFieldPaginaActual.setHorizontalAlignment(SwingConstants.CENTER);
				textFieldPaginaActual.setFont(new Font(Messages.getString("VisorPDF.3"), Font.PLAIN, 14)); //$NON-NLS-1$
				textFieldPaginaActual.setMaximumSize(new Dimension(30, 30));
				buttonPane.add(textFieldPaginaActual);
				textFieldPaginaActual.setColumns(10);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(10, 32767));
				horizontalStrut.setMinimumSize(new Dimension(10, 0));
				buttonPane.add(horizontalStrut);
			}
			{
				lblnumeroPaginas = new JLabel(Messages.getString("VisorPDF.4")); //$NON-NLS-1$
				lblnumeroPaginas.setFont(new Font(Messages.getString("VisorPDF.5"), Font.PLAIN, 16)); //$NON-NLS-1$
				buttonPane.add(lblnumeroPaginas);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				buttonPane.add(horizontalStrut);
			}
			{
				bttnSiguiente_1 = new JButton(Messages.getString("VisorPDF.6")); //$NON-NLS-1$
				bttnSiguiente_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				bttnSiguiente_1.setActionCommand(Messages.getString("VisorPDF.7")); //$NON-NLS-1$
				buttonPane.add(bttnSiguiente_1);
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JToolBar toolBar = new JToolBar();
				toolBar.setFloatable(false);
				menuBar.add(toolBar);
				{
					btnGuardar = new JButton(Messages.getString("VisorPDF.8")); //$NON-NLS-1$
					btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					toolBar.add(btnGuardar);
				}
				{
					btnImprimir = new JButton(Messages.getString("VisorPDF.9")); //$NON-NLS-1$
					btnImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					toolBar.add(btnImprimir);
				}
			}
		}
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBttnAnterior() {
		return bttnAnterior;
	}

	public JButton getBttnSiguiente() {
		return bttnSiguiente_1;
	}

	public JLabel getLblnumeroPaginas() {
		return lblnumeroPaginas;
	}

	public JLabel getLblPage() {
		return lblPage;
	}

	public JPanel getPanelPagina() {
		return panelPagina;
	}

	public JTextField getTextFieldPaginaActual() {
		return textFieldPaginaActual;
	}
}
