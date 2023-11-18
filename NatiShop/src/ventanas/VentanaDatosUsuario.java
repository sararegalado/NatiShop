package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import clases.Usuario;

public class VentanaDatosUsuario extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private static Usuario usuario;
	
	public VentanaDatosUsuario(Usuario usuario2) {
		super();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 600, 400);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(16, 2, 0, 0));
		
		
		VentanaRegistro ventanaRegistro = null;
		JLabel lblNewLabel = new JLabel("NOMBRE: " + usuario.getNombre());	//NOMBRE no es editable se carga por defecto
		panel.add(lblNewLabel);
		
		/*textField = new JTextField(usuario.getNombre());
		panel.add(textField);
		textField.setColumns(10);*/
		
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
		
		textField_3 = new JTextField(usuario.getContrasenia());
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("EMAIL");
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField(usuario.getCorreo());
		textField_4.setText("");
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("WALLET");
		panel.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		panel.add(textField_5);
		textField_5.setColumns(10);
		
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
					VentanaDatosUsuario ventanaDatosCliente = new VentanaDatosUsuario(usuario2);
					ventanaDatosCliente.dispose();
				}
        	}
        });
		
		btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lógica para cerrar la sesión
                JOptionPane.showMessageDialog(null, "Sesión cerrada");
                VentanaDatosUsuario ventanaDatosCliente = new VentanaDatosUsuario(usuario2);
				ventanaDatosCliente.dispose(); 
            }
        });
		
		
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 guardarDatos();
			}
		});
		 
	        
		
		cargarDatosUsuario(usuario);
		
		setVisible(true);
	}

	private void cargarDatosUsuario(Usuario usuario2) {
		if (usuario != null) {
			//lblNewLabel.setText(usuario.getNombre());
			textField_3.setText(usuario.getContrasenia());
			textField_4.setText(usuario.getCorreo());
		} else {
			JOptionPane.showMessageDialog(null, "El usuario no ha introducido ningun dato");               
		}
	};
	
	private void guardarDatos() {
        //usuario.setContrasenia(textField_3.getText());
        //usuario.setCorreo(textField_4.getText());
        String direccion = textField_1.getText();
        String telefono = textField_2.getText();
        String contrasenia = textField_3.getText();
        String email = textField_4.getText();
        String wallet = textField_5.getText();               
        System.out.println("Datos guardados: " + direccion + telefono + contrasenia + email + wallet);
        //System.out.println("Datos guardados: " + usuario.getContrasenia() + usuario.getCorreo());
    };			

	
	public static void main1(String[] args) {
		 Usuario usuario = new Usuario();
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new VentanaDatosUsuario(usuario);
	            }
	        });
	    }
	
	public static void main(String[] args) {
		VentanaDatosUsuario VentanaDatosCliente = new VentanaDatosUsuario(usuario);
		
	}
}







