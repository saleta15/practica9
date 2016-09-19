package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Comentario;

/**
 *
 * Created by vacax on 03/06/16.
 */
public class ComentarioServices extends GestionDb<Comentario> {

    private static ComentarioServices instancia;

    private ComentarioServices() {
        super(Comentario.class);
    }

    public static ComentarioServices getInstancia(){
        if(instancia==null){
            instancia = new ComentarioServices();
        }
        return instancia;
    }

    /**
     *
     * @param nombre
     * @return
     */



}
