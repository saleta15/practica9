package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Venta;
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.VentaServices;
import org.primefaces.event.SelectEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by saleta on 9/25/2016.
 */
@ManagedBean
@SessionScoped
public class HistorialVentasBean {
    private ArrayList<Venta>  ventas;

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public void cargarVentas(Integer id){
        List<Venta> ventas = VentaServices.getInstancia().historialVentas(id);

        this.ventas = (ArrayList<Venta>)ventas;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("h_ventas.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
