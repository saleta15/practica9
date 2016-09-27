package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Comentario;
import com.saleneu.practica9.entidades.Imagen;
import com.saleneu.practica9.entidades.Producto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

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

    public ArrayList<Comentario> getComentariosProducto(Producto producto){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Comentario e where e.producto = :id");
        query.setParameter("id", producto);
        ArrayList<Comentario> lista = (ArrayList<Comentario>)query.getResultList();
        return lista;
    }



}
