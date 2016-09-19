package com.saleneu.practica9.entidades;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Producto producto;
    @OneToOne
    private Usuario usuario;
    private int cantidad;
    @Transient
    private int totalVendido;

    public Producto getProducto() {
        return producto;
    }

    public int getId() {
        return id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(int totalVendido) {
        this.totalVendido = totalVendido;
    }
}
