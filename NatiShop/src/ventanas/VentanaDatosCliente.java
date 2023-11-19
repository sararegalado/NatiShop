package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import clases.Usuario;

public class VentanaDatosCliente extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Usuario usuario;
	private JFrame vActual,vAnterior;

	public VentanaDatosCliente(JFrame va, String nombre, String direccion, String telefono, String contrasenia, String email, String wallet) {
		super();
		vActual = this;
		vAnterior = va;
		setTitle("Datos del Usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 600, 400);
		usuario = new Usuario();
	    
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(16, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("NOMBRE");	//NOMBRE no es editable se carga por defecto
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("DIRECCIÓN");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("TELEFONO");
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CONTRASEÑA");
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("EMAIL");
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setText("");
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("WALLET");
		panel.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField.setText(nombre);
	    //textField_1.setText(direccion);
	    //textField_2.setText(telefono);
	    textField_3.setText(contrasenia);
	    textField_4.setText(email);
	    //textField_5.setText(wallet);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnEliminarCuenta = new JButton("Eliminar cuenta");
		panel_1.add(btnEliminarCuenta);
		
		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		panel_1.add(btnCerrarSesion);
		
		JButton btnGuardar = new JButton("Guardar");
		panel_1.add(btnGuardar);
		
		//BOTONES
		btnEliminarCuenta.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(btnEliminarCuenta, "Estas seguro de que quieres elimiar tu cuenta", "Confirmacio", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					//VentanaDatosCliente ventanaDatosCliente = new VentanaDatosCliente();
					//ventanaDatosCliente.dispose();	MARIAN
				}
        	}
        });
				
		btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lógica para cerrar la sesión
                JOptionPane.showMessageDialog(null, "Sesión cerrada");
                VentanaDatosCliente ventanaDatosCliente = new VentanaDatosCliente(va);
				ventanaDatosCliente.dispose(); 
            }
        });
		

		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 guardarDatos();
			}
		});


		setVisible(true);
	}


	/*private void cargarDatosUsuario() {	//pero los quiero cargar desde la ventanaRegistro no desde usuario
		if (usuario != null) {
			textField.setText(usuario.getNombre());
			textField_3.setText(usuario.getContrasenia());
			textField_4.setText(usuario.getCorreo());
		} else {
			JOptionPane.showMessageDialog(null, "El usuario no ha introducido ningun dato");               
		}
	};*/
	
	private void guardarDatos() {
        //usuario.setContrasenia(textField_3.getText());
        //usuario.setCorreo(textField_4.getText());
		textField_1 = new JTextField();
        textField_2 = new JTextField();
        String direccion = textField_1.getText();
        String telefono = textField_2.getText();
        String contrasenia = textField_3.getText();
        String email = textField_4.getText();
        String wallet = textField_5.getText();               
        System.out.println("Datos guardados: " + direccion + telefono + contrasenia + email + wallet);
    };			

	
	/*public static void main1(String[] args) {
		 Usuario usuario = new Usuario();
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new VentanaDatosCliente(usuario);
	            }
	        });
	    }*/
    // DARLE A LA IMAGEN PERFIL y si esta iniciado sesion que te diriga a mi ventana
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main Frame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            VentanaDatosCliente ventanaDatosCliente = new VentanaDatosCliente(frame, "nombre", "direccion", "telefono", "contrasenia", "email", "wallet");

            frame.setVisible(true);
        });
    }

    
}
