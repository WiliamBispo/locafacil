package br.ba.fvc.dao;

import br.ba.fvc.mapeamento.Cliente;
import java.util.List;

public interface ClienteDAOListener {

    void alterar(Cliente cliente);

    Cliente consultar(String documento);

    void excluir(Cliente cliente);

    List<Cliente> listarSemFiltro();

    List<Cliente> pesquisar(String documento);

    void salvar(Cliente cliente);

}
