package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Venta;

/**
 *
 * Created by vacax on 03/06/16.
 */
public class VentaServices extends GestionDb<Venta> {

    private static VentaServices instancia;

    private VentaServices() {
        super(Venta.class);
    }

    public static VentaServices getInstancia(){
        if(instancia==null){
            instancia = new VentaServices();
        }
        return instancia;
    }

    /**
     *
     * @param nombre
     * @return
     */



}
