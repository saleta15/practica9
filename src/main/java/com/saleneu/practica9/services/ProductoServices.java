package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Producto;

/**
 *
 * Created by vacax on 03/06/16.
 */
public class ProductoServices extends GestionDb<Producto> {

    private static ProductoServices instancia;

    private ProductoServices() {
        super(Producto.class);
    }

    public static ProductoServices getInstancia(){
        if(instancia==null){
            instancia = new ProductoServices();
        }
        return instancia;
    }

    /**
     *
     * @param nombre
     * @return
     */



}
