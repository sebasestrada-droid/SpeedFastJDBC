package dao;

import conexion.ConexionDB;
import modelo.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean guardar(Pedido pedido) {

        String sql = """
                INSERT INTO pedido (direccion, tipo, estado)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conexion = ConexionDB.conectar();
                PreparedStatement statement =
                        conexion.prepareStatement(sql)
        ) {

            statement.setString(1, pedido.getDireccion());
            statement.setString(2, pedido.getTipo());
            statement.setString(3, pedido.getEstado());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al guardar pedido:");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = """
                SELECT id, direccion, tipo, estado
                FROM pedido
                ORDER BY id
                """;

        try (
                Connection conexion = ConexionDB.conectar();
                PreparedStatement statement =
                        conexion.prepareStatement(sql);
                ResultSet resultado =
                        statement.executeQuery()
        ) {

            while (resultado.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(
                        resultado.getInt("id")
                );

                pedido.setDireccion(
                        resultado.getString("direccion")
                );

                pedido.setTipo(
                        resultado.getString("tipo")
                );

                pedido.setEstado(
                        resultado.getString("estado")
                );

                pedidos.add(pedido);
            }

        } catch (SQLException e) {

            System.out.println("Error al listar pedidos:");
            System.out.println(e.getMessage());
        }

        return pedidos;
    }
}