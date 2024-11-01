package br.ba.fvc.beans;

import br.ba.fvc.mapeamento.Aluguel;
import br.ba.fvc.mapeamento.Cliente;
import br.ba.fvc.mapeamento.Veiculo;
import br.ba.fvc.rn.AluguelRN;
import br.ba.fvc.rn.ClienteRN;
import br.ba.fvc.rn.VeiculoRN;
import br.ba.fvc.util.FacesMessages;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "aluguelBean")
@SessionScoped
public class AluguelBean implements Serializable {

    private static final long serialVersionUID = 1L;

    AluguelRN aluguelRN;
    VeiculoRN veiculoRN;
    ClienteRN clienteRN;
    private Aluguel aluguel;
    private Aluguel aluguelSelecionado;
    private String termoPesquisa;
    private FacesMessages messages;
    private List<Aluguel> listaAlugueis;
    private List<Veiculo> listaVeiculos;
    private List<Cliente> listaClientes;

    public AluguelBean() {
        aluguel = new Aluguel();
        messages = new FacesMessages();
        popularDataTable();
    }

    public void prepararNovoAluguel() {
        aluguel = new Aluguel();
        popularComboBoxVeiculos();
        popularComboBoxClientes();
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public Aluguel getAluguelSelecionado() {
        return aluguelSelecionado;
    }

    public void setAluguelSelecionado(Aluguel aluguelSelecionado) {
        this.aluguelSelecionado = aluguelSelecionado;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public List<Aluguel> getListaAlugueis() {
        return listaAlugueis;
    }

    public void setListaAlugueis(List<Aluguel> listaAlugueis) {
        this.listaAlugueis = listaAlugueis;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void salvar() {
        try {
            aluguelRN = new AluguelRN();

            Aluguel aluguelExistente = aluguelRN.consultar(aluguel.getVeiculo().getNumero());

            if (aluguelExistente != null) {
                messages.info("Esse veículo já está alugado e não pode ser alugado novamente!");
                return;
            }

            aluguelRN.salvar(aluguel);
            this.listaAlugueis = aluguelRN.listarSemFiltro();
            aluguel = new Aluguel();

            messages.info("Aluguel registrado com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao cadastrar o aluguel.");
            System.err.println("Erro ao salvar aluguel: " + e.getMessage());
        }
    }

    public void popularDataTable() {
        aluguelRN = new AluguelRN();
        this.listaAlugueis = aluguelRN.listarSemFiltro();
    }

    public void popularComboBoxVeiculos() {
        veiculoRN = new VeiculoRN();
        this.listaVeiculos = veiculoRN.listarSemFiltro();
    }

    public void popularComboBoxClientes() {
        clienteRN = new ClienteRN();
        this.listaClientes = clienteRN.listarSemFiltro();
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public void pesquisar() {
        aluguelRN = new AluguelRN();

        if (termoPesquisa != null && !termoPesquisa.trim().isEmpty()) {
            try {

                int termoPesquisarInt = Integer.parseInt(termoPesquisa);
                listaAlugueis = aluguelRN.pesquisar(termoPesquisarInt);

                if (listaAlugueis.isEmpty()) {
                    messages.info("Sua consulta não retornou registros.");
                }

            } catch (NumberFormatException e) {
                messages.info("O termo de pesquisa deve ser um número válido.");
            }
        } else {
            this.listaAlugueis = aluguelRN.listarSemFiltro();
        }
    }

    public boolean isAluguelSelecionadoSeleciona() {
        return aluguelSelecionado != null && aluguelSelecionado.getIdAluguel() != 0;
    }

    public void alterar() {
        try {
            aluguelRN = new AluguelRN();
            aluguelRN.alterar(aluguelSelecionado);
            this.listaAlugueis = aluguelRN.listarSemFiltro();

            messages.info("Aluguel alterado com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao alterar o aluguel.");
            System.err.println("Erro ao alterar aluguel: " + e.getMessage());
        }
    }

    public void excluir() {
        try {
            aluguelRN = new AluguelRN();
            aluguelRN.excluir(aluguelSelecionado);

            aluguelSelecionado = null;
            this.listaAlugueis = aluguelRN.listarSemFiltro();

            messages.info("Aluguel excluído com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao excluir o aluguel.");
            System.err.println("Erro ao excluir aluguel: " + e.getMessage());
        }
    }

}
