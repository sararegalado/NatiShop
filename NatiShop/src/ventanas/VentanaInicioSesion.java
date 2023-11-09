package ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class VentanaInicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JPasswordField tfContrasena;
	
	private String nombreUsuario;
	
	private JFrame vActual,vAnterior;
	
	
	public VentanaInicioSesion(JFrame va) {
		vActual = this;
		vAnterior = va;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlInicioSesion = new JPanel();
		pnlInicioSesion.setBounds(238, 37, 267, 317);
		contentPane.add(pnlInicioSesion);
		pnlInicioSesion.setLayout(null);
		
		JLabel lblTitulo = new JLabel("ACCEDE A TU CUENTA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(17, 6, 226, 23);
		lblTitulo.setFont(new Font("Baskerville", Font.PLAIN, 20));
		pnlInicioSesion.add(lblTitulo);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		pnlDatos.setBounds(33, 71, 183, 204);
		pnlInicioSesion.add(pnlDatos);
		pnlDatos.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblEmail.setBounds(61, 22, 61, 16);
		pnlDatos.add(lblEmail);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(33, 51, 130, 26);
		pnlDatos.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblContrasena.setBounds(43, 89, 103, 16);
		pnlDatos.add(lblContrasena);
		
		tfContrasena = new JPasswordField();
		tfContrasena.setBounds(33, 119, 130, 26);
		pnlDatos.add(tfContrasena);
		
		JButton btnIniciarSes = new JButton("Iniciar sesión");
		btnIniciarSes.setBounds(33, 157, 117, 29);
		pnlDatos.add(btnIniciarSes);
		
		JButton btnAccederRegistro = new JButton("¿No tienes cuenta? Haz click aqui para registrarte");
		
		btnAccederRegistro.setBounds(202, 355, 334, 43);
		contentPane.add(btnAccederRegistro);
		
		btnAccederRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro(vActual);
				vActual.setVisible(false);
				
			}
		});

		
		
		/**
		 * Dependiendo si del dominio del correo del usuario se abrira una ventana o otra
		 * si el dominio del correo del usuario que inicia sesion es @Natyshop será un trabajador el que abrá iniciado sesion 
		 * y por lo tanto la ventana que se ejecutara no será la principal sino la Ventana Admin conn otras funciones distintas
		 */
//		nombreUsuario= textNom.getText();
//		if(nombreUsuario.indexOf("Natyshop")!= -1) {
//			new VentanaAdmin();
//		}else {
//			new VentanaPrincipal();
//		}
		
		setVisible(true);
	}
	
}
