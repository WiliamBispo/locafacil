package br.ba.fvc.util;

import br.ba.fvc.dao.AluguelDAO;
import br.ba.fvc.dao.AluguelDAOListener;
import br.ba.fvc.dao.ClienteDAO;
import br.ba.fvc.dao.ClienteDAOListener;
import br.ba.fvc.dao.FuncionarioDAO;
import br.ba.fvc.dao.FuncionarioDAOListener;
import br.ba.fvc.dao.VeiculoDAO;
import br.ba.fvc.dao.VeiculoDAOListener;

public class FabricaDAO {

    public static ClienteDAOListener criarClienteDAO() {

        ClienteDAO clienteDao = new ClienteDAO();
        clienteDao.setSessao(HibernateUtil.getSessionFactory().getCurrentSession());
        return clienteDao;
    }

    public static FuncionarioDAOListener criarFuncionarioDAO() {

        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        funcionarioDao.setSessao(HibernateUtil.getSessionFactory().getCurrentSession());
        return funcionarioDao;
    }

    public static VeiculoDAOListener criarVeiculoDAO() {

        VeiculoDAO veiculoDao = new VeiculoDAO();
        veiculoDao.setSessao(HibernateUtil.getSessionFactory().getCurrentSession());
        return veiculoDao;
    }

    public static AluguelDAOListener criarAluguelDAO() {

        AluguelDAO aluguelDao = new AluguelDAO();
        aluguelDao.setSessao(HibernateUtil.getSessionFactory().getCurrentSession());
        return aluguelDao;
    }

}
