package ventanas;

import javax.swing.*;

import clases.Administrador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VentanaNuevoAdmin extends JFrame {
    private JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfDNI;
    private JTextField tfCorreo;
    private JTextField tfTelefono;
    private JTextField tfProvincia;
    private JTextField tfFechaNacimiento;
    private JTextField tfFechaInicio;
    private JTextField tfJornada;
    private JTextField tfPuesto;
    private JPasswordField pfContraseña;

    public VentanaNuevoAdmin(JFrame vActual) {

        tfNombre = new JTextField();
        tfApellido = new JTextField();
        tfDNI = new JTextField();
        tfCorreo = new JTextField();
        tfTelefono = new JTextField();
        tfProvincia = new JTextField();
        tfFechaNacimiento = new JTextField();
        tfFechaInicio = new JTextField();
        tfJornada = new JTextField();
        tfPuesto = new JTextField();
        pfContraseña = new JPasswordField();

        setLayout(new GridLayout(12, 2));

        add(new JLabel("Nombre:"));
        add(tfNombre);
        add(new JLabel("Apellido:"));
        add(tfApellido);
        add(new JLabel("DNI:"));
        add(tfDNI);
        add(new JLabel("Correo:"));
        add(tfCorreo);
        add(new JLabel("Teléfono:"));
        add(tfTelefono);
        add(new JLabel("Provincia:"));
        add(tfProvincia);
        add(new JLabel("Fecha de Nacimiento:"));
        add(tfFechaNacimiento);
        add(new JLabel("Fecha de Inicio:"));
        add(tfFechaInicio);
        add(new JLabel("Jornada:"));
        add(tfJornada);
        add(new JLabel("Puesto:"));
        add(tfPuesto);
        add(new JLabel("Contraseña:"));
        add(pfContraseña);

        JButton btnAgregar = new JButton("Agregar Administrador");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoAdmin();
            }
        });

        add(btnAgregar);

        pack();
        setLocationRelativeTo(null);
    }

    private void agregarNuevoAdmin() {
        dispose();
    }

    private void guardarEnCSV(Administrador admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("administradores.csv", true))) {
            String lineaCSV = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                    admin.getDni(), admin.getNombre(), admin.getApellido(), admin.getCorreo(),
                    admin.getTlf(), admin.getProvincia(), admin.getfNac(),
                    admin.getFInicEmpresa(), admin.getJornadaLaboral(), admin.getPuesto(), admin.getContrasenia());
            writer.write(lineaCSV);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

