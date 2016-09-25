package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    public  List<Producto> paginarProductos(int pageNumber, int pageSize, String filtro){
        Query query;
        if(!filtro.equals("")){
            query = getEntityManager().createQuery("select e from Producto e where e.nombre like :filtro");
            query.setParameter("filtro", "%"+filtro+"%");
        }
        else{
            query = getEntityManager().createQuery("from Producto");
        }

        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);
        List <Producto> fooList = query.getResultList();
        return fooList;
    }

    public  long cantidadProductos(){
        Query query = getEntityManager().createQuery("select count (e.id)from Producto e ");


        List <Producto> fooList = query.getResultList();
        return (long)query.getSingleResult();

    }

    public  long cantidadProductosFiltro(String valor){
        Query query = getEntityManager().createQuery("select count (e.id)from Producto e where e.nombre like :valor");
        query.setParameter("valor", "%"+valor + "%");
        long count = (long)query.getSingleResult();
        return count;

    }

    public List<Producto> filtrar(String parametro, String valor){
        EntityManager em = getEntityManager();
        Query query;

            query = em.createQuery("select e from Producto e where e.nombre like :valor");

        query.setParameter("valor", "%"+valor+"%");
        List<Producto> lista = query.getResultList();
        return lista;
    }




}
