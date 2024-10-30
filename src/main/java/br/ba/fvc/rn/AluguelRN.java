package br.ba.fvc.rn;

import br.ba.fvc.dao.AluguelDAOListener;
import br.ba.fvc.mapeamento.Aluguel;
import br.ba.fvc.util.FabricaDAO;
import java.util.Date;
import java.util.List;

public class AluguelRN {

    private AluguelDAOListener aluguelDao;

    public AluguelRN() {
        this.aluguelDao = FabricaDAO.criarAluguelDAO();
    }

    public void salvar(Aluguel aluguel) {
        this.aluguelDao.salvar(aluguel);
    }

    public List<Aluguel> listarSemFiltro() {
        return this.aluguelDao.listarSemFiltro();
    }

    public List<Aluguel> pesquisar(int documento) {
        return this.aluguelDao.pesquisar(documento);
    }

    public Aluguel consultar(int numeroVeiculo) {
        return this.aluguelDao.consultar(numeroVeiculo);
    }

    public void alterar(Aluguel aluguel) {
        this.aluguelDao.alterar(aluguel);
    }

    public void excluir(Aluguel aluguel) {
        this.aluguelDao.excluir(aluguel);
    }

    public List<Aluguel> pesquisarAlugueisPorPeriodo(Date dataInicio, Date dataFim) {
        return this.aluguelDao.pesquisarAlugueisPorPeriodo(dataInicio, dataFim);
    }

    public List<Aluguel> pesquisarAlugueisPendentes() {
        return this.aluguelDao.pesquisarAlugueisPendentes();
    }

    public List<Aluguel> consultarAluguelPendente(int documento) {
        return this.aluguelDao.consultarAluguelPendente(documento);
    }

    public Aluguel verificarSeClientePossuiAluguelExistente(String documento) {
        return this.aluguelDao.verificarSeClientePossuiAluguelExistente(documento);
    }
    
    public Aluguel verificarSeVeiculoPossuiAluguelExistente(int documento) {
        return this.aluguelDao.verificarSeVeiculoPossuiAluguelExistente(documento);
    }

}
