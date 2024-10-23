package br.ba.fvc.validator;

import java.time.Year;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("anoValidator")
public class AnoValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {

        if (value == null || value.toString().trim().isEmpty()) {
            FacesMessage msg = new FacesMessage("O campo ano é obrigatório.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        try {
            int anoInput = Integer.parseInt(value.toString());
            int anoAtual = Year.now().getValue();

            if (anoInput < (anoAtual - 10) || anoInput > (anoAtual + 1)) {
                FacesMessage msg = new FacesMessage("O ano deve ser entre " + (anoAtual - 10) + " e " + (anoAtual + 1) + ".");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        } catch (NumberFormatException e) {

            FacesMessage msg = new FacesMessage("Por favor, insira um ano válido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}
