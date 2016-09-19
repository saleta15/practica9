package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.services.UsuarioServices;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@ManagedBean
@Named("registroBean")
public class RegistroBean {

    private Usuario nuevoUsuario = new Usuario();

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    @PostConstruct
    public void init(){

    }

    public void registrarUsuario(){
        nuevoUsuario.setEsAdmin(false);
        UsuarioServices.getInstancia().crear(nuevoUsuario);

    }



}
