package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Funcionario;
import java.util.List;

public interface FuncionarioDAOListener {

    void alterar(Funcionario funcionario);

    Funcionario consultarUsuario(String login);

    void excluir(Funcionario funcionario);

    List<Funcionario> listarSemFiltro();

    List<Funcionario> pesquisar(String nome);

    void salvar(Funcionario funcionario);

}
