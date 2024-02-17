package panel.vista.mostrar;

import javax.swing.JDialog;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class MostrarGraficaDialogo extends JDialog {

	private ChartPanel cp;
	private JFreeChart grafica;

	public MostrarGraficaDialogo() {
		setModal(true);
		setResizable(false);
	}

	public ChartPanel getCp() {
		return cp;
	}

	public JFreeChart getGrafica() {
		return grafica;
	}

	public void setCp(ChartPanel cp) {
		this.cp = cp;
	}

	public void setGrafica(JFreeChart grafica) {
		this.grafica = grafica;
	}
}
