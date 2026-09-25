package vista;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private final PedidoDAO pedidoDAO =
            new PedidoDAO();

    private final RepartidorDAO repartidorDAO =
            new RepartidorDAO();

    private final EntregaDAO entregaDAO =
            new EntregaDAO();

    private JTextField txtDireccion;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboEstado;

    private JTextField txtNombreRepartidor;

    private JTextField txtIdPedido;
    private JTextField txtIdRepartidor;

    private JTable tablaPedidos;

    private DefaultTableModel modeloTabla;

    public VentanaPrincipal() {

        setTitle("SpeedFast - Gestión de pedidos");

        setSize(850, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        crearInterfaz();
    }

    private void crearInterfaz() {

        JTabbedPane pestanas =
                new JTabbedPane();

        pestanas.addTab(
                "Pedidos",
                crearPanelPedidos()
        );

        pestanas.addTab(
                "Repartidores",
                crearPanelRepartidores()
        );

        pestanas.addTab(
                "Entregas",
                crearPanelEntregas()
        );

        pestanas.addTab(
                "Consultar pedidos",
                crearPanelConsulta()
        );

        add(pestanas);
    }

    private JPanel crearPanelPedidos() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel lblDireccion =
                new JLabel("Dirección:");

        txtDireccion =
                new JTextField(20);

        JLabel lblTipo =
                new JLabel("Tipo:");

        comboTipo =
                new JComboBox<>(
                        new String[]{
                                "COMIDA",
                                "ENCOMIENDA",
                                "EXPRESS"
                        }
                );

        JLabel lblEstado =
                new JLabel("Estado:");

        comboEstado =
                new JComboBox<>(
                        new String[]{
                                "PENDIENTE",
                                "EN_REPARTO",
                                "ENTREGADO"
                        }
                );

        JButton btnGuardar =
                new JButton("Guardar pedido");

        btnGuardar.addActionListener(
                e -> guardarPedido()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(
                lblDireccion,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                txtDireccion,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                lblTipo,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                comboTipo,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                lblEstado,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                comboEstado,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 3;

        panel.add(
                btnGuardar,
                gbc
        );

        return panel;
    }

    private void guardarPedido() {

        String direccion =
                txtDireccion
                        .getText()
                        .trim();

        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una dirección."
            );

            return;
        }

        String tipo =
                comboTipo
                        .getSelectedItem()
                        .toString();

        String estado =
                comboEstado
                        .getSelectedItem()
                        .toString();

        Pedido pedido =
                new Pedido(
                        direccion,
                        tipo,
                        estado
                );

        boolean guardado =
                pedidoDAO.guardar(pedido);

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido guardado correctamente."
            );

            txtDireccion.setText("");

            cargarPedidos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible guardar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private JPanel crearPanelRepartidores() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel lblNombre =
                new JLabel("Nombre:");

        txtNombreRepartidor =
                new JTextField(20);

        JButton btnGuardar =
                new JButton(
                        "Guardar repartidor"
                );

        btnGuardar.addActionListener(
                e -> guardarRepartidor()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(
                lblNombre,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                txtNombreRepartidor,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(
                btnGuardar,
                gbc
        );

        return panel;
    }

    private void guardarRepartidor() {

        String nombre =
                txtNombreRepartidor
                        .getText()
                        .trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el nombre del repartidor."
            );

            return;
        }

        Repartidor repartidor =
                new Repartidor(nombre);

        boolean guardado =
                repartidorDAO.guardar(
                        repartidor
                );

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor guardado correctamente."
            );

            txtNombreRepartidor.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible guardar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private JPanel crearPanelEntregas() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel lblPedido =
                new JLabel("ID del pedido:");

        txtIdPedido =
                new JTextField(15);

        JLabel lblRepartidor =
                new JLabel("ID del repartidor:");

        txtIdRepartidor =
                new JTextField(15);

        JButton btnGuardar =
                new JButton(
                        "Registrar entrega"
                );

        btnGuardar.addActionListener(
                e -> guardarEntrega()
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(
                lblPedido,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                txtIdPedido,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                lblRepartidor,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                txtIdRepartidor,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(
                btnGuardar,
                gbc
        );

        return panel;
    }

    private void guardarEntrega() {

        try {

            int idPedido =
                    Integer.parseInt(
                            txtIdPedido
                                    .getText()
                                    .trim()
                    );

            int idRepartidor =
                    Integer.parseInt(
                            txtIdRepartidor
                                    .getText()
                                    .trim()
                    );

            Entrega entrega =
                    new Entrega(
                            idPedido,
                            idRepartidor,
                            LocalDate.now(),
                            LocalTime.now()
                    );

            boolean guardado =
                    entregaDAO.guardar(
                            entrega
                    );

            if (guardado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Entrega registrada correctamente."
                );

                txtIdPedido.setText("");

                txtIdRepartidor.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar la entrega.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Los ID deben ser números.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private JPanel crearPanelConsulta() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("ID");

        modeloTabla.addColumn(
                "Dirección"
        );

        modeloTabla.addColumn(
                "Tipo"
        );

        modeloTabla.addColumn(
                "Estado"
        );

        tablaPedidos =
                new JTable(modeloTabla);

        JButton btnActualizar =
                new JButton(
                        "Actualizar pedidos"
                );

        btnActualizar.addActionListener(
                e -> cargarPedidos()
        );

        panel.add(
                new JScrollPane(tablaPedidos),
                BorderLayout.CENTER
        );

        panel.add(
                btnActualizar,
                BorderLayout.SOUTH
        );

        cargarPedidos();

        return panel;
    }

    private void cargarPedidos() {

        if (modeloTabla == null) {
            return;
        }

        modeloTabla.setRowCount(0);

        List<Pedido> pedidos =
                pedidoDAO.listarTodos();

        for (Pedido pedido : pedidos) {

            modeloTabla.addRow(
                    new Object[]{
                            pedido.getId(),
                            pedido.getDireccion(),
                            pedido.getTipo(),
                            pedido.getEstado()
                    }
            );
        }
    }
}