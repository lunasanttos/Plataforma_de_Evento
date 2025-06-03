package br.com.teste.dao;
import br.com.teste.config.Conexao;
import br.com.teste.model.Responsavel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ResponsavelDao {

    private Conexao conexao;
    private PreparedStatement ps;

    public ResponsavelDao(){
        conexao = new Conexao();
    }

    public ResultSet listar(){
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM responsavel");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao listar responsáveis.");
        }
        return null;
    }

    public void inserir(Responsavel responsavel){
        try {
            String SQL = "INSERT INTO responsavel(nome, email) VALUES (?, ?)";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setString(1, responsavel.getNome());
            ps.setString(2, responsavel.getEmail());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir responsável.");
        }
    }

    public void excluir(Responsavel responsavel){
        try {
            String SQL = "DELETE FROM responsavel WHERE id_responsavel = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, responsavel.getId_responsavel());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao excluir responsável.");
        }
    }

    public void editar(Responsavel responsavel){
        try {

            String SQL = "UPDATE responsavel SET nome = ?" + " email = ? " + "WHERE id_responsavel = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setString(1, responsavel.getNome());
            ps.setString(2, responsavel.getEmail());
            ps.setInt(3, responsavel.getId_responsavel());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao editar responsável.");
        }
    }
}