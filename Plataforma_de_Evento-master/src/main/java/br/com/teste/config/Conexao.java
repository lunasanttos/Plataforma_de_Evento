package br.com.teste.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conn;

    public Conexao() {
        try {
            // Carregando o driver do MariaDB
            Class.forName("org.mariadb.jdbc.Driver");

            // Estabelecendo a conexão
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/plataforma_de_eventos", "root", "sistemas14");

            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Falha ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
    }
}
