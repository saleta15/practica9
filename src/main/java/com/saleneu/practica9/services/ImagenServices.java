package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Imagen;

/**
 *
 * Created by vacax on 03/06/16.
 */
public class ImagenServices extends GestionDb<Imagen> {

    private static ImagenServices instancia;

    private ImagenServices() {
        super(Imagen.class);
    }

    public static ImagenServices getInstancia(){
        if(instancia==null){
            instancia = new ImagenServices();
        }
        return instancia;
    }




}
