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
        messages = new FacesMessages();
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
                messages.info("Já existe um veículo cadastrado com esta placa.");
                return;
            }

            veiculoRN.salvar(veiculo);
            this.listaVeiculos = veiculoRN.listarSemFiltro();
            veiculo = new Veiculo();

            messages.info("Veículo cadastrado com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao cadastrar o veículo.");
            System.err.println("Erro ao salvar veículo: " + e.getMessage());
        }
    }

    public void popularDataTable() {
        veiculoRN = new VeiculoRN();
        this.listaVeiculos = veiculoRN.listarSemFiltro();
    }

    public void pesquisar() {
        try {
            veiculoRN = new VeiculoRN();
            listaVeiculos = veiculoRN.pesquisar(termoPesquisa);

            if (listaVeiculos.isEmpty()) {
                messages.info("Sua consulta não retornou registros.");
            }

        } catch (Exception e) {
            messages.error("Erro ao realizar a pesquisa.");
            System.err.println("Erro ao realizar a pesquisa: " + e.getMessage());
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

            messages.info("Veículo alterado com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao alterar o veículo.");
            System.err.println("Erro ao alterar veículo: " + e.getMessage());
        }
    }

    public void excluir() {
        try {

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

            messages.info("Veículo excluído com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao excluir o veículo.");
            System.err.println("Erro ao excluir veículo: " + e.getMessage());
        }
    }

}
