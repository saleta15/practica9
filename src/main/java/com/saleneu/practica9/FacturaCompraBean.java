package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Venta;
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.VentaServices;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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

    @ManagedProperty(value = "#{principalBean.carritoCompras}")
    private ArrayList<Producto> carritoCompras;
    private Producto producto;
    private int cantidad;

    public ArrayList<Producto> getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(ArrayList<Producto> carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

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

    public String agregarAlCarro() {
        this.producto.setCantidad(this.cantidad);
        this.carritoCompras.add(this.producto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Producto añadido"));
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        this.cantidad = 0;

        return "principal?faces-redirect=true";

    }


}
