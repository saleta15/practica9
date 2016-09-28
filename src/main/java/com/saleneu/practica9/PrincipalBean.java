package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.UsuarioServices;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class PrincipalBean {

    private ArrayList<Producto> carritoCompras = new ArrayList<>();


    private LazyDataModel<Producto> lazyModel = null;
    @PostConstruct
    public void init(){
        lazyModel = new ProductoLazyList();
    }


    private Producto player;


    public LazyDataModel<Producto> getLazyModel() {
        return lazyModel;
    }

    public ArrayList<Producto> getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(ArrayList<Producto> carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public Producto getPlayer() {
        if(player == null){
            player = new Producto();
        }

        return player;
    }

    public void setPlayer(Producto player) {
        this.player = player;
    }





}
