package br.com.teste.service;

import br.com.teste.dao.EventoDao;
import java.sql.ResultSet;
import br.com.teste.model.Evento;

public class EventoService {

    private EventoDao eventoDao;

    public EventoService(){
        eventoDao = new EventoDao();
    }

    public ResultSet listar(){
        return  eventoDao.listar();
    }

    public boolean inserir(Evento evento){
        if (!validar(evento))
            return false;
        eventoDao.inserir(evento);
        return true;
    }

    public boolean excluir(Evento evento){
        if (evento.getIdEvento() == 0)
            return false;
        eventoDao.excluir(evento);
        return true;
    }

    public boolean editar(Evento evento){
        if (!validar(evento))
            return false;
        eventoDao.editar(evento);
        return true;
    }
    public boolean validar(Evento evento){
        if (evento.getNome() == null || evento.getTipo() == null || evento.getData == 0 || evento.getHora == 0 || evento.getDescricao == null)
            return false;

        if (evento.getNome().isEmpty() || evento.getTipo().isEmpty || evento.getData.isEmpty() || evento.getHora.isEmpty() || evento.getDescricao.isEmpty())            return false;

        return true;
    }
}