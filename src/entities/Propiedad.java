package entities;

public class Propiedad {

	private int			id;
	private String		nombre;
	private String		descripcion;
	private String		direccion;
	private float		latitud;
	private float		longitud;
	private double		area;
	private double		precio;
	private Municipio	municipio;

	public Propiedad() {
		
	}
	
	public Propiedad(int id, String nombre, String descripcion, String direccion, float latitud, float longitud, double area, double precio, Municipio municipio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.area = area;
		this.precio = precio;
		this.municipio = municipio;
	}
	
	public Propiedad(String nombre, String descripcion, String direccion, float latitud, float longitud, double area, double precio, Municipio municipio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.area = area;
		this.precio = precio;
		this.municipio = municipio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
}