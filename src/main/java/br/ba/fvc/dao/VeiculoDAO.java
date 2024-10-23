package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Veiculo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class VeiculoDAO implements VeiculoDAOListener {

    private Session sessao;

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    @Override
    public void salvar(Veiculo veiculo) {
        this.sessao.save(veiculo);
    }

    @Override
    public List<Veiculo> listarSemFiltro() {
        return this.sessao.createCriteria(Veiculo.class).list();
    }

    @Override
    public List<Veiculo> pesquisar(String placa) {
        Criteria crit = this.sessao.createCriteria(Veiculo.class);
        crit.add(Restrictions.like("placa", "%" + placa + "%"));
        return (List<Veiculo>) crit.list();
    }

    @Override
    public Veiculo consultar(String placa) {
        String hql = "FROM Veiculo v WHERE v.placa = :placa";
        Query query = this.sessao.createQuery(hql);
        query.setParameter("placa", placa);
        return (Veiculo) query.uniqueResult();
    }

    @Override
    public void alterar(Veiculo veiculo) {
        this.sessao.update(veiculo);
    }

    @Override
    public void excluir(Veiculo veiculo) {
        this.sessao.delete(veiculo);
    }
}
