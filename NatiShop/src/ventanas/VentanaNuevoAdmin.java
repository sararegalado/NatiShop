package ventanas;

import javax.swing.*;
import clases.Administrador;
import clases.Jornada;
import clases.Provincia;
import clases.Puesto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
        System.out.println("Agregando nuevo administrador...");

        String nombre = tfNombre.getText();
        String apellido = tfApellido.getText();
        String dni = tfDNI.getText();
        String correo = tfCorreo.getText();
        String telefono = tfTelefono.getText();
        String provincia = tfProvincia.getText();
        String fechaNacimientoStr = tfFechaNacimiento.getText();
        String fechaInicioStr = tfFechaInicio.getText();
        String jornada = tfJornada.getText();
        String puesto = tfPuesto.getText();
        char[] contraseña = pfContraseña.getPassword();

        if (dni.isEmpty() || nombre.isEmpty() || fechaNacimientoStr.isEmpty() || correo.isEmpty() || contraseña.length == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!correo.toLowerCase().endsWith("@natyshop.es")) {
        	JOptionPane.showMessageDialog(this, "El correo no es de un administrador", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        

        try {
        	
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
            Date fechaInicio = sdf.parse(fechaInicioStr);

            Administrador nuevoAdmin = new Administrador();
            nuevoAdmin.setDni(dni);
            nuevoAdmin.setNombre(nombre);
            nuevoAdmin.setApellido(apellido);
            nuevoAdmin.setCorreo(correo);
            nuevoAdmin.setTlf(telefono);
            nuevoAdmin.setProvincia(Provincia.valueOf(provincia));
            nuevoAdmin.setfNacStr(fechaNacimientoStr);
            nuevoAdmin.setFInicEmpresarstr(fechaInicioStr);
            nuevoAdmin.setJornadaLaboral(Jornada.valueOf(jornada));
            nuevoAdmin.setPuesto(Puesto.valueOf(puesto));
            nuevoAdmin.setContrasenia(new String(contraseña));
            
            guardarEnCSV(nuevoAdmin);

            setVisible(false);

            System.out.println("Administrador agregado correctamente");
        } catch (ParseException | IllegalArgumentException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al convertir las fechas o al asignar valores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarEnCSV(Administrador admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Administradores.csv", true))) {
            String lineaCSV = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                    admin.getDni(), admin.getNombre(), admin.getApellido(), admin.getCorreo(),
                    admin.getTlf(), admin.getProvincia(), admin.getfNacStr(),
                    admin.getFInicEmpresaStr(), admin.getJornadaLaboral(), admin.getPuesto(), admin.getContrasenia());
            writer.write(lineaCSV);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo CSV", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
