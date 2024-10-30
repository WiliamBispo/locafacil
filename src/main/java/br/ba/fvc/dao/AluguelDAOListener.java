package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Aluguel;
import java.util.Date;
import java.util.List;

public interface AluguelDAOListener {

    void alterar(Aluguel aluguel);

    Aluguel consultar(int numeroVeiculo);

    List<Aluguel> consultarAluguelPendente(int documento);

    void excluir(Aluguel aluguel);

    List<Aluguel> listarSemFiltro();

    List<Aluguel> pesquisar(int documento);

    List<Aluguel> pesquisarAlugueisPendentes();

    List<Aluguel> pesquisarAlugueisPorPeriodo(Date dataInicio, Date dataFim);

    void salvar(Aluguel aluguel);

    Aluguel verificarSeClientePossuiAluguelExistente(String documento);

    Aluguel verificarSeVeiculoPossuiAluguelExistente(int documento);

}
