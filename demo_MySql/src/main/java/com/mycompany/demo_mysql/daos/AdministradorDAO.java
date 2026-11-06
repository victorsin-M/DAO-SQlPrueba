/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo_mysql.daos;

import com.mycompany.demo_mysql.modelos.administrador;
import java.sql.SQLException;

import com.mycompany.demo_mysql.config.conexion;
import com.mycompany.demo_mysql.modelos.EEstados;
import com.mycompany.demo_mysql.modelos.Eroles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author Victor Meza
 */
public class AdministradorDAO {
    
    public boolean guardar(administrador admin){
        Connection con = null;
        String sql= "INSERT INTO administradores"
                + "(usuario,nombres,apellidos,correo,contrasena,rol,ruta_imagen,estado)  "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            con = conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getUsuario());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getApellidos());
            ps.setString(4, admin.getCorreo());
            ps.setString(5, admin.getContraseña());
            ps.setString(6, admin.getRol().name());
            ps.setString(7, admin.getRutaImagen());
            ps.setString(8, admin.getEstado().name());
            
            return ps.executeUpdate()> 0;
        } catch (SQLException e) {
            System.out.println("Error = " + e.getMessage());
        }
        return false;
    }
    
    
    public boolean actualizar(administrador admin) {
    String sql = "UPDATE administradores "
            + "SET usuario = ?, nombres = ?, apellidos = ?, "
            + "correo = ?, contrasena = ?, rol = ?, "
            + "ruta_imagen = ?, estado = ? "
            + "WHERE id = ?";

    try (Connection con = conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, admin.getUsuario());
        ps.setString(2, admin.getNombre());
        ps.setString(3, admin.getApellidos());
        ps.setString(4, admin.getCorreo());
        ps.setString(5, admin.getContraseña());
        ps.setString(6, admin.getRol().name());
        ps.setString(7, admin.getRutaImagen());
        ps.setString(8, admin.getEstado().name());
        ps.setInt(9, admin.getId());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar: " + e.getMessage());
    }

    return false;
}
    
    
    public boolean borrar(int id) {
    String sql = "DELETE FROM administradores  "
            + "WHERE id = ?";

    try (Connection con = conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1 ,id);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar: " + e.getMessage());
    }

    return false;
}
    
public List<administrador> obtenerTodos() {
    List<administrador> lista = new ArrayList<>();

    String sql = "SELECT * FROM administradores";

    try (Connection con = conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            administrador admin = new administrador();

            admin.setId(rs.getInt("id"));
            admin.setUsuario(rs.getString("usuario"));
            admin.setNombre(rs.getString("nombres"));
            admin.setApellidos(rs.getString("apellidos"));
            admin.setCorreo(rs.getString("correo"));
            admin.setContraseña(rs.getString("contrasena"));
            admin.setRol(Eroles.valueOf(rs.getString("rol")));
            admin.setRutaImagen(rs.getString("ruta_imagen"));
            admin.setEstado(EEstados.valueOf(rs.getString("estado")));

            lista.add(admin);
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener administradores: "
                + e.getMessage());
    }

    return lista;
}





public List<administrador> obtenerAlgunos(String campo) {
    List<administrador> lista = new ArrayList<>();

    String sql = "SELECT * FROM administradores WHERE estado = ?";

    try (Connection con = conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, campo);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                administrador admin = new administrador();

                admin.setId(rs.getInt("id"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setNombre(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                admin.setCorreo(rs.getString("correo"));
                admin.setContraseña(rs.getString("contrasena"));
                admin.setRol(Eroles.valueOf(rs.getString("rol")));
                admin.setRutaImagen(rs.getString("ruta_imagen"));
                admin.setEstado(EEstados.valueOf(rs.getString("estado")));

                lista.add(admin);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener administradores: " + e.getMessage());
    }

    return lista;
}




public administrador obtenerXId(int id) {
    String sql = "SELECT * FROM administradores WHERE id = ?";

    try (Connection con = conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                administrador admin = new administrador();

                admin.setId(rs.getInt("id"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setNombre(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                admin.setCorreo(rs.getString("correo"));
                admin.setContraseña(rs.getString("contrasena"));
                admin.setRol(Eroles.valueOf(rs.getString("rol")));
                admin.setRutaImagen(rs.getString("ruta_imagen"));
                admin.setEstado(EEstados.valueOf(rs.getString("estado")));

                return admin;
            }
        }

   } catch (SQLException e) {
        System.out.println("Error al obtener administrador: " + e.getMessage());
    }

    return null;
}
}




