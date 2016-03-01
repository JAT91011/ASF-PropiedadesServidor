package entities;

import java.util.Date;

public class Alquiler {

	private Propiedad	propiedad;
	private Cliente		cliente;
	private Actividad	actividad;
	private Date		fechaInicio;
	private Date		fechaFin;
	private double		precio;

	public Alquiler(Propiedad propiedad, Cliente cliente, Actividad actividad, Date fechaInicio, Date fechaFin, double precio) {
		super();
		this.propiedad = propiedad;
		this.cliente = cliente;
		this.actividad = actividad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Alquiler [propiedad=" + propiedad + ", cliente=" + cliente + ", actividad=" + actividad + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", precio=" + precio + "]";
	}
}