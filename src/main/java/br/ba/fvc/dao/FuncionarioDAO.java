package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Funcionario;
import br.ba.fvc.mapeamento.Login;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class FuncionarioDAO implements FuncionarioDAOListener {

    private Session sessao;

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    @Override
    public void salvar(Funcionario funcionario) {
        this.sessao.save(funcionario);
    }

    @Override
    public List<Funcionario> listarSemFiltro() {
        return this.sessao.createCriteria(Funcionario.class).list();
    }

    @Override
    public List<Funcionario> pesquisar(String nome) {
        Criteria crit = this.sessao.createCriteria(Funcionario.class);
        crit.add(Restrictions.like("nome", "%" + nome + "%"));
        return (List<Funcionario>) crit.list();

    }

    @Override
    public void alterar(Funcionario funcionario) {
        this.sessao.update(funcionario);
    }

    @Override
    public void excluir(Funcionario funcionario) {
        this.sessao.delete(funcionario);
    }

    @Override
    public Funcionario consultarUsuario(Login login) {
        String hql = "select a from Funcionario a where a.login = :login and a.senha = :senha";
        Query consulta = this.sessao.createQuery(hql);
        consulta.setParameter("login", login.getLogin());
        consulta.setParameter("senha", login.getSenha());

        return (Funcionario) consulta.uniqueResult();
    }

}
