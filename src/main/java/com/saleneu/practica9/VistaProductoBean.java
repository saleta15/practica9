package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Comentario;
import com.saleneu.practica9.entidades.Imagen;
import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.services.ComentarioServices;
import com.saleneu.practica9.services.ImagenServices;
import com.saleneu.practica9.services.ProductoServices;
import org.primefaces.event.SelectEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.awt.font.ImageGraphicAttribute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by saleta on 9/25/2016.
 */
@ManagedBean
@SessionScoped
public class VistaProductoBean {
    private Producto producto;
    private ArrayList<Imagen> imagenes;
    private ArrayList<Comentario> comentarios;
    private Comentario comentario = new Comentario();
    private boolean admin;

    public Comentario getComentario() {
        if(comentario ==null){
            this.comentario = new Comentario();
            comentario.setRating(2);
        }
        return comentario;
    }

    public boolean isAdmin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        return usuario.getEsAdmin();
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public ArrayList<Comentario> getComentarios() {
        if(comentarios.size()!=0)
         return comentarios;
        else{
            this.comentarios = ComentarioServices.getInstancia().getComentariosProducto(this.producto);
            return comentarios ;
        }
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Imagen> getImagenes() {
        if(imagenes!=null)
            return imagenes;
        else
            return ImagenServices.getInstancia().getImagenesProducto(this.producto);
    }

    public void setImagenes(ArrayList<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void onRowSelect(SelectEvent evento){
        this.comentarios = new ArrayList<Comentario>();
        Producto nuevoProducto = (Producto)evento.getObject();
        this.producto = nuevoProducto;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("vista_producto.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void dejarComentario(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setProducto(this.producto);
        nuevoComentario.setUsuario(usuario);
        nuevoComentario.setRating(comentario.getRating());
        nuevoComentario.setMensaje(comentario.getMensaje());
        ComentarioServices.getInstancia().crear(nuevoComentario);
        this.comentarios.add(nuevoComentario);
        this.comentario = new Comentario();
        comentario.setRating(0);
    }


}
