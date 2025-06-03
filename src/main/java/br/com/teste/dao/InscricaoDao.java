package br.com.teste.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import br.com.teste.model.Inscricao;
import br.com.teste.model.Evento;
import br.com.teste.model.Participante;
import br.com.teste.config.Conexao;

public class InscricaoDao {

    private Conexao conexao;
    private PreparedStatement ps;
    private EventoDao eventoDao;
    private ParticipanteDao participanteDao;

    public InscricaoDao(){
        conexao = new Conexao();
        eventoDao = new EventoDao();
        participanteDao = new ParticipanteDao();
    }

    public ResultSet listar(){
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM inscricao");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao listar inscrições.");
        }
        return null;
    }

    public Inscricao buscarPorId(int id) {
        String SQL = "SELECT id_inscricao, id_evento, id_participante, data_inscricao FROM inscricao WHERE id_inscricao = ?";
        ResultSet rs = null;
        PreparedStatement psBuscar = null;

        try {
            psBuscar = conexao.getConn().prepareStatement(SQL);
            psBuscar.setInt(1, id);
            rs = psBuscar.executeQuery();

            if (rs.next()) {
                int idEvento = rs.getInt("id_evento");
                int idParticipante = rs.getInt("id_participante");
                LocalDate dataInscricao = rs.getDate("data_inscricao").toLocalDate();

                Evento evento = eventoDao.buscarPorId(idEvento);
                Participante participante = participanteDao.buscarPorId(idParticipante);

                return new Inscricao(
                        rs.getInt("id_inscricao"),
                        evento,
                        participante,
                        dataInscricao
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar inscrição por ID.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (psBuscar != null) psBuscar.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void inserir(Inscricao inscricao){
        try {
            String SQL = "INSERT INTO inscricao(id_evento, id_participante, data_inscricao) "+ " VALUES (?, ?, ?)";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, inscricao.getEvento().getId_evento());
            ps.setInt(2, inscricao.getParticipante().getId_participante());
            ps.setDate(3, Date.valueOf(inscricao.getDataInscricao()));

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir inscrição.");
        }
    }

    public void excluir(Inscricao inscricao){
        try {
            String SQL = "DELETE FROM inscricao WHERE id_inscricao = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, inscricao.getId_inscricao());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao excluir inscrição.");
        }
    }

    public void editar(Inscricao inscricao){
        try {
            String SQL = "UPDATE inscricao SET " +
                    "id_evento = ?, id_participante = ?, data_inscricao = ? " +
                    "WHERE id_inscricao = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, inscricao.getEvento().getId_evento());
            ps.setInt(2, inscricao.getParticipante().getId_participante());
            ps.setDate(3, Date.valueOf(inscricao.getDataInscricao()));
            ps.setInt(4, inscricao.getId_inscricao());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao editar inscrição.");
        }
    }
}