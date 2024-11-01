package br.ba.fvc.beans;

import br.ba.fvc.mapeamento.Aluguel;
import br.ba.fvc.rn.AluguelRN;
import br.ba.fvc.rn.ClienteRN;
import br.ba.fvc.rn.VeiculoRN;
import br.ba.fvc.util.FacesMessages;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "aluguelPendenteBean")
@ViewScoped
public class AluguelPendenteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    AluguelRN aluguelRN;
    VeiculoRN veiculoRN;
    ClienteRN clienteRN;
    private String termoPesquisa;
    private FacesMessages messages;
    private Aluguel aluguelPendente;
    private List<Aluguel> listaAlugueisPendentes;

    public AluguelPendenteBean() {
        aluguelPendente = new Aluguel();
        messages = new FacesMessages();
        popularDataTable();
    }

    public Aluguel getAluguelPendente() {
        return aluguelPendente;
    }

    public void setAluguelPendente(Aluguel aluguelPendente) {
        this.aluguelPendente = aluguelPendente;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public List<Aluguel> getListaAlugueisPendentes() {
        return listaAlugueisPendentes;
    }

    public void setListaAlugueisPendentes(List<Aluguel> listaAlugueisPendentes) {
        this.listaAlugueisPendentes = listaAlugueisPendentes;
    }

    public void popularDataTable() {
        aluguelRN = new AluguelRN();
        listaAlugueisPendentes = aluguelRN.pesquisarAlugueisPendentes();
    }

    public void pesquisar() {
        aluguelRN = new AluguelRN();

        if (termoPesquisa != null && !termoPesquisa.trim().isEmpty()) {
            try {

                int termoPesquisarInt = Integer.parseInt(termoPesquisa);
                listaAlugueisPendentes = aluguelRN.consultarAluguelPendente(termoPesquisarInt);

                if (listaAlugueisPendentes.isEmpty()) {
                    messages.info("Sua consulta não retornou registros.");
                }
            } catch (NumberFormatException e) {
                messages.info("O termo de pesquisa deve ser um número válido.");
            }
        } else {
            this.listaAlugueisPendentes = aluguelRN.pesquisarAlugueisPendentes();
        }
    }

}
