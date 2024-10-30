package br.ba.fvc.beans;

import br.ba.fvc.mapeamento.Aluguel;
import br.ba.fvc.mapeamento.Veiculo;
import br.ba.fvc.rn.AluguelRN;
import br.ba.fvc.rn.VeiculoRN;
import br.ba.fvc.util.FacesMessages;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "veiculoBean")
@SessionScoped
public class VeiculoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    VeiculoRN veiculoRN;
    AluguelRN aluguelRN;
    private Veiculo veiculo;
    private Veiculo veiculoSelecionado;
    private String termoPesquisa;
    private FacesMessages messages;
    private int qtdPortas;
    private List<Veiculo> listaVeiculos;

    public VeiculoBean() {
        veiculo = new Veiculo();
        popularDataTable();
    }

    public void prepararNovoVeiculo() {
        veiculo = new Veiculo();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public int getQtdPortas() {
        return qtdPortas;
    }

    public void setQtdPortas(int qtdPortas) {
        this.qtdPortas = qtdPortas;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public void salvar() {

        try {
            veiculoRN = new VeiculoRN();

            Veiculo veiculoExistente = veiculoRN.consultar(veiculo.getPlaca());

            if (veiculoExistente != null) {
                messages = new FacesMessages();
                messages.info("Já existe um veículo cadastrado com esta placa.");
                return;
            }

            veiculoRN.salvar(veiculo);
            this.listaVeiculos = veiculoRN.listarSemFiltro();
            veiculo = new Veiculo();

            messages = new FacesMessages();
            messages.info("Veículo cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar veículo");
        }
    }

    public void popularDataTable() {
        veiculoRN = new VeiculoRN();
        this.listaVeiculos = veiculoRN.listarSemFiltro();
    }

    public void pesquisar() {
        veiculoRN = new VeiculoRN();
        listaVeiculos = veiculoRN.pesquisar(termoPesquisa);

        if (listaVeiculos.isEmpty()) {
            messages = new FacesMessages();
            messages.info("Sua consulta não retornou registros.");
        }
    }

    public boolean isVeiculoSelecionadoSeleciona() {
        return veiculoSelecionado != null && veiculoSelecionado.getNumero() != null;
    }

    public void alterar() {
        try {
            veiculoRN = new VeiculoRN();
            veiculoRN.alterar(veiculoSelecionado);
            this.listaVeiculos = veiculoRN.listarSemFiltro();

            veiculoSelecionado = new Veiculo();

            messages = new FacesMessages();
            messages.info("Veículo alterado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao alterar veículo");

        }
    }

    public void excluir() {
        try {

            messages = new FacesMessages();

            aluguelRN = new AluguelRN();
            Aluguel aluguelExistente = aluguelRN.verificarSeVeiculoPossuiAluguelExistente(veiculoSelecionado.getNumero());

            if (aluguelExistente != null) {
                messages.info("Não é possivel excluir, pois veículo possui aluguel.");
                return;
            }

            veiculoRN = new VeiculoRN();
            veiculoRN.excluir(veiculoSelecionado);

            veiculoSelecionado = null;
            this.listaVeiculos = veiculoRN.listarSemFiltro();

            messages = new FacesMessages();
            messages.info("Veículo excluído com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao excluir veículo: " + e);
        }
    }

}
