package br.com.teste.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipanteDAO {
    
    private Conexao conexao;
    private PreparedStatement ps;

    public ResultSet listar() {
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM participante");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void inserir(Participante participante) {
        try {
            String SQL = "INSERT INTO participante(id_participante, nome, cpf, email) VALUES (?, ?, ?, ?)";
            ps = conexao.getConn().prepareStatement(SQL);
            
            ps.setInt(1, participante.getId_participante());
            ps.setString(2, participante.getNome());
            ps.setString(3, participante.getCpf());
            ps.setString(4, participante.getEmail());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void excluir(Participante participante) {
        try {
            String SQL = "DELETE FROM participante WHERE id_participante = ?";
            ps = conexao.getConn().prepareStatement(SQL);
            ps.setInt(1, participante.getId_participante());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void editar(Participante participante) {
        try {
            String SQL = "UPDATE participante SET nome = ?, cpf = ?, email = ? WHERE id_participante = ?";
            ps = conexao.getConn().prepareStatement(SQL);
            
            ps.setString(1, participante.getNome());
            ps.setString(2, participante.getCpf());
            ps.setString(3, participante.getEmail());
            ps.setInt(4, participante.getId_participante());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}