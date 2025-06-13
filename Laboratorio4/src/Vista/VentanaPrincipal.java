package Vista;

import Modelo.Cliente;
import controlador.ClienteControlador;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private final ClienteControlador controlador;
    private JTextField txtCodigo, txtNombre, txtApellido, txtTelefono, txtCorreo, txtDireccion, txtPostal;
    private JTextArea areaResultados;

    public VentanaPrincipal() {
        controlador = new ClienteControlador(10);
        setTitle("Gestión de Clientes con Tabla Hash");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(350, 0));

        JPanel panelForm = new JPanel(new GridLayout(7, 2, 5, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        panelForm.setMaximumSize(new Dimension(350, 300));

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        txtDireccion = new JTextField();
        txtPostal = new JTextField();

        txtCodigo.setToolTipText("Código único del cliente");
        txtNombre.setToolTipText("Nombres completos del cliente (obligatorio)");
        txtApellido.setToolTipText("Apellidos del cliente (obligatorio)");
        txtTelefono.setToolTipText("Teléfono en formato +XX XXX XXXXXX");
        txtCorreo.setToolTipText("Correo electrónico válido");
        txtDireccion.setToolTipText("Dirección completa");
        txtPostal.setToolTipText("Código postal numérico");

        panelForm.add(new JLabel("Código:"));
        panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Nombres (*):"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Apellidos (*):"));
        panelForm.add(txtApellido);
        panelForm.add(new JLabel("Teléfono:"));
        panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Correo:"));
        panelForm.add(txtCorreo);
        panelForm.add(new JLabel("Dirección:"));
        panelForm.add(txtDireccion);
        panelForm.add(new JLabel("Código Postal:"));
        panelForm.add(txtPostal);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelBotones.setMaximumSize(new Dimension(350, 70));
        
        JButton btnInsertar = new JButton("Insertar Cliente");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnInsertar.setBackground(new Color(70, 130, 180));
        btnInsertar.setForeground(Color.WHITE);
        btnBuscar.setBackground(new Color(46, 139, 87));
        btnBuscar.setForeground(Color.WHITE);
        btnLimpiar.setBackground(new Color(105, 105, 105));
        btnLimpiar.setForeground(Color.WHITE);

        panelBotones.add(btnInsertar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        panelIzquierdo.add(panelForm);
        panelIzquierdo.add(panelBotones);
        panelIzquierdo.add(Box.createVerticalGlue());

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaResultados.setLineWrap(true);
        areaResultados.setWrapStyleWord(true);
        
        JScrollPane scrollResultados = new JScrollPane(areaResultados);
        scrollResultados.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Resultados de Operaciones"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        scrollResultados.setPreferredSize(new Dimension(500, 0));

        btnInsertar.addActionListener(e -> insertarCliente());
        btnBuscar.addActionListener(e -> buscarCliente());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(scrollResultados, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal);
    }

    private void insertarCliente() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        
        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarError("Los campos Nombre y Apellido son obligatorios");
            txtNombre.requestFocus();
            return;
        }

        try {
            Cliente cliente = new Cliente(
                    txtCodigo.getText(),
                    nombre,
                    apellido,
                    txtTelefono.getText(),
                    txtCorreo.getText(),
                    txtDireccion.getText(),
                    txtPostal.getText()
            );
            
            String comparacion = controlador.compararTiemposInsercion(cliente);
            
            StringBuilder resultado = new StringBuilder();
            resultado.append("══════════════════════════════════════════════════════════════\n");
            resultado.append("              COMPARACIÓN DE TIEMPOS DE INSERCIÓN\n");
            resultado.append("══════════════════════════════════════════════════════════════\n\n");
            resultado.append(comparacion);  
            
            areaResultados.setText(resultado.toString());
            mostrarExito("Cliente insertado exitosamente");
            
        } catch (Exception ex) {
            mostrarError("Error al insertar: " + ex.getMessage());
        }
    }

    private void buscarCliente() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        
        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarError("Ingrese nombre y apellido para buscar");
            txtNombre.requestFocus();
            return;
        }

        String nombreCompleto = nombre + " " + apellido;  

        try {
            Cliente resultadoLineal = controlador.buscarEnLineal(nombreCompleto);
            Cliente resultadoArbol = controlador.buscarEnArbol(nombreCompleto);
            
            StringBuilder resultado = new StringBuilder();
            resultado.append("══════════════════════════════════════════════════════════════\n");
            resultado.append("                  RESULTADOS DE BÚSQUEDA\n");
            resultado.append("══════════════════════════════════════════════════════════════\n\n");
            resultado.append("CLAVE BUSCADA: ").append(nombreCompleto).append("\n\n");
            
            resultado.append("────────────────── REASIGNACIÓN LINEAL ──────────────────\n");
            if (resultadoLineal != null) {
                resultado.append("Cliente encontrado:\n");
                resultado.append(resultadoLineal);
            } else {
                resultado.append("✖ Cliente no encontrado\n");
            }
            
            resultado.append("\n──────────────── ENCADENAMIENTO CON ÁRBOL ────────────────\n");
            if (resultadoArbol != null) {
                resultado.append("Cliente encontrado:\n");
                resultado.append(resultadoArbol);
            } else {
                resultado.append("✖ Cliente no encontrado\n");
            }
            
            resultado.append("\n════════════════════ COMPARACIÓN FINAL ════════════════════\n");
            if (resultadoLineal != null && resultadoArbol != null) {
                resultado.append("✔ Ambas técnicas encontraron el cliente");
            } else if (resultadoLineal != null) {
                resultado.append("✔ Solo reasignación lineal encontró el cliente");
            } else if (resultadoArbol != null) {
                resultado.append("✔ Solo encadenamiento con árbol encontró el cliente");
            } else {
                resultado.append("✖ Ninguna técnica encontró el cliente");
            }
            
            areaResultados.setText(resultado.toString());
            
        } catch (Exception ex) {
            mostrarError("Error en la búsqueda: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtPostal.setText("");
        areaResultados.setText("");
        txtNombre.requestFocus();
    }

 

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            new VentanaPrincipal();
        });
    }
}