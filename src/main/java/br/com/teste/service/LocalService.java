package br.com.teste.service;

import br.com.teste.dao.LocalDao;
import java.sql.ResultSet;
import br.com.teste.model.Local;

public class LocalService {

    private LocalDao localDao;

    public LocalService(){
        localDao = new LocalDao();
    }

    public ResultSet listar(){
        return  localDao.listar();
    }

    public boolean inserir(Local local){

        if (!validar(local))
            return false;

        localDao.inserir(local);
        return true;
    }

    public boolean excluir(Local local){

        if (local.getIdLocal() == 0)
            return false;

        localDao.excluir(local);
        return true;
    }

    public boolean editar(Local local){

        if (!validar(local))
            return false;

        localDao.editar(local);
        return true;
    }
    public boolean validar(Local local){

        if (local.getNome() == null || local.getEndereco == null || local.getCapacidade == 0)
            return false;

        if (local.getNome().isEmpty() || local.getendereco().isEmpty() || local.getCapacidade().isEmpty())
            return false;

        return true;
    }

}
