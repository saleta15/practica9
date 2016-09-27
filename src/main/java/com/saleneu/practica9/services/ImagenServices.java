package com.saleneu.practica9.services;


import com.saleneu.practica9.Images;
import com.saleneu.practica9.entidades.Imagen;
import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Venta;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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



    public ArrayList<Imagen> getImagenesProducto(Producto producto){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Imagen e where e.producto = :id");
        query.setParameter("id", producto);
        ArrayList<Imagen> lista = (ArrayList<Imagen>)query.getResultList();
        return lista;
    }




}
