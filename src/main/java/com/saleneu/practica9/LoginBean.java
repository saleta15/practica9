package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.services.UsuarioServices;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.IOException;

@ManagedBean
public class LoginBean {

    private String usuario;
    private String contrasena;
    @PostConstruct
    public void init(){
        UsuarioServices us = UsuarioServices.getInstancia();
        if(!us.hayAdmin()){
            us.crear(new Usuario("admin","1234","Administrador","",true));
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void login() {

        Boolean papazon = UsuarioServices.getInstancia().registroValido(usuario,contrasena);
        if(!papazon){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Credenciales no validas");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
        else{
            try {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("usuario", UsuarioServices.getInstancia().find(usuario));
                FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
