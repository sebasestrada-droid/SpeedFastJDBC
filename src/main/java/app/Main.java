package app;

import conexion.ConexionDB;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        ConexionDB.probarConexion();

        SwingUtilities.invokeLater(() -> {

            VentanaPrincipal ventana =
                    new VentanaPrincipal();

            ventana.setVisible(true);
        });
    }
}