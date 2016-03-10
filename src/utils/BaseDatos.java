package utils;

import java.sql.*;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.State;

import entities.Municipio;
import entities.Propiedad;
import entities.Provincia;

public class BaseDatos {

	private static BaseDatos instance;
	
	private Connection con;
    private String dataSource = "//localhost/propiedades";
    private String username = "root";
    private String password = "";
    private String driver = "com.mysql.jdbc.Driver";
    private String protocol = "jdbc:mysql";    
    
    private BaseDatos() {
    	
    }
    
    public void conectar() throws ClassNotFoundException, SQLException{
        Class.forName(driver);        
        String url = protocol + ":" + dataSource;
        con = DriverManager.getConnection(url, username, password);               
    }
    
    public void desconectar() throws SQLException{
        con.close();
    }    
    
    // MÉTODOS SERVICIOS WEB
    
    public Propiedad[] obtenetPropiedades () throws SQLException{        
    	int numProp = numPropiedades();
    	Propiedad [] propiedades = new Propiedad [numProp];
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM propiedad";
        ResultSet rs = stmt.executeQuery(query);
        
        Municipio m = null;
        int pos = 0;
        while (rs.next()){
        	m = obtenerMunicipio(rs.getInt("idMunicipio"));
        	propiedades[pos] = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
        			rs.getString("descripcion"), rs.getString("direccion"), 
        			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
        			rs.getDouble("precio"), m);
        	pos++;
        }
        rs.close();
        stmt.close(); 
        
        return propiedades;
    }
    
    public Propiedad obtenerPropiedad (int id) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM propiedad WHERE idPropiedad = " + id;
    	ResultSet rs = stmt.executeQuery(query);
    	
    	Municipio m = null;
    	Propiedad p = null;
    	if (rs.next()) {
    		m = obtenerMunicipio(rs.getInt("idMunicipio"));
    		p = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
        			rs.getString("descripcion"), rs.getString("direccion"), 
        			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
        			rs.getDouble("precio"), m);
    	}
    	
    	return p;
    }
    
    public Propiedad[] obtenetPropiedadesPorMunicipio (String municipio) throws SQLException{        
    	int numProp = numPropiedadesEnMunicipio(municipio);
    	Propiedad [] propiedades = new Propiedad [numProp];
    	Municipio m = obtenerMunicipioPorNombre(municipio);
    	
    	if (m != null && numProp > 0) {
    		Statement stmt = con.createStatement();
        	String query = "SELECT * FROM propiedad WHERE idMunicipio = " + m.getId();
            ResultSet rs = stmt.executeQuery(query);
            
            int pos = 0;
            while (rs.next()){
            	propiedades [pos] = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
            			rs.getString("descripcion"), rs.getString("direccion"), 
            			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
            			rs.getDouble("precio"), m);
            	pos++;
            }
            rs.close();
            stmt.close();
            
            return propiedades;
    	} else {
    		return null;
    	}
    }
    
    public Propiedad[] obtenetPropiedadesPorNombre (String nombre) throws SQLException{        
    	int numProp = numPropiedadesConNombre(nombre);
    	Propiedad [] propiedades = new Propiedad [numProp];
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM propiedad WHERE nombre LIKE '%" + nombre + "%'";
        ResultSet rs = stmt.executeQuery(query);
        
        Municipio m = null;
        int pos = 0;
        while (rs.next()){
        	m = obtenerMunicipio(rs.getInt("idMunicipio"));
        	propiedades [pos] = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
        			rs.getString("descripcion"), rs.getString("direccion"), 
        			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
        			rs.getDouble("precio"), m);
        	pos++;
        }
        rs.close();
        stmt.close();
        
        return propiedades;
    }
    
    public void insertarPropiedad (Propiedad p) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "INSERT INTO propiedad (nombre, descripcion, direccion, latitud, longitud, "
    			+ "area, precio, idMunicipio) VALUES ('" + p.getNombre() + "', '" + p.getDescripcion() + "', "
    					+ "'" + p.getDireccion() + "', " + p.getLatitud() + ", " + p.getLongitud() + ", "
    							+ p.getArea() +", " + p.getPrecio() + ", " + p.getMunicipio().getId() + ")";
    	stmt.executeUpdate(query);
    	stmt.close();
    }
    
    public void editarPropiedad (Propiedad p) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "UPDATE propiedad SET nombre = '" + p.getNombre() + "', descripcion = '" + p.getDescripcion() + "',"
    			+ " direccion = '" + p.getDireccion() + "', latitud = " + p.getLatitud() + ", "
    					+ "longitud = " + p.getLongitud() + ", area = " + p.getArea() + ", "
    							+ "precio = " + p.getPrecio() + ", idMunicipio = " + p.getMunicipio().getId() + " WHERE "
    									+ "idPropiedad = " + p.getId();
    	stmt.executeUpdate(query);
    	stmt.close();
    }
    
    public void borrarPropiedad (int id) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "DELETE FROM propiedad WHERE idPropiedad = " + id;
    	stmt.executeUpdate(query);
    	stmt.close();
    }
    
    public boolean existeProvinciaConNombre (String provincia) throws SQLException {
    	Statement stmt = con.createStatement();
		String query = "SELECT * FROM provincia WHERE nombre = '" + provincia + "'";
        ResultSet rs = stmt.executeQuery(query);
        
        if (rs.next()) {
        	rs.close();
            stmt.close();
        	return true;
        } else {
        	rs.close();
            stmt.close();
        	return false;
        }
    }
    
    public void insertarProvincia (Provincia p) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "INSERT INTO provincia (nombre) VALUES ('" + p.getNombre() + "')";
    	stmt.executeUpdate(query);
    	stmt.close();
    }
    
    public Provincia obtenerProvinciaPorNombre (String provincia) throws SQLException {
    	Provincia p = null;
    	
    	Statement stmt = con.createStatement();
		String query = "SELECT * FROM provincia WHERE nombre = '" + provincia + "'";
        ResultSet rs = stmt.executeQuery(query);
        
        if (rs.next()) {
        	p = new Provincia(rs.getInt("idProvincia"), rs.getString("nombre"));
        }
        rs.close();
        stmt.close();
        
        return p;
    }
    
    public boolean existeMunicipioConNombre (String nomMunicipio) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM municipio WHERE nombre = '" + nomMunicipio + "'";
        ResultSet rs = stmt.executeQuery(query);
        
        if (rs.next()) {
        	rs.close();
        	stmt.close();
        	return true;
        } else {
        	rs.close();
            stmt.close();
            return false;
        }
    }
    
    public void insertarMunicipio (Municipio m) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "INSERT INTO municipio (nombre, idProvincia) VALUES ('" + m.getNombre() + "',"
    			+ " " + m.getProvincia().getId() + ")";
    	stmt.executeUpdate(query);
    	stmt.close();
    }
    
    public Municipio obtenerMunicipioPorNombre (String nomMunicipio) throws SQLException {
    	Municipio m = null;
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM municipio WHERE nombre = '" + nomMunicipio + "'";
        ResultSet rs = stmt.executeQuery(query);
        
        Provincia p = null;
        if (rs.next()) {
        	p = obtenerProvincia(rs.getInt("idProvincia"));
        	m = new Municipio(rs.getInt("idMunicipio"), rs.getString("nombre"), 
        			p);
        }
        rs.close();
        stmt.close();
        
        return m;
    }
    
    // MÉTODOS AUXILIARES
    
    public Municipio obtenerMunicipio (int id) throws SQLException {
    	Municipio m = null;
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM municipio WHERE idMunicipio = " + id;
        ResultSet rs = stmt.executeQuery(query);
        
        Provincia p = null;
        if (rs.next()) {
        	p = obtenerProvincia(rs.getInt("idProvincia"));
        	m = new Municipio(rs.getInt("idMunicipio"), rs.getString("nombre"), 
        			p);
        }
        rs.close();
        stmt.close();
        
        return m;
    }
    
    public Provincia obtenerProvincia (int id) throws SQLException {
    	Provincia p = null;
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM provincia WHERE idProvincia = " + id;
        ResultSet rs = stmt.executeQuery(query);
        
        if (rs.next()) {
        	p = new Provincia(rs.getInt("idProvincia"), rs.getString("nombre"));
        }
        rs.close();
        stmt.close();
        
        return p;
    }
    
    public int numPropiedades() throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "SELECT COUNT(*) FROM propiedad";
    	ResultSet rs = stmt.executeQuery(query);
    	
    	int num = 0;
    	if (rs.next()) {
    		num = rs.getInt(1);
    	}
    	
    	rs.close();
    	stmt.close();
    	return num;
    }
    
    public int numPropiedadesEnMunicipio(String municipio) throws SQLException {
    	Municipio m = obtenerMunicipioPorNombre(municipio);
    	
    	if (m != null) {
    		Statement stmt = con.createStatement();
        	String query = "SELECT COUNT(*) FROM propiedad WHERE idMunicipio = " + m.getId();
        	ResultSet rs = stmt.executeQuery(query);
        	
        	int num = 0;
        	if (rs.next()) {
        		num = rs.getInt(1);
        	}
        	
        	rs.close();
        	stmt.close();
        	return num;
    	} else {
    		return 0;
    	}
    }
    
    public int numPropiedadesConNombre(String nombre) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "SELECT COUNT(*) FROM propiedad WHERE nombre LIKE '%" + nombre + "%'";
    	ResultSet rs = stmt.executeQuery(query);
    	
    	int num = 0;
    	if (rs.next()) {
    		num = rs.getInt(1);
    	}
    	
    	rs.close();
    	stmt.close();
    	return num;
    }
    
    public static BaseDatos obtenerInstancia() {
    	if(instance == null) {
    		instance = new BaseDatos();
    	}
    	return instance;
    }
}
