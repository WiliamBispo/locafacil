package br.ba.fvc.beans;

import br.ba.fvc.mapeamento.Aluguel;
import br.ba.fvc.mapeamento.Cliente;
import br.ba.fvc.rn.AluguelRN;
import br.ba.fvc.rn.ClienteRN;
import br.ba.fvc.util.FacesMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    ClienteRN clienteRN;
    AluguelRN aluguelRN;
    private Cliente cliente;
    private Cliente clienteSelecionado;
    private String termoPesquisa;
    private FacesMessages messages;
    private List<Cliente> listaClientes;
    private List<String> listaUFs;

    public ClienteBean() {
        cliente = new Cliente();
        messages = new FacesMessages();
        popularDataTable();
        carregarUFs();
    }

    public void prepararNovoCliente() {
        cliente = new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<String> getListaUFs() {
        return listaUFs;
    }

    public void setListaUFs(List<String> listaUFs) {
        this.listaUFs = listaUFs;
    }

    public void salvar() {
        try {
            clienteRN = new ClienteRN();

            Cliente clienteExistente = clienteRN.consultar(cliente.getCpf());

            if (clienteExistente != null) {
                messages.info("Já existe um cliente cadastrado com este CPF.");
                return;
            }

            clienteRN.salvar(cliente);
            this.listaClientes = clienteRN.listarSemFiltro();
            cliente = new Cliente();

            messages.info("Cliente cadastrado com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao cadastrar o cliente.");
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public void popularDataTable() {
        clienteRN = new ClienteRN();
        this.listaClientes = clienteRN.listarSemFiltro();
    }

    public void pesquisar() {
        try {
            clienteRN = new ClienteRN();
            listaClientes = clienteRN.pesquisar(termoPesquisa);

            if (listaClientes.isEmpty()) {
                messages.info("Sua consulta não retornou registros.");
            }

        } catch (Exception e) {
            messages.error("Erro ao realizar a pesquisa.");
            System.err.println("Erro ao realizar a pesquisa: " + e.getMessage());
        }
    }

    public boolean isClienteSelecionadoSeleciona() {
        return clienteSelecionado != null && clienteSelecionado.getCpf() != null;
    }

    public void alterar() {
        try {
            clienteRN = new ClienteRN();
            clienteRN.alterar(clienteSelecionado);
            this.listaClientes = clienteRN.listarSemFiltro();

            messages.info("Cliente alterado com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao alterar o cliente.");
            System.err.println("Erro ao alterar cliente: " + e.getMessage());
        }
    }

    public void excluir() {
        try {

            aluguelRN = new AluguelRN();
            Aluguel aluguelExistente = aluguelRN.verificarSeClientePossuiAluguelExistente(clienteSelecionado.getCpf());

            if (aluguelExistente != null) {
                messages.info("Não é possivel excluir, pois cliente possui aluguel.");
                return;
            }

            clienteRN = new ClienteRN();
            clienteRN.excluir(clienteSelecionado);

            clienteSelecionado = null;
            this.listaClientes = clienteRN.listarSemFiltro();

            messages.info("Cliente excluído com sucesso!");

        } catch (Exception e) {
            messages.error("Erro ao excluir o cliente.");
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    public void carregarUFs() {
        listaUFs = new ArrayList<>();
        listaUFs.add("AC");
        listaUFs.add("AL");
        listaUFs.add("AM");
        listaUFs.add("AP");
        listaUFs.add("BA");
        listaUFs.add("CE");
        listaUFs.add("DF");
        listaUFs.add("ES");
        listaUFs.add("GO");
        listaUFs.add("MA");
        listaUFs.add("MT");
        listaUFs.add("MS");
        listaUFs.add("MG");
        listaUFs.add("PA");
        listaUFs.add("PB");
        listaUFs.add("PR");
        listaUFs.add("PE");
        listaUFs.add("PI");
        listaUFs.add("RJ");
        listaUFs.add("RN");
        listaUFs.add("RS");
        listaUFs.add("RO");
        listaUFs.add("RR");
        listaUFs.add("SC");
        listaUFs.add("SP");
        listaUFs.add("SE");
        listaUFs.add("TO");
    }

}
