package utils;

import java.io.IOException;
import java.sql.*;

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
    	try {
	    	if(numProvincias() == 0) {
	    		cargarProvincias();
	    	}
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void conectar() throws ClassNotFoundException, SQLException{
        Class.forName(driver);        
        String url = protocol + ":" + dataSource;
        con = DriverManager.getConnection(url, username, password);               
    }
    
    public void desconectar() throws SQLException{
        con.close();
    }
    
    // CARGA DE DATOS EN LA BASE DE DATOS
    private void cargarProvincias() throws SQLException {
    	Statement statement = con.createStatement();
    	try {
    		statement.executeUpdate(Utilities.toString("data/provincias.sql"));
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	statement.close();
    }
    
    // MÉTODOS SERVICIOS WEB
    
    public Propiedad[] obtenerPropiedades () throws SQLException{        
    	int numProp = numPropiedades();
    	Propiedad [] propiedades = new Propiedad [numProp];
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM propiedad";
        ResultSet rs = stmt.executeQuery(query);
        
        Provincia p = null;
        int pos = 0;
        while (rs.next()){
        	p = obtenerProvincia(rs.getInt("idProvincia"));
        	propiedades[pos] = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
        			rs.getString("descripcion"), rs.getString("direccion"), 
        			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
        			rs.getDouble("precio"), p);
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
    	
    	Provincia p = null;
    	Propiedad pro = null;
    	if (rs.next()) {
    		p = obtenerProvincia(rs.getInt("idProvincia"));
    		pro = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
        			rs.getString("descripcion"), rs.getString("direccion"), 
        			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
        			rs.getDouble("precio"), p);
    	}
    	
    	return pro;
    }
    
    public Propiedad[] obtenerPropiedadesPorProvincia (String provincia) throws SQLException{        
    	int numProp = numPropiedadesEnProvincia(provincia);
    	Propiedad [] propiedades = new Propiedad [numProp];
    	Provincia p = obtenerProvinciaPorNombre(provincia);
    	
    	if (p != null && numProp > 0) {
    		Statement stmt = con.createStatement();
        	String query = "SELECT * FROM propiedad WHERE idProvincia = " + p.getId();
            ResultSet rs = stmt.executeQuery(query);
            
            int pos = 0;
            while (rs.next()){
            	propiedades [pos] = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
            			rs.getString("descripcion"), rs.getString("direccion"), 
            			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
            			rs.getDouble("precio"), p);
            	pos++;
            }
            rs.close();
            stmt.close();
            
            return propiedades;
    	} else {
    		return null;
    	}
    }
    
    public Propiedad[] obtenerPropiedadesPorNombre (String nombre) throws SQLException{        
    	int numProp = numPropiedadesConNombre(nombre);
    	Propiedad [] propiedades = new Propiedad [numProp];
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM propiedad WHERE nombre LIKE '%" + nombre + "%'";
        ResultSet rs = stmt.executeQuery(query);
        
        Provincia p = null;
        int pos = 0;
        while (rs.next()){
        	p = obtenerProvincia(rs.getInt("idProvincia"));
        	propiedades [pos] = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"), 
        			rs.getString("descripcion"), rs.getString("direccion"), 
        			rs.getFloat("latitud"), rs.getFloat("longitud"), rs.getDouble("area"), 
        			rs.getDouble("precio"), p);
        	pos++;
        }
        rs.close();
        stmt.close();
        
        return propiedades;
    }
    
    public void insertarPropiedad (Propiedad p) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "INSERT INTO propiedad (nombre, descripcion, direccion, latitud, longitud, "
    			+ "area, precio, idProvincia) VALUES ('" + p.getNombre() + "', '" + p.getDescripcion() + "', "
    					+ "'" + p.getDireccion() + "', " + p.getLatitud() + ", " + p.getLongitud() + ", "
    							+ p.getArea() +", " + p.getPrecio() + ", " + p.getProvincia().getId() + ")";
    	stmt.executeUpdate(query);
    	stmt.close();
    }
    
    public void editarPropiedad (Propiedad p) throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "UPDATE propiedad SET nombre = '" + p.getNombre() + "', descripcion = '" + p.getDescripcion() + "',"
    			+ " direccion = '" + p.getDireccion() + "', latitud = " + p.getLatitud() + ", "
    					+ "longitud = " + p.getLongitud() + ", area = " + p.getArea() + ", "
    							+ "precio = " + p.getPrecio() + ", idProvincia = " + p.getProvincia().getId() + " WHERE "
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
    
    public Provincia[] obtenerProvincias() throws SQLException {
    	int numProvincias = numProvincias();
    	Provincia[] provincias = new Provincia[numProvincias];
    	
    	Statement stmt = con.createStatement();
    	String query = "SELECT * FROM provincia";
    	ResultSet rs = stmt.executeQuery(query);
    	
    	int pos = 0;
    	while (rs.next()) {
    		Provincia p = new Provincia(rs.getInt("idProvincia"), rs.getString("nombre"));
    		provincias[pos] = p;
    		pos++;
    	}
    	
    	rs.close();
    	stmt.close();
    	
    	return provincias;
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
    
    // MÉTODOS AUXILIARES
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
    
    public int numProvincias() throws SQLException {
    	Statement stmt = con.createStatement();
    	String query = "SELECT COUNT(*) FROM provincia";
    	ResultSet rs = stmt.executeQuery(query);
    	
    	int num = 0;
    	if (rs.next()) {
    		num = rs.getInt(1);
    	}
    	
    	rs.close();
    	stmt.close();
    	return num;
    }
    
    public int numPropiedadesEnProvincia(String provincia) throws SQLException {
    	Provincia p = obtenerProvinciaPorNombre(provincia);
    	
    	if (p != null) {
    		Statement stmt = con.createStatement();
        	String query = "SELECT COUNT(*) FROM propiedad WHERE idProvincia = " + p.getId();
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
