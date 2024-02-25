package panel.control.mostrar;

import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import constante.Messages;
import exception.DialogoError;
import panel.vista.mostrar.MostrarGraficaDialogo;

public class MostrarGraficaDialogoControl {
	private String abscisa;
	private Double[][] array;
	private ChartPanel cp;
	private XYDataset datosLinea;
	private String linea;
	private String nom;
	private String ordenada;
	private MostrarGraficaDialogo vista;

	public MostrarGraficaDialogoControl(Double[][] array, String linea, String nom, String abscisa, String ordenada) {
		try {
			if (linea != null)
				this.linea = linea;
			else
				this.linea = Messages.getString("VACIO"); //$NON-NLS-1$

			this.array = array;
//			XYSeries datos = new XYSeries(this.linea);
			this.datosLinea = new XYSeriesCollection(addDatos(this.array));

			if (nom != null)
				this.nom = nom;
			else
				this.nom = Messages.getString("VACIO"); //$NON-NLS-1$

			if (abscisa != null)
				this.abscisa = abscisa;
			else
				this.abscisa = Messages.getString("VACIO"); //$NON-NLS-1$

			if (ordenada != null)
				this.ordenada = ordenada;
			else
				this.ordenada = Messages.getString("VACIO"); //$NON-NLS-1$

			this.vista = new MostrarGraficaDialogo();
			cp = this.vista.getCp();
			cp = new ChartPanel(ChartFactory.createXYLineChart(this.nom, this.abscisa, this.ordenada, this.datosLinea,
					PlotOrientation.VERTICAL, true, true, true));

			cp.setPreferredSize(new Dimension(1500, 800));
			cp.setRequestFocusEnabled(false);
			cp.setEnabled(false);
			cp.setMouseWheelEnabled(false);
			this.vista.setCp(cp);
			this.vista.add(cp);
			this.vista.pack();

		} catch (Exception e) {
			new DialogoError(new Exception(Messages.getString("MostrarGraficaDialogoControl.4"))).showErrorMessage(); //$NON-NLS-1$
		}

	}

	private XYSeries addDatos(Double[][] array) {
		XYSeries datos = new XYSeries(this.linea);
		for (int i = 0; i < array.length; i++) {
			datos.add(array[i][0], array[i][1]);
		}
		return datos;

	}

	public void setRangedAxis(String[] axis, String nom) {
		SymbolAxis rangeAxis = new SymbolAxis(nom, axis);

		XYPlot plot = (XYPlot) vista.getGrafica().getPlot();
		plot.setForegroundAlpha(0.5f);
		rangeAxis.setRange(0, axis.length);
		plot.setRangeAxis(rangeAxis);
	}

	public void setVisible(boolean bool) {
		this.vista.setVisible(bool);
	}

}