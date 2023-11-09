package ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import clases.Usuario;
public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfDNI;
	private JTextField tfEmail;
	private JTextField tfFechaNac;
	private JPasswordField tfContrasena1;
	private JPasswordField tfContrasena2;
	
	private JFrame vActual,vAnterior;

	
	public VentanaRegistro(JFrame va) {
		vActual = this;
		vAnterior = va;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(254, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel pnlRegistro = new JPanel();
		pnlRegistro.setBackground(new Color(254, 255, 255));
		pnlRegistro.setForeground(new Color(254, 255, 255));
		pnlRegistro.setLayout(null);
		pnlRegistro.setBounds(145, 16, 434, 405);
		contentPane.add(pnlRegistro);
		
		JLabel lblCrearCuenta = new JLabel("CREAR CUENTA");
		lblCrearCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearCuenta.setFont(new Font("Baskerville", Font.PLAIN, 20));
		lblCrearCuenta.setBounds(111, 16, 226, 23);
		pnlRegistro.add(lblCrearCuenta);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		pnlDatos.setBounds(93, 44, 255, 300);
		pnlRegistro.add(pnlDatos);
		pnlDatos.setLayout(new GridLayout(12, 1, 0, 0));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Baskerville", Font.PLAIN, 15));
		pnlDatos.add(lblNombre);
		
		tfNombre = new JTextField();
		pnlDatos.add(tfNombre);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Baskerville", Font.PLAIN, 15));
		pnlDatos.add(lblDni);
		
		tfDNI = new JTextField();
		tfDNI.setColumns(10);
		pnlDatos.add(tfDNI);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Baskerville", Font.PLAIN, 15));
		pnlDatos.add(lblEmail);
		
		tfEmail = new JTextField();
		pnlDatos.add(tfEmail);
		
		JLabel lblFechaNac = new JLabel("Fecha de nacimiento (DD-MM-AA");
		lblFechaNac.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblFechaNac.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDatos.add(lblFechaNac);
		
		tfFechaNac = new JTextField();
		pnlDatos.add(tfFechaNac);
		
		JLabel lblContrasena1 = new JLabel("Crea una nueva contraseña");
		lblContrasena1.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena1.setFont(new Font("Baskerville", Font.PLAIN, 15));
		pnlDatos.add(lblContrasena1);
		
		tfContrasena1 = new JPasswordField();
		pnlDatos.add(tfContrasena1);
		
		JLabel lblContrasena2 = new JLabel("Confirma tu contraseña");
		lblContrasena2.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena2.setFont(new Font("Baskerville", Font.PLAIN, 15));
		pnlDatos.add(lblContrasena2);
		
		tfContrasena2 = new JPasswordField();
		pnlDatos.add(tfContrasena2);
		
		
		JButton btnRegistro = new JButton("Registrar cuenta");
		btnRegistro.setBounds(97, 356, 251, 24);
		pnlRegistro.add(btnRegistro);
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = tfDNI.getText();
				String nom = tfNombre.getText();
				String fNac = tfFechaNac.getText();
				String correo = tfEmail.getText();
				String con1 = tfContrasena1.getText();
				
				if (tfContrasena1.getText().equals(tfContrasena2.getText())) {
					Usuario u = new Usuario(dni, nom, fNac, correo, con1);
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Los valores de la contraseña deben coincidir","ERROR",JOptionPane.ERROR_MESSAGE);
					tfContrasena1.setText("");
					tfContrasena2.setText("");
				};
				
//				if(Tienda.buscarCliente(dni)!=null) {
//					JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese dni","ERROR",JOptionPane.ERROR_MESSAGE);
//				}else {
//					Tienda.aniadirCliente(c);
//					JOptionPane.showMessageDialog(null, "Cliente registrado con éxito","REGISTRADO",JOptionPane.INFORMATION_MESSAGE);
//				}
//			});
				
			}
		});
		
		setVisible(true);

	}
	
	
	
}
