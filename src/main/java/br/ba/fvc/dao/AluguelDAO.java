package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Aluguel;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class AluguelDAO implements AluguelDAOListener {

    private Session sessao;

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    @Override
    public void salvar(Aluguel aluguel) {
        this.sessao.save(aluguel);
    }

    @Override
    public List<Aluguel> listarSemFiltro() {
        return this.sessao.createCriteria(Aluguel.class).list();
    }

    @Override
    public List<Aluguel> pesquisar(int documento) {
        Criteria crit = this.sessao.createCriteria(Aluguel.class);
        crit.add(Restrictions.eq("id", documento));
        return (List<Aluguel>) crit.list();
    }

    @Override
    public Aluguel consultar(int numeroVeiculo) {

        String sql = "SELECT a.* FROM aluguel a "
                + "JOIN veiculo v ON a.veiculo_id = v.numero "
                + "WHERE a.entregue = :statusEntregue AND v.numero = :numeroVeiculo";

        Query consulta = this.sessao.createSQLQuery(sql)
                .addEntity(Aluguel.class)
                .setParameter("numeroVeiculo", numeroVeiculo)
                .setParameter("statusEntregue", "N");

        return (Aluguel) consulta.uniqueResult();
    }

    @Override
    public void alterar(Aluguel aluguel) {
        this.sessao.update(aluguel);
    }

    @Override
    public void excluir(Aluguel aluguel) {
        this.sessao.delete(aluguel);
    }

    @Override
    public List<Aluguel> pesquisarAlugueisPorPeriodo(Date dataInicio, Date dataFim) {
        String hql = "select a from Aluguel a where a.dataAluguel >= :dataInicio and a.dataAluguel <= :dataFim";
        Query consulta = this.sessao.createQuery(hql);
        consulta.setDate("dataInicio", dataInicio);
        consulta.setDate("dataFim", dataFim);

        return (List<Aluguel>) consulta.list();
    }

    @Override
    public List<Aluguel> pesquisarAlugueisPendentes() {

        String hql = "SELECT a FROM Aluguel a WHERE :dataAtual > a.dataEntrega AND a.entregue = :statusEntregue";

        Query consulta = this.sessao.createQuery(hql);
        consulta.setParameter("dataAtual", new Date());
        consulta.setParameter("statusEntregue", 'N');

        List<Aluguel> results = (List<Aluguel>) consulta.list();

        return results;
    }

    @Override
    public List<Aluguel> consultarAluguelPendente(int documento) {

        String hql = "SELECT a FROM Aluguel a WHERE :dataAtual > a.dataEntrega AND a.entregue = :statusEntregue AND a.idAluguel = :documento ";

        Query consulta = this.sessao.createQuery(hql);
        consulta.setParameter("dataAtual", new Date());
        consulta.setParameter("statusEntregue", 'N');
        consulta.setParameter("documento", documento);

        List<Aluguel> results = (List<Aluguel>) consulta.list();

        return results;
    }
}
