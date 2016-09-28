package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.entidades.Venta;
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.UsuarioServices;
import com.saleneu.practica9.services.VentaServices;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dell_2 on 9/26/2016.
 */
@ManagedBean
@SessionScoped
public class CarritoCompraBean {

    @ManagedProperty(value = "#{principalBean.carritoCompras}")
    private ArrayList<Producto> carritoCompras;
    private int totalCarrito;

    public ArrayList<Producto> getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(ArrayList<Producto> carritoCompras) {
        this.carritoCompras = carritoCompras;
    }




    public void comprarProductos() {
        for(Producto p : carritoCompras){
            int cantidad = ProductoServices.getInstancia().cantidadProducto(p);
            if(cantidad < p.getCantidad()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El producto " + p.getNombre() + " se ha acabado"));
                return;

            }

        }

        for(Producto p : carritoCompras){
            int cantidad = ProductoServices.getInstancia().cantidadProducto(p);
            Venta venta = new Venta();
            venta.setCantidad(p.getCantidad());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            Usuario usuario = (Usuario)session.getAttribute("usuario");
            venta.setUsuario(usuario);
            venta.setProducto(p);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY,17);
            cal.set(Calendar.MINUTE,30);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            Date fecha = cal.getTime();
            venta.setFecha(fecha);

            venta.setPrecio(p.getPrecio());
            VentaServices.getInstancia().crear(venta);
            p.setCantidad(cantidad-p.getCantidad());
            ProductoServices.getInstancia().editar(p);


        }
        carritoCompras.clear();



        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void borrarDeCarrito(Producto producto){

        for(Producto p: carritoCompras){
            if(p.getId() == producto.getId()){
                carritoCompras.remove(p);
                break;
            }

        }
    }

    public int getTotalCarrito(){
        totalCarrito = 0;
        for(Producto p: carritoCompras){
            totalCarrito+=p.getPrecio()*p.getCantidad();
        }
        return totalCarrito;
    }


}
