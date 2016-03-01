package utils;

import java.sql.*;

import entities.Provincia;

public class BaseDatos {

	private static BaseDatos instance;
	
	private Connection con;
    private String dataSource = "//localhost/Propiedades";
    private String username = "root";
    private String password = "toor";
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
    
    public Provincia obtenerProvincia(String id) throws SQLException{        
    	Provincia provincia = null;
    	String select = "select * from CLIENTES where id=" + id;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        if (rs.next()){
        	provincia = new Provincia(rs.getInt("id"), rs.getString("nombre"));
        }
        rs.close();
        stmt.close();  
        return provincia;
    }
    
    public static BaseDatos obtenerInstancia() {
    	if(instance == null) {
    		instance = new BaseDatos();
    	}
    	return instance;
    }
}
