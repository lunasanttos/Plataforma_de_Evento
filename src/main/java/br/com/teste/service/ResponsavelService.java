package br.com.teste.service;

import br.com.teste.dao.ResponsavelDao;
import java.sql.ResultSet;
import br.com.teste.model.Responsavel;

public class ResponsavelService {
    private ResponsavelDao responsavelDao;

    public ResponsavelService(){
        responsavelDao = new ResponsavelDao();
    }

    public ResultSet listar(){
        return  responsavelDao.listar();
    }

    public boolean inserir(Responsavel responsavel){
        if (!validar(responsavel))
            return false;
        responsavelDao.inserir(responsavel);
        return true;
    }

    public boolean excluir(Responsavel responsavel){
        if (responsavel.getIdResponsavel() == 0)
            return false;
        responsavelDao.excluir(responsavel);
        return true;
    }

    public boolean editar(Responsavel responsavel){
        if (!validar(responsavel))
            return false;
        responsavelDao.editar(responsavel);
        return true;
    }
    public boolean validar(Responsavel responsavel){
        if (responsavel.getNome() == null || responsavel.getEmail() == null)
            return false;

        if (responsavel.getNome().isEmpty() || responsavel.getEmail().isEmpty())
            return false;

        return true;
    }
}
