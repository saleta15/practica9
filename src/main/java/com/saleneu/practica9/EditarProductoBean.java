package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Imagen;
import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.services.ImagenServices;
import com.saleneu.practica9.services.ProductoServices;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

@ManagedBean
@Named("registroBean")
@SessionScoped
public class EditarProductoBean {

    private Producto nuevoProducto = new Producto();

    private ArrayList<UploadedFile> uploadedFiles = new ArrayList<>();
    public Producto getNuevoProducto() {
        return nuevoProducto;
    }
    int i =0;
    public void setNuevoProducto(Producto nuevoUsuario) {
        this.nuevoProducto = nuevoUsuario;
    }

    @PostConstruct
    public void init(){

    }


    public void inicializar(Producto p){

        this.nuevoProducto=p;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("editar_producto.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void editarProducto() throws IOException{

        ProductoServices.getInstancia().editar(nuevoProducto);


    }





}
