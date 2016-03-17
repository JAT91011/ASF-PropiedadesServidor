package services;

import java.sql.SQLException;
import entities.Propiedad;
import entities.Provincia;
import utils.BaseDatos;;

public class PropiedadesService {
	
	public PropiedadesService() {
		
	}
	
	public Propiedad[] obtenerPropiedades () throws ClassNotFoundException, SQLException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] propiedades = BaseDatos.obtenerInstancia().obtenerPropiedades();
		BaseDatos.obtenerInstancia().desconectar();
		
		return propiedades;
	}
	
	public Propiedad[] obtenerPropiedadesPorProvincia (String provincia) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] p = BaseDatos.obtenerInstancia().obtenerPropiedadesPorProvincia(provincia);
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public Propiedad[] obtenerPropiedadesPorNombre (String nombre) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] p = BaseDatos.obtenerInstancia().obtenerPropiedadesPorNombre(nombre);
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public String insertarPropiedad (Propiedad p) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		// Comprobar si existe la provincia
		Provincia provincia = BaseDatos.obtenerInstancia().obtenerProvinciaPorNombre(p.getProvincia().getNombre());
		if(provincia != null) {
			p.setProvincia(provincia);
			BaseDatos.obtenerInstancia().insertarPropiedad(p);
			BaseDatos.obtenerInstancia().desconectar();
			return "OK";
		} else {
			BaseDatos.obtenerInstancia().desconectar();
			return "ERR:No existe la provincia: " + p.getProvincia().getNombre();
		}
	}
	
	public void editarPropiedad (Propiedad p) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		BaseDatos.obtenerInstancia().editarPropiedad(p);
		BaseDatos.obtenerInstancia().desconectar();
	}
	
	public void borrarPropiedad (int id) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		BaseDatos.obtenerInstancia().borrarPropiedad(id);
		BaseDatos.obtenerInstancia().desconectar();
	}
	
	public Provincia[] obtenerProvincias() throws ClassNotFoundException, SQLException {
		BaseDatos.obtenerInstancia().conectar();
		Provincia[] provincias = BaseDatos.obtenerInstancia().obtenerProvincias();
		BaseDatos.obtenerInstancia().desconectar();
		return provincias;
	}
}
