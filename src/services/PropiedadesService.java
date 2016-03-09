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
	
	public ArrayList<Propiedad> obtenerPropiedades() throws SQLException, ClassNotFoundException {
		ArrayList<Propiedad> alp = new ArrayList<Propiedad>();
		
		BaseDatos.obtenerInstancia().conectar();
		alp = BaseDatos.obtenerInstancia().obtenetPropiedades();
		BaseDatos.obtenerInstancia().desconectar();
		
		return alp;
	}
	
	public ArrayList<Propiedad> obtenerPropiedadesPorMunicipio (String municipio) throws SQLException, ClassNotFoundException {
		ArrayList<Propiedad> alp = new ArrayList<Propiedad>();
		
		BaseDatos.obtenerInstancia().conectar();
		alp = BaseDatos.obtenerInstancia().obtenetPropiedadesPorMunicipio(municipio);
		BaseDatos.obtenerInstancia().desconectar();
		
		return alp;
	}
	
	public ArrayList<Propiedad> obtenerPropiedadesPorNombre (String nombre) throws SQLException, ClassNotFoundException {
		ArrayList<Propiedad> alp = new ArrayList<Propiedad>();
		
		BaseDatos.obtenerInstancia().conectar();
		alp = BaseDatos.obtenerInstancia().obtenetPropiedadesPorNombre(nombre);
		BaseDatos.obtenerInstancia().desconectar();
		
		return alp;
	}
	
	public Propiedad obtenerPropiedad (int id) throws SQLException, ClassNotFoundException {
		Propiedad p = new Propiedad();
		
		BaseDatos.obtenerInstancia().conectar();
		p = BaseDatos.obtenerInstancia().obtenerPropiedad(id);
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
}
