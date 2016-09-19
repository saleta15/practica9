package com.saleneu.practica9.entidades;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private  Usuario usuario;
    @OneToOne
    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    private  String mensaje;
    private int rating;

    public Comentario(){

    }

    public Comentario(Usuario usuario, String mensaje, int rating) {
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
