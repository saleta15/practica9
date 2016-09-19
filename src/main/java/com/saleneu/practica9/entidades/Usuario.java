package com.saleneu.practica9.entidades;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;

/**
 * Created by vacax on 02/06/16.
 */
@Entity
public class Usuario implements Serializable {

    @Id
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private String apellido;
    private Boolean esAdmin;

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public Usuario(String nombreUsuario, String contrasena, String nombre, String apellido, Boolean esAdmin) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.esAdmin = esAdmin;
    }

    public Usuario(){

    }




    public String getNombreUsuario() {
        return nombreUsuario;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}
