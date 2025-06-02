package br.com.teste.model;

import java.time.LocalDate;

public class Inscricao {
    private int id_inscricao;
    private Evento evento;
    private Participante participante;
    private LocalDate dataInscricao;


    public int getId_inscricao() {
        return id_inscricao;
    }
    public void setId_inscricao(int id_inscricao) {
        this.id_inscricao = id_inscricao;
    }

    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }
    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }
    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}
