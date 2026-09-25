package dao;

import conexion.ConexionDB;
import modelo.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public boolean guardar(Repartidor repartidor) {

        String sql = """
                INSERT INTO repartidor (nombre)
                VALUES (?)
                """;

        try (
                Connection conexion = ConexionDB.conectar();
                PreparedStatement statement =
                        conexion.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    repartidor.getNombre()
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar repartidor:"
            );

            System.out.println(e.getMessage());

            return false;
        }
    }

    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores =
                new ArrayList<>();

        String sql = """
                SELECT id, nombre
                FROM repartidor
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

                int id =
                        resultado.getInt("id");

                String nombre =
                        resultado.getString("nombre");

                Repartidor repartidor =
                        new Repartidor(id, nombre);

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar repartidores:"
            );

            System.out.println(e.getMessage());
        }

        return repartidores;
    }
}