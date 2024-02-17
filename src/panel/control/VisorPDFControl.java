package panel.control;

import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import exception.DialogoError;
import herramienta.ImprimirManager;
import herramienta.PDFEscogerFichero;
import panel.vista.VisorPDF;

public class VisorPDFControl implements DocumentListener {

	private BufferedImage bim;
	private PDDocument documento;
	private PDFRenderer documentoRender;
	private int pagina;
	private VisorPDF vista;

	public VisorPDFControl(VisorPDF vista, File fpdf) throws IOException {
		this.vista = vista;
		this.vista.setTitle(fpdf.getName());

		this.documento = PDDocument.load(fpdf);
		this.documento.getDocumentInformation().setTitle(fpdf.getName());
		this.documentoRender = new PDFRenderer(this.documento);
		this.pagina = 0;

		this.vista.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				fpdf.delete();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		this.vista.getBttnSiguiente().addActionListener(e -> siguiente());
		this.vista.getBttnAnterior().addActionListener(e -> anterior());
		this.vista.getPanelPagina().addMouseWheelListener(e -> enRotarRaton(e));
		this.vista.getTextFieldPaginaActual().getDocument().addDocumentListener(this);
		this.vista.getBtnGuardar().addActionListener(e -> guardarDocumento());
		this.vista.getBtnImprimir().addActionListener(e -> imprimir());

		this.vista.getLblnumeroPaginas().setText("de " + this.documento.getNumberOfPages());
		this.vista.getTextFieldPaginaActual().setText(this.pagina + 1 + "");
	}

	public void anterior() {
		if (this.pagina != 0) {
			this.pagina--;
			this.vista.getTextFieldPaginaActual().setText(this.pagina + 1 + "");
		}
	}

	@Override
	public void changedUpdate(DocumentEvent de) {
	}

	public void enRotarRaton(MouseWheelEvent e) {
		int direccion = e.getWheelRotation();
		if (direccion < 0)
			anterior();
		else
			siguiente();
	}

	private void guardarDocumento() {
		PDFEscogerFichero c = new PDFEscogerFichero();
		c.guardarDocumento(documento);
	}

	private void imprimir() {
		try {
			ImprimirManager.imprimirDocumentoConOpciones(documento);
		} catch (PrinterException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent de) {
		try {
			int pagina = Integer.parseInt(this.vista.getTextFieldPaginaActual().getText());
			if (pagina <= this.documento.getNumberOfPages() && pagina >= 1)
				irPagina(pagina - 1);
		} catch (IOException e) {
			new DialogoError(e).showErrorMessage();
		} catch (NumberFormatException e1) {
		}
	}

	public void irPagina(int pagina) throws NumberFormatException, IOException {
		this.bim = documentoRender.renderImage(pagina);
		this.vista.getLblPage().setIcon(new ImageIcon(bim));
	}

	@Override
	public void removeUpdate(DocumentEvent de) {
	}

	public void siguiente() {
		if (this.pagina + 1 != this.documento.getNumberOfPages()) {
			this.pagina++;
			this.vista.getTextFieldPaginaActual().setText(this.pagina + 1 + "");
		}
	}
}