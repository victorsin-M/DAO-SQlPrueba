
package com.mycompany.demo_mysql.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Victor Meza
 */
public class conexion {
    private final static String HOST = "localhost";//127.0.0.1
    private final static String PUERTO ="3306";
    private final static String BD = "poo";
    private final static String USUARIO = "root";
    private final static String CONTRASENA = "";
    private final static String URL="jdbc:mysql://"+HOST+":"+PUERTO+"/"+BD;//"jdbc:mysql://localhost:3306/poo";
    
    
    public static Connection conectar(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,USUARIO,CONTRASENA);
            System.out.println("CONEXION ESTABLECIDA");
        } catch (SQLException e) {
            System.out.println("ERROR NO SE PUDO CONECTAR" + e.getMessage());
        } finally {
        }
        return con; 
    }
}
