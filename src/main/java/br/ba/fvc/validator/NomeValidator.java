package br.ba.fvc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("nomeValidator")
public class NomeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String nomeInput = (String) value;

        if (nomeInput == null || nomeInput.trim().isEmpty()) {
            FacesMessage msg = new FacesMessage("O campo nome é obrigatório.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (!nomeInput.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+")) {
            FacesMessage msg = new FacesMessage("O campo nome só pode conter letras e espaços.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
