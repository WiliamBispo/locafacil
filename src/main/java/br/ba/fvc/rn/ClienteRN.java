package br.ba.fvc.rn;

import br.ba.fvc.dao.ClienteDAOListener;
import br.ba.fvc.mapeamento.Cliente;
import br.ba.fvc.util.FabricaDAO;
import java.util.List;

public class ClienteRN {

    private ClienteDAOListener clienteDao;

    public ClienteRN() {
        this.clienteDao = FabricaDAO.criarClienteDAO();
    }

    public void salvar(Cliente cliente) {
        this.clienteDao.salvar(cliente);
    }

    public List<Cliente> listarSemFiltro() {
        return this.clienteDao.listarSemFiltro();
    }

    public List<Cliente> pesquisar(String documento) {
        return this.clienteDao.pesquisar(documento);
    }

    public Cliente consultar(String documento) {
        return this.clienteDao.consultar(documento);
    }

    public void alterar(Cliente cliente) {
        this.clienteDao.alterar(cliente);
    }

    public void excluir(Cliente cliente) {
        this.clienteDao.excluir(cliente);
    }

}
