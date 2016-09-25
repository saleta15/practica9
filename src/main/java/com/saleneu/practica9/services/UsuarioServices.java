package com.saleneu.practica9.services;


import com.saleneu.practica9.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * Created by vacax on 03/06/16.
 */
public class UsuarioServices extends GestionDb<Usuario> {

    private static UsuarioServices instancia;

    private UsuarioServices() {
        super(Usuario.class);
    }

    public static UsuarioServices getInstancia(){
        if(instancia==null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }


    public boolean hayAdmin(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Usuario e");
        List<Usuario> lista = query.getResultList();
        return lista.size() > 0;
    }

    public boolean usuarioExiste(String usuario){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Usuario e where e.nombreUsuario = :usuario");
        query.setParameter("usuario", usuario);
        List<Usuario> lista = query.getResultList();
        if (lista.size() < 1)
            return false;
        else{
            return true;
        }
    }

    public boolean registroValido(String usuario, String contrasena){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Usuario e where e.nombreUsuario = :usuario");
        query.setParameter("usuario", usuario);
        List<Usuario> lista = query.getResultList();
        if (lista.size() < 1)
            return false;
        else{
            Usuario u =lista.get(0);
            if(u.getContrasena().equals(contrasena))
                return true;
            else
                return false;
        }
    }



}
