package br.com.teste.dao;
import br.com.teste.config.Conexao;
import br.com.teste.model.Participante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipanteDao {

    private Conexao conexao;
    private PreparedStatement ps;


    public ParticipanteDao() {
        this.conexao = new Conexao();
    }

    public ResultSet listar() {
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM participante");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao listar participantes.");
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
            System.out.println("Erro ao inserir participante.");
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
            System.out.println("Erro ao excluir participante.");
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
            System.out.println("Erro ao editar participante.");
        }
    }
}