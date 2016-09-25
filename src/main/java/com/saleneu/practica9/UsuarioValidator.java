package com.saleneu.practica9;

/**
 * Created by saleta on 9/21/2016.
 */
import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.services.UsuarioServices;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "miValidadorUsuario")
public class UsuarioValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //validando que la matricula no sea menor al 2000
        String usuario = (String) value;
        if(UsuarioServices.getInstancia().usuarioExiste(usuario))
        {
            FacesMessage msg =
                    new FacesMessage("Hay una cuenta con este usuario.",
                            "Usuario ya existe.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }




    }
}
