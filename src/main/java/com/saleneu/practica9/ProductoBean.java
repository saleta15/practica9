package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Imagen;
import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.services.ImagenServices;
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.UsuarioServices;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Named("registroBean")
@ViewScoped
public class ProductoBean {

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

//    <p:fieldset legend="Image from resources">
//                            <p:graphicImage value="#{images.image}">
//                                <f:param name="id" value="4" />
//                            </p:graphicImage>
//                        </p:fieldset>

    public void handleFileUpload(FileUploadEvent event) throws IOException {

        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        this.uploadedFiles.add(event.getFile());


    }



    public void registrarProducto() throws IOException{

        ProductoServices.getInstancia().crear(nuevoProducto);
        int i =0;
        for(UploadedFile archivoSubido: uploadedFiles){
            FacesMessage message = new FacesMessage("Succesful", archivoSubido.getFileName() + " is uploaded.");


        InputStream input = archivoSubido.getInputstream();;
        String root = "/var/webapp/images";
        Path folder = Paths.get(root);

        String filename = nuevoProducto.getId()+"_"+ i;
        String extension = FilenameUtils.getExtension(archivoSubido.getFileName());
        Path file = Files.createTempFile(folder,archivoSubido.getFileName(),extension);

        FacesContext.getCurrentInstance().addMessage(null, message);
        Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        Imagen imagen = new Imagen();
        imagen.setProducto(nuevoProducto);
        imagen.setRuta(root+ "/"+ file.getFileName().toString());
        ImagenServices.getInstancia().crear(imagen);
         i++;
        }

    }



}
