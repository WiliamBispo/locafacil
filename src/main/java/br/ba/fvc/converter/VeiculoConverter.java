package br.ba.fvc.converter;

import br.ba.fvc.mapeamento.Veiculo;
import br.ba.fvc.rn.VeiculoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("veiculoConverter")
public class VeiculoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        VeiculoRN veiculoRN = new VeiculoRN();

        return veiculoRN.consultar(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Veiculo) {
            Veiculo veiculo = (Veiculo) obj;
            return veiculo.getPlaca();
        } else {
            return "";
        }
    }

}
