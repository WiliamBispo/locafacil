package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Veiculo;
import java.util.List;

public interface VeiculoDAOListener {

    void alterar(Veiculo veiculo);

    Veiculo consultar(String placa);

    void excluir(Veiculo veiculo);

    List<Veiculo> listarSemFiltro();

    List<Veiculo> pesquisar(String placa);

    void salvar(Veiculo veiculo);

}
