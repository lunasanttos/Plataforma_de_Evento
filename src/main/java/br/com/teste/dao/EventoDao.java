package br.com.teste.dao;

import br.com.teste.model.Evento;
import br.com.teste.model.Local;
import br.com.teste.model.Responsavel;
import br.com.teste.config.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventoDao {

    private Conexao conexao;
    private PreparedStatement ps;
    private LocalDao localDao;

    public EventoDao() {
        this.conexao = new Conexao();
        this.localDao = new LocalDao();
    }

    public ResultSet listar() {
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM evento");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao listar eventos.");
        }
        return null;
    }

    public Evento buscarPorId(int id) {
        String SQL = "SELECT id_evento, nome, tipo, data, hora, descricao, id_local FROM evento WHERE id_evento = ?";
        ResultSet rs = null;
        PreparedStatement psBuscar = null;

        try {
            psBuscar = conexao.getConn().prepareStatement(SQL);
            psBuscar.setInt(1, id);
            rs = psBuscar.executeQuery();

            if (rs.next()) {
                int idLocal = rs.getInt("id_local");
                Local local = localDao.buscarPorId(idLocal);

                Evento evento = new Evento(
                        rs.getInt("id_evento"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("descricao"),
                        local
                );


                List<Responsavel> responsaveis = listarResponsaveisPorEvento(id);
                evento.setResponsavelLista(responsaveis);

                return evento;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar evento por ID.");
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

    private List<Responsavel> listarResponsaveisPorEvento(int idEvento) {
        List<Responsavel> lista = new ArrayList<>();
        String SQL = """
            SELECT r.id_responsavel, r.nome, r.email
            FROM responsavel r
            INNER JOIN evento_responsavel er ON r.id_responsavel = er.id_responsavel
            WHERE er.id_evento = ?
        """;

        try (PreparedStatement ps = conexao.getConn().prepareStatement(SQL)) {
            ps.setInt(1, idEvento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Responsavel responsavel = new Responsavel(
                        rs.getInt("id_responsavel"),
                        rs.getString("nome"),
                        rs.getString("email")
                );
                lista.add(responsavel);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar responsáveis do evento.");
        }

        return lista;
    }

    public int inserir(Evento evento) {
        int idGerado = 0;
        try {
            String SQL = "INSERT INTO evento(nome, tipo, data, hora, descricao, id_local) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            ps = conexao.getConn().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getTipo());
            ps.setDate(3, Date.valueOf(evento.getData()));
            ps.setTime(4, Time.valueOf(evento.getHora()));
            ps.setString(5, evento.getDescricao());
            ps.setInt(6, evento.getId_Local().getId_local());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
                evento.setId_evento(idGerado); // atualiza o evento com o id gerado
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir evento.");
        }
        return idGerado;
    }

    public void vincularResponsaveis(Evento evento) {
        if (evento.getResponsavelLista() == null || evento.getResponsavelLista().isEmpty()) {
            return; // Nenhum responsável para vincular
        }

        String sql = "INSERT INTO evento_responsavel (id_evento, id_responsavel) VALUES (?, ?)";

        try (PreparedStatement ps = conexao.getConn().prepareStatement(sql)) {
            for (Responsavel r : evento.getResponsavelLista()) {
                ps.setInt(1, evento.getId_evento());
                ps.setInt(2, r.getId_responsavel());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao vincular responsáveis ao evento.");
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
            System.out.println("Ocorreu um erro ao excluir evento.");
        }
    }

    public void editar(Evento evento) {
        try {
            String SQL = "UPDATE evento SET nome = ?, tipo = ?, data = ?, hora = ?, descricao = ?, id_local = ? " +
                    "WHERE id_evento = ?";
            ps = conexao.getConn().prepareStatement(SQL);

            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getTipo());
            ps.setDate(3, Date.valueOf(evento.getData()));
            ps.setTime(4, Time.valueOf(evento.getHora()));
            ps.setString(5, evento.getDescricao());
            ps.setInt(6, evento.getId_Local().getId_local());
            ps.setInt(7, evento.getId_evento());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ocorreu um erro ao editar evento.");
        }
    }
}
