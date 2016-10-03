package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.entidades.Venta;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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


    public List<Venta> historialVentas(int id){
        EntityManager em = getEntityManager();
        Producto p = ProductoServices.getInstancia().find(id);
        Query query = em.createQuery("select e from Venta e where e.producto = :id");
        query.setParameter("id", p);
        List<Venta> lista = query.getResultList();
       return lista;
    }

    public List<Object[]> totalesVentas(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e.producto.nombre,sum(e.cantidad) from Venta e group by e.producto");

        List<Object[]> lista = query.getResultList();
        return lista;

    }

    public List<Object[]> ventasPorDia(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e.fecha,sum(e.cantidad*e.precio) from Venta e group by e.fecha");

        List<Object[]> lista = query.getResultList();
        return lista;

    }


}
