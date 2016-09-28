package com.saleneu.practica9;

/**
 * Created by saleta on 9/21/2016.
 */
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.UsuarioServices;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "miValidadorDisponibilidad")
public class DisponibilidadValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Integer valor = (Integer) value;
        Integer id = (Integer)component.getAttributes().get("producto");
        if(ProductoServices.getInstancia().find(id).getCantidad()< valor)
        {
            FacesMessage msg =
                    new FacesMessage("No hay suficiente.",
                            "No hay suficiente.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }


    }
}
