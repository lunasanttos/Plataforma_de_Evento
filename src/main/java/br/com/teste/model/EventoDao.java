package br.com.teste.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EventoDAO {
    
    private Conexao conexao;
    private PreparedStatement ps;

    public ResultSet listar() {
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM evento");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void inserir(Evento evento) {
        try {
            String SQL = "INSERT INTO evento(id_evento, nome, data_evento) VALUES (?, ?, ?)";
            ps = conexao.getConn().prepareStatement(SQL);
            
            ps.setInt(1, evento.getId_evento());
            ps.setString(2, evento.getNome());
            ps.setDate(3, Date.valueOf(evento.getData_evento()));
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void excluir(Evento evento) {
        try {
            String SQL = "DELETE FROM evento WHERE id_evento = ?";
            ps = conexao.getConn().prepareStatement(SQL);
            ps.setInt(1, evento.getId_evento());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void editar(Evento evento) {
        try {
            String SQL = "UPDATE evento SET nome = ?, data_evento = ? WHERE id_evento = ?";
            ps = conexao.getConn().prepareStatement(SQL);
            
            ps.setString(1, evento.getNome());
            ps.setDate(2, Date.valueOf(evento.getData_evento()));
            ps.setInt(3, evento.getId_evento());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}