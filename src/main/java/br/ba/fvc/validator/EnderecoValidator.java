package br.ba.fvc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("enderecoValidator")
public class EnderecoValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String nomeInput = (String) value;

        if (nomeInput == null || nomeInput.trim().isEmpty()) {
            FacesMessage msg = new FacesMessage("O campo endereço é obrigatório.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (!nomeInput.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9\\s]+$")) {
            FacesMessage msg = new FacesMessage("Endereço Inválido!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
