/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo_mysql.modelos;

/**
 *
 * @author Victor Meza
 */
public class administrador {
    private int id;
    private String usuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contraseña;
    private Eroles rol;
    private String rutaImagen;
    private EEstados estado;

    public administrador() {
        
        
    }

    public administrador(int id, String usuario, String nombre, String apellidos, String correo, String contraseña, Eroles rol, String rutaImagen, EEstados estado) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
        this.rutaImagen = rutaImagen;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Eroles getRol() {
        return rol;
    }

    public void setRol(Eroles rol) {
        this.rol = rol;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public EEstados getEstado() {
        return estado;
    }

    public void setEstado(EEstados estado) {
        this.estado = estado;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final administrador other = (administrador) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", rol=" + rol + ", rutaImagen=" + rutaImagen + ", estado=" + estado + '}';
    }

 
    
    
    
    
    
}
