package br.ba.fvc.rn;

import br.ba.fvc.dao.FuncionarioDAOListener;
import br.ba.fvc.mapeamento.Funcionario;
import br.ba.fvc.util.FabricaDAO;
import java.util.List;

public class FuncionarioRN {

    private FuncionarioDAOListener funcionarioDao;

    public FuncionarioRN() {
        this.funcionarioDao = FabricaDAO.criarFuncionarioDAO();
    }

    public void salvar(Funcionario funcionario) {
        this.funcionarioDao.salvar(funcionario);
    }

    public List<Funcionario> listarSemFiltro() {
        return this.funcionarioDao.listarSemFiltro();
    }

    public List<Funcionario> pesquisar(String nome) {
        return this.funcionarioDao.pesquisar(nome);
    }

    public void alterar(Funcionario funcionario) {
        this.funcionarioDao.alterar(funcionario);
    }

    public void excluir(Funcionario funcionario) {
        this.funcionarioDao.excluir(funcionario);
    }

    public Funcionario consultarUsuario(String login) {
        return this.funcionarioDao.consultarUsuario(login);
    }

}
