package br.ba.fvc.converter;

import br.ba.fvc.mapeamento.Cliente;
import br.ba.fvc.rn.ClienteRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        ClienteRN clienteRN = new ClienteRN();

        return clienteRN.consultar(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;
            return cliente.getCpf();
        } else {
            return "";
        }
    }

}
