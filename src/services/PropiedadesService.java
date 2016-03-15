package services;

import java.sql.SQLException;
import java.util.ArrayList;


import entities.Municipio;
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
	
	public Propiedad[] obtenerPropiedadesPorMunicipio (String municipio) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] p = BaseDatos.obtenerInstancia().obtenerPropiedadesPorMunicipio(municipio);
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public Propiedad[] obtenerPropiedadesPorNombre (String nombre) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] p = BaseDatos.obtenerInstancia().obtenerPropiedadesPorNombre(nombre);
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public Propiedad obtenerPropiedad (int id) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad p = BaseDatos.obtenerInstancia().obtenerPropiedad(id);
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public void insertarPropiedad (Propiedad p) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		BaseDatos.obtenerInstancia().insertarPropiedad(p);
		BaseDatos.obtenerInstancia().desconectar();
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
	
	public boolean existeProvinciaConNombre (String provincia) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		boolean resul = BaseDatos.obtenerInstancia().existeProvinciaConNombre(provincia);
		BaseDatos.obtenerInstancia().desconectar();
		
		return resul;
	}
	
	public void insertarProvincia (Provincia p) throws ClassNotFoundException, SQLException {
		BaseDatos.obtenerInstancia().conectar();
		BaseDatos.obtenerInstancia().insertarProvincia(p);
		BaseDatos.obtenerInstancia().desconectar();
	}
	
	public Provincia obtenerProvinciaPorNombre (String provincia) throws ClassNotFoundException, SQLException {
		BaseDatos.obtenerInstancia().conectar();
		Provincia p = BaseDatos.obtenerInstancia().obtenerProvinciaPorNombre(provincia);
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public boolean existeMunicipioConNombre (String nomMunicipio) throws ClassNotFoundException, SQLException {
		BaseDatos.obtenerInstancia().conectar();
		boolean resul = BaseDatos.obtenerInstancia().existeMunicipioConNombre(nomMunicipio);
		BaseDatos.obtenerInstancia().desconectar();
		
		return resul;
	}
	
	public void insertarMunicipio (Municipio m) throws ClassNotFoundException, SQLException {
		BaseDatos.obtenerInstancia().conectar();
		BaseDatos.obtenerInstancia().insertarMunicipio(m);
		BaseDatos.obtenerInstancia().desconectar();
	}
	
	public Municipio obtenerMunicipioPorNombre (String nomMunicipio) throws ClassNotFoundException, SQLException {
		BaseDatos.obtenerInstancia().conectar();
		Municipio m = BaseDatos.obtenerInstancia().obtenerMunicipioPorNombre(nomMunicipio);
		BaseDatos.obtenerInstancia().desconectar();
		
		return m;
	}
}
