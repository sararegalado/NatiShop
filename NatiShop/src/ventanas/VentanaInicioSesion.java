package ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;

import clases.Administrador;
import clases.Articulo;
import clases.Cliente;
import clases.Tienda;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSpinner;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombre;
	private JPasswordField tfContrasena;
	
	
	private JFrame vActual,vAnterior;
	
	//Para guardar el usuario que Inicia Sesion y asignar el un carrito vacio que se ira llenando
	private static Cliente cliente;
	private static List<Articulo> carrito;
	
	
	
	public static Cliente getCliente() {
		return cliente;
	}


	public static void setCliente(Cliente cliente) {
		VentanaInicioSesion.cliente = cliente;
	}


	public static List<Articulo> getCarrito() {
		return carrito;
	}


	public static void setCarrito(List<Articulo> carrito) {
		VentanaInicioSesion.carrito = carrito;
	}


	public VentanaInicioSesion(JFrame va) {
		vActual = this;
		vAnterior = va;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,  778, 455);
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
		lblTitulo.setBounds(20, 11, 226, 23);
		lblTitulo.setFont(new Font("Baskerville", Font.PLAIN, 20));
		pnlInicioSesion.add(lblTitulo);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		pnlDatos.setBounds(33, 71, 183, 204);
		pnlInicioSesion.add(pnlDatos);
		pnlDatos.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre de usuario");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblNombre.setBounds(22, 24, 141, 16);
		pnlDatos.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(22, 51, 141, 26);
		pnlDatos.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfContrasena = new JPasswordField();
		tfContrasena.setBounds(22, 119, 141, 26);
		pnlDatos.add(tfContrasena);
		
		JButton btnIniciarSes = new JButton("Iniciar sesión");
		btnIniciarSes.setBounds(33, 157, 117, 29);
		pnlDatos.add(btnIniciarSes);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblContrasena.setBounds(50, 88, 79, 26);
		pnlDatos.add(lblContrasena);
		
		JButton btnAccederRegistro = new JButton("¿No tienes cuenta? Haz click aqui para registrarte");
		
		btnAccederRegistro.setBounds(202, 355, 334, 43);
		contentPane.add(btnAccederRegistro);
		
		//Cargamos los usuarios desde la clase tienda
		Tienda.cargarClientes(Tienda.getNomfichclientes());
		
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
//		if(Tienda.getmapaAdmin().containkey(nom) {
//			new VentanaAdmin();
//		}else {
//			new VentanaPrincipal();
//		}
		
		btnIniciarSes.addActionListener((e)->{
			String nom = tfNombre.getText();
			String con = tfContrasena.getText();
			
			
			if(Tienda.getAdministradores().containsKey(nom)) {
				Administrador admin  = Tienda.getAdministradores().get(nom);
				System.out.println(admin);
				new VentanaAdministrador(vActual,admin);
				vActual.setVisible(false);
				
				
				
			}else {
				if (Tienda.buscarClientePorNomCon(nom, con) != null) {
					Cliente c = Tienda.buscarClientePorNomCon(nom, con);
					JOptionPane.showMessageDialog(null, "Bienvenido!","SESIÓN INICIADA",JOptionPane.INFORMATION_MESSAGE);
					cliente = c; //Guardamos la información del cliente que ha iniciado sesión
					carrito = new ArrayList<>(); //Inicializamos su carrito a una lista vacía
					VentanaPrincipal.setClienteHaIniciadoSesion(true);
					VentanaPrincipal.asignarNombreCliente(c);
					vActual.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Para poder iniciar sesión tienes que estar registrado","ERROR",JOptionPane.ERROR_MESSAGE);
					tfNombre.setText("");
					tfContrasena.setText("");
				}
				
			}
			
			
			
		});
		
		//setAlwaysOnTop(rootPaneCheckingEnabled);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}

