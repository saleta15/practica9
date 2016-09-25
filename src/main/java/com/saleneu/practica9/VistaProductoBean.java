package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.services.ProductoServices;
import org.primefaces.event.SelectEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Map;

/**
 * Created by saleta on 9/25/2016.
 */
@ManagedBean
@SessionScoped
public class VistaProductoBean {
    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void onRowSelect(SelectEvent evento){
        Producto nuevoProducto = (Producto)evento.getObject();
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.producto = nuevoProducto;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("vista_producto.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
