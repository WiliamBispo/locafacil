package br.ba.fvc.rn;

import br.ba.fvc.dao.VeiculoDAOListener;
import br.ba.fvc.mapeamento.Veiculo;
import br.ba.fvc.util.FabricaDAO;
import java.util.List;

public class VeiculoRN {

    private VeiculoDAOListener veiculoDao;

    public VeiculoRN() {
        this.veiculoDao = FabricaDAO.criarVeiculoDAO();
    }

    public void salvar(Veiculo veiculo) {
        this.veiculoDao.salvar(veiculo);
    }

    public List<Veiculo> listarSemFiltro() {
        return this.veiculoDao.listarSemFiltro();
    }

    public List<Veiculo> pesquisar(String numero) {
        return this.veiculoDao.pesquisar(numero);
    }

    public Veiculo consultar(String placa) {
        return this.veiculoDao.consultar(placa);
    }

    public void alterar(Veiculo veiculo) {
        this.veiculoDao.alterar(veiculo);
    }

    public void excluir(Veiculo veiculo) {
        this.veiculoDao.excluir(veiculo);
    }

}
