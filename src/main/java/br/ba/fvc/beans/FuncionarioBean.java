package br.ba.fvc.beans;

import br.ba.fvc.mapeamento.Funcionario;
import br.ba.fvc.rn.FuncionarioRN;
import br.ba.fvc.util.FacesMessages;
import br.ba.fvc.util.PasswordUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    FuncionarioRN funcionarioRN;
    private Funcionario funcionario;
    private Funcionario funcionarioSelecionado;
    private String termoPesquisa;
    private FacesMessages messages;
    private List<Funcionario> listaFuncionarios;

    public FuncionarioBean() {
        funcionario = new Funcionario();
        popularDataTable();

    }

    public void prepararNovoFuncionario() {
        funcionario = new Funcionario();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
        this.funcionarioSelecionado = funcionarioSelecionado;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public void salvar() {

        try {

            funcionarioRN = new FuncionarioRN();

            String hashedPassword = PasswordUtil.hashPassword(funcionario.getSenha());
            funcionario.setSenha(hashedPassword);

            funcionarioRN.salvar(funcionario);

            this.listaFuncionarios = funcionarioRN.listarSemFiltro();
            funcionario = new Funcionario();

            messages = new FacesMessages();
            messages.info("Funcionário cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao salvar funcionário.");
        }
    }

    public void popularDataTable() {
        funcionarioRN = new FuncionarioRN();
        this.listaFuncionarios = funcionarioRN.listarSemFiltro();
    }

    public void pesquisar() {

        funcionarioRN = new FuncionarioRN();
        listaFuncionarios = funcionarioRN.pesquisar(termoPesquisa);

        if (listaFuncionarios.isEmpty()) {
            messages = new FacesMessages();
            messages.info("Sua consulta não retornou registros.");
        }
    }

    public boolean isFuncionarioSelecionadoSeleciona() {
        return funcionarioSelecionado != null && funcionarioSelecionado.getId() != null;
    }

    public void alterar() {
        try {
            funcionarioRN = new FuncionarioRN();

            String hashedPassword = PasswordUtil.hashPassword(funcionarioSelecionado.getSenha());
            funcionarioSelecionado.setSenha(hashedPassword);

            funcionarioRN.alterar(funcionarioSelecionado);
            this.listaFuncionarios = funcionarioRN.listarSemFiltro();

            messages = new FacesMessages();
            messages.info("Funcionário alterado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao alterar funcionário");

        }
    }

    public void excluir() {
        try {
            funcionarioRN = new FuncionarioRN();
            funcionarioRN.excluir(funcionarioSelecionado);

            funcionarioSelecionado = null;
            this.listaFuncionarios = funcionarioRN.listarSemFiltro();

            messages = new FacesMessages();
            messages.info("Funcionário excluído com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao excluir funcionário");
        }
    }

}
