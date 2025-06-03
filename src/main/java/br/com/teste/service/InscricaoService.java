package br.com.teste.service;

import br.com.teste.dao.InscricaoDao;
import java.sql.ResultSet;
import br.com.teste.model.Inscricao;

public class InscricaoService {

    private InscricaoDao inscricaoDao;

    public IntroducaoService(){
        inscricaoDao = new InscricaoDao();
    }

    public ResultSet listar(){
        return  inscricaoDao.listar();
    }

    public boolean inserir(Inscricao inscricao){
        if (!validar(inscricao))
            return false;
        inscricaoDao.inserir(inscricao);
        return true;
    }

    public boolean excluir(Inscricao inscricao){
        if (inscricao.getIdInscricao() == 0)
            return false;
        inscricaoDao.excluir(inscricao);
        return true;
    }

    public boolean editar(Inscricao inscricao){
        if (!validar(inscricao))
            return false;
        inscricaoDao.editar(inscricao);
        return true;
    }
    public boolean validar(Inscricao inscricao){
        if (inscricao.getDataInscricao() == 0)
            return false;

        if (inscricao.getDataInscricao.isEmpty())
            return false;

        return true;
    }
}
