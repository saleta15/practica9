package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Venta;
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.VentaServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell_2 on 9/26/2016.
 */
@ManagedBean
@SessionScoped
public class FacturaCompraBean {


    private Producto producto;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void facturaVentas(Integer id){
       Producto producto = ProductoServices.getInstancia().find(id);

        this.producto=producto;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("factura.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disponible(Integer id){
        Producto producto = ProductoServices.getInstancia().find(id);
        int disponible = producto.getCantidad();
        if(disponible>cantidad)
        {

        }


    }
}
