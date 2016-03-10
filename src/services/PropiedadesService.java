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
	
	public Propiedad[] obtenerPropiedades() throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] p = BaseDatos.obtenerInstancia().obtenetPropiedades();
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public Propiedad[] obtenerPropiedadesPorMunicipio (String municipio) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] p = BaseDatos.obtenerInstancia().obtenetPropiedadesPorMunicipio(municipio);
		BaseDatos.obtenerInstancia().desconectar();
		
		return p;
	}
	
	public Propiedad[] obtenerPropiedadesPorNombre (String nombre) throws SQLException, ClassNotFoundException {
		BaseDatos.obtenerInstancia().conectar();
		Propiedad [] p = BaseDatos.obtenerInstancia().obtenetPropiedadesPorNombre(nombre);
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
	
	public static void main (String [] args) throws SQLException, ClassNotFoundException {
		// Declaramos el servicio
		PropiedadesService ps = new PropiedadesService();
		
		// Provincia Madrid
		if (!ps.existeProvinciaConNombre("Madrid")) {
			ps.insertarProvincia(new Provincia("Madrid"));
		}
		Provincia p_Madrid = ps.obtenerProvinciaPorNombre("Madrid");
		
		// Provincia Vizcaya
		if (!ps.existeProvinciaConNombre("Vizcaya")) {
			ps.insertarProvincia(new Provincia("Vizcaya"));
		}
		Provincia p_Vizcaya = ps.obtenerProvinciaPorNombre("Vizcaya");
		
		// Provincia Alava
		if (!ps.existeProvinciaConNombre("Alava")) {
			ps.insertarProvincia(new Provincia("Alava"));
		}
		Provincia p_Alava = ps.obtenerProvinciaPorNombre("Alava");
		
		// Municipio Bilbao
		if (!ps.existeMunicipioConNombre("Bilbao")) {
			ps.insertarMunicipio(new Municipio("Bilbao", p_Vizcaya));
		}
		Municipio m_Bilbao = ps.obtenerMunicipioPorNombre("Bilbao");
		
		// Municipio Karrantza
		if (!ps.existeMunicipioConNombre("Karrantza")) {
			ps.insertarMunicipio(new Municipio("Karrantza", p_Vizcaya));
		}
		Municipio m_Karrantza = ps.obtenerMunicipioPorNombre("Karrantza");
		
		// Municipio Madrid
		if (!ps.existeMunicipioConNombre("Madrid")) {
			ps.insertarMunicipio(new Municipio("Madrid", p_Madrid));
		}
		Municipio m_Madrid = ps.obtenerMunicipioPorNombre("Madrid");
		
		// Municipio Vitoria
		if (!ps.existeMunicipioConNombre("Vitoria")) {
			ps.insertarMunicipio(new Municipio("Vitoria", p_Alava));
		}
		Municipio m_Vitoria = ps.obtenerMunicipioPorNombre("Vitoria");
		
		// Propiedades
		Propiedad pro_Bilbao = new Propiedad("Terreno Bilbao", "grande", "Calle falsa 1234", 25.564561f, -4.5646513216f, 2500.1, 500, m_Bilbao);
		Propiedad pro_Karrantza = new Propiedad("Terreno Karrantza", "grande", "Calle falsa 1234", 25.564561f, -4.5646513216f, 2500.1, 500, m_Karrantza);
		Propiedad pro_Madrid = new Propiedad("Terreno Madrid", "grande", "Calle falsa 1234", 25.564561f, -4.5646513216f, 2500.1, 500, m_Madrid);
		Propiedad pro_Vitoria = new Propiedad("Terreno Vitoria", "grande", "Calle falsa 1234", 25.564561f, -4.5646513216f, 2500.1, 500, m_Vitoria);
		
		// Insertamos las propiedades
		ps.insertarPropiedad(pro_Bilbao);
		ps.insertarPropiedad(pro_Karrantza);
		ps.insertarPropiedad(pro_Madrid);
		ps.insertarPropiedad(pro_Vitoria);
		System.out.println("Insercion realizada correctamente\n");
		
		// Obtenemos las propiedades y las mostramos por pantalla
		System.out.println("Propiedades insertadas:");
		Propiedad [] propiedades = ps.obtenerPropiedades();
		for (int i = 0; i < propiedades.length; i++) {
			System.out.println((i+1) + ". " + propiedades[i].getNombre() + "\n");
		}
		
		// Mostramos las propiedades de Bilbao
		System.out.println("Propiedades en Bilbao:");
		Propiedad [] propiedadesMunicipio = ps.obtenerPropiedadesPorMunicipio("Bilbao");
		for (int i = 0; i < propiedadesMunicipio.length; i++) {
			System.out.println((i+1) + ". " + propiedadesMunicipio[i].getNombre() + "\n");
		}
		
		// Mostramos las propiedades que tengan como nombre Karrantza
		System.out.println("Propiedades con nombre Karrantza:");
		Propiedad [] propiedadesNombre = ps.obtenerPropiedadesPorNombre("Karrantza");
		for (int i = 0; i < propiedadesNombre.length; i++) {
			System.out.println((i+1) + ". " + propiedadesNombre[i].getNombre() + "\n");
		}
		
		// Editamos la propiedad de Madrid
		for (int i = 0; i < propiedades.length; i++) {
			if (propiedades[i].getNombre().equals("Terreno Madrid")) {
				propiedades[i].setNombre("Terreno Madrid Editado");
				ps.editarPropiedad(propiedades[i]);
				System.out.println("La propiedad " + propiedades[i].getNombre() + " ha sido editada correctamente.\n");
			}
		}
		
		// Borramos la propiedad de Vitoria
		for (int i = 0; i < propiedades.length; i++) {
			if (propiedades[i].getNombre().equals("Terreno Vitoria")) {
				ps.borrarPropiedad(propiedades[i].getId());
				System.out.println("La propiedad " + propiedades[i].getNombre() + " ha sido borrada correctamente.\n");
			}
		}
	}
}
