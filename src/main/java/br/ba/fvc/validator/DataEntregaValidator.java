package br.ba.fvc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

@FacesValidator("dataEntregaValidator")
public class DataEntregaValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        Date dataAluguel = (Date) component.getAttributes().get("dataRetirada");

        if (dataAluguel == null) {
            return;
        }

        Date dataEntrega = (Date) value;

        if (dataEntrega.before(dataAluguel)) {
            FacesMessage msg = new FacesMessage("A data de entrega não pode ser anterior à data de retirada.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}
