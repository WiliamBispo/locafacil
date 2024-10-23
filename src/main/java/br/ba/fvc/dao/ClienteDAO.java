package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Cliente;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ClienteDAO implements ClienteDAOListener {

    private Session sessao;

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    @Override
    public void salvar(Cliente cliente) {
        this.sessao.save(cliente);
    }

    @Override
    public List<Cliente> listarSemFiltro() {
        return this.sessao.createCriteria(Cliente.class).list();
    }

    @Override
    public List<Cliente> pesquisar(String documento) {
        Criteria crit = this.sessao.createCriteria(Cliente.class);
        crit.add(Restrictions.like("cpf", "%" + documento + "%"));
        return (List<Cliente>) crit.list();

    }

    @Override
    public Cliente consultar(String documento) {
        return (Cliente) this.sessao.get(Cliente.class, documento);
    }

    @Override
    public void alterar(Cliente cliente) {
        this.sessao.update(cliente);
    }

    @Override
    public void excluir(Cliente cliente) {
        this.sessao.delete(cliente);
    }

}
