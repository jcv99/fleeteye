package panel.vista;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;

import constante.Messages;
import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Camion;
import objeto.Cliente;
import objeto.Encargo;
import objeto.Presupuesto;
import objeto.Remolque;
import objeto.Trabajador;
import panel.control.DetallesCamionPanelControl;
import panel.control.DetallesClientePanelControl;
import panel.control.DetallesEncargoPanelControl;
import panel.control.DetallesPresupuestoPanelControl;
import panel.control.DetallesRemolquePanelControl;
import panel.control.DetallesTrabajadorPanelControl;
public class DialogoDetalles extends JDialog {

	private ArrayList<Camion> camiones = null;
	private EnActualizadaBBDD enActualizadaBBDD;

	private ArrayList<Encargo> encargosClientes;
	private EnPeticionBBDD enPeticionBBDD;
	private ArrayList<Presupuesto> presupuestosClientes;
	private ArrayList<Remolque> remolques = null;
	private ArrayList<Trabajador> trabajadores = null;

	public DialogoDetalles(EnPeticionBBDD enPeticionBBDD, EnActualizadaBBDD enActualizadaBBDD) {
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;
		setName(Messages.getString("DialogoDetalles.0")); //$NON-NLS-1$
		dialogInit();
		setBounds(100, 100, 712, 618);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		setResizable(false);

	}

	public void abrirDetalles(Object objeto) {
		if (objeto instanceof Trabajador) {
			setTitle(Messages.getString("DialogoDetalles.1") + ((Trabajador) objeto).getIdentidad().getNif()); //$NON-NLS-1$
			setBounds(new Rectangle(0, 0, 675, 550));
			getContentPane()
					.add(new DetallesTrabajadorPanelControl((Trabajador) objeto, enPeticionBBDD, enActualizadaBBDD)
							.getDetallesTrabajadorPanel());
		} else if (objeto instanceof Cliente) {
			Cliente cliente = (Cliente) objeto;
			setTitle(Messages.getString("DialogoDetalles.2") + cliente.getIdentidad().getRazonSocial()); //$NON-NLS-1$
			try {
				this.enPeticionBBDD.buscarEncargosPorCliente(cliente);
				this.enPeticionBBDD.buscarPresupuestosPorCliente(cliente);
			} catch (SQLException | DatoNoValidoException | NIFNoValidoException | TrabajadorOcupadoException
					| RemolqueYaAsignadoException | CamionOcupadoException | VehiculoOcupadoExcepcion
					| RemolqueNoCompatibleException | TrabajadorNoAsignadoException | MatriculaNoValidaException e) {
				new DialogoError(e).showErrorMessage();
			}
			setBounds(new Rectangle(0, 0, 750, 700));
			getContentPane().add(new DetallesClientePanelControl(cliente, this.enPeticionBBDD, presupuestosClientes,
					encargosClientes).getDetallesClientePanel());
		} else if (objeto instanceof Presupuesto) {
			setTitle(Messages.getString("DialogoDetalles.3") + ((Presupuesto) objeto).getId()); //$NON-NLS-1$
			setBounds(new Rectangle(0, 0, 760, 850));

			getContentPane().add(new DetallesPresupuestoPanelControl(((Presupuesto) objeto), this.enPeticionBBDD,
					this.enActualizadaBBDD).getDetallesPresupuestoPanel());
		} else if (objeto instanceof Encargo) {
			setTitle(Messages.getString("DialogoDetalles.4") + ((Encargo) objeto).getId()); //$NON-NLS-1$
			setBounds(new Rectangle(0, 0, 650, 760));
			getContentPane().add(
					new DetallesEncargoPanelControl(((Encargo) objeto), this.enPeticionBBDD, this.enActualizadaBBDD)
							.getDetallesEncargoPanel());
		} else if (objeto instanceof Camion) {
			setTitle(Messages.getString("DialogoDetalles.5") + ((Camion) objeto).getMatricula()); //$NON-NLS-1$
			setBounds(new Rectangle(0, 0, 600, 575));
			getContentPane().add(new DetallesCamionPanelControl(((Camion) objeto), this.remolques, this.trabajadores,
					enPeticionBBDD, enActualizadaBBDD).getDetallesCamionPanel());
		} else if (objeto instanceof Remolque) {
			setTitle(Messages.getString("DialogoDetalles.6") + ((Remolque) objeto).getMatricula()); //$NON-NLS-1$
			setBounds(new Rectangle(0, 0, 675, 430));
			getContentPane().add(new DetallesRemolquePanelControl(((Remolque) objeto)).getDetallesRemolquePanel());
		}

	}

	public void setCamiones(ArrayList<Camion> camiones) {
		this.camiones = camiones;
	}

	public void setEncargoClientes(ArrayList<Encargo> encargos) {
		this.encargosClientes = encargos;
	}

	public void setPresupuestosClientes(ArrayList<Presupuesto> presupuestos) {
		this.presupuestosClientes = presupuestos;
	}

	public void setRemolques(ArrayList<Remolque> remolques) {
		this.remolques = remolques;
	}

	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
}