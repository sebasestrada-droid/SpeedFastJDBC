package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/speedfast_db?useSSL=false&serverTimezone=UTC";

    private static final String USUARIO = "root";

    private static final String PASSWORD = "MYSQL_PASSWORD";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
        );
    }

    public static void probarConexion() {

        try (Connection conexion = conectar()) {

            System.out.println("Conexión a MySQL exitosa.");

        } catch (SQLException e) {

            System.out.println("Error al conectar con MySQL.");
            System.out.println(e.getMessage());
        }
    }
}