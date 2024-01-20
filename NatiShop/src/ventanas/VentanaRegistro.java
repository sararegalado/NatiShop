package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import clases.Cliente;
import clases.Provincia;

public class VentanaRegistro extends JFrame{
	
	private JLabel lblTitulo, lblNombre, lblDNI, lblEmail, lblFecha, lblTlf, lblProvincia, lblCon1, lblCon2, lblAtras;
	private JTextField tfNombre, tfDNI, tfEmail, tfTlf;
	private JComboBox<Provincia> cbProv;
	private JCalendar jcFecha;
	private JPanel pnlDatos, pnlDcha, pnlIzq, pnlFecha, pnlCombo, pnlBoton;
	private JPasswordField tfCon1, tfCon2;
	
	private JButton btnRegistro;
	
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private JFrame vActual,vAnterior;
	
	public VentanaRegistro(JFrame va){
		vActual = this;
		vAnterior = va;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		
		pnlDatos = new JPanel(new GridLayout(1, 2));
		getContentPane().add(pnlDatos, BorderLayout.CENTER);
		
		
		lblTitulo = new JLabel("DATOS PERSONALES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Baskerville", Font.PLAIN, 20));
		JPanel pnlDividir = new JPanel(new GridLayout(1, 3));
		
		lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(VentanaInicioSesion.class.getResource("/imagenes/volver.png")));
		
		pnlDividir.add(lblAtras);
		pnlDividir.add(lblTitulo);
		pnlDividir.add(new JLabel());
		
		
		getContentPane().add (pnlDividir, BorderLayout.NORTH);
		getContentPane().add(new JPanel(), BorderLayout.WEST);
		getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		getContentPane().add(new JPanel(), BorderLayout.EAST);
		
		pnlIzq = new JPanel(new GridLayout(12, 1));
		
		lblNombre = new JLabel("Nombre de usuario");
		lblDNI = new JLabel("DNI");
		lblEmail = new JLabel("Email");
		lblTlf = new JLabel ("Telefono");
		lblCon1 = new JLabel("Contraseña");
		lblCon2 = new JLabel("Confirmar la contraseña");
		
		tfNombre = new JTextField();
		tfDNI = new JTextField();
		tfEmail = new JTextField();
		tfTlf = new JTextField();
		tfCon1 = new JPasswordField();
		tfCon2 = new JPasswordField();
		
		pnlIzq.add(lblNombre);
		pnlIzq.add(tfNombre);
		pnlIzq.add(lblDNI);
		pnlIzq.add(tfDNI);
		pnlIzq.add(lblEmail);
		pnlIzq.add(tfEmail);
		pnlIzq.add(lblTlf);
		pnlIzq.add(tfTlf);
		pnlIzq.add(lblCon1);
		pnlIzq.add(tfCon1);
		pnlIzq.add(lblCon2);
		pnlIzq.add(tfCon2);
		
		pnlDatos.add(pnlIzq);
		
		pnlDcha = new JPanel(new GridLayout(3, 1));
		pnlFecha = new JPanel (new BorderLayout());
		pnlCombo = new JPanel();
		
		lblFecha = new JLabel("Selecciona tu fecha de nacimiento");
		jcFecha = new JCalendar(new Date());
		pnlFecha.add(lblFecha, BorderLayout.NORTH);
		pnlFecha.add(jcFecha, BorderLayout.CENTER);
		pnlDcha.add(pnlFecha);
		
		
		lblProvincia = new JLabel("Elige tu provincia");
		cbProv = new JComboBox<>(Provincia.values());
		pnlCombo.add(lblProvincia);
		pnlCombo.add(cbProv);
		pnlDcha.add(pnlCombo);
		
		pnlBoton = new JPanel(new BorderLayout());
		JPanel pnlCentrar = new JPanel(new GridLayout(1,3));
		btnRegistro = new JButton("REGISTRAR");
		pnlCentrar.add(new JPanel());
		pnlCentrar.add(btnRegistro);
		pnlCentrar.add(new JPanel());
		pnlBoton.add(pnlCentrar, BorderLayout.NORTH);
		
		pnlDcha.add(pnlBoton);
	
		pnlDatos.add(pnlDcha);
		
		//Tienda.cargarClientes(Tienda.getNomfichclientes());
		
		btnRegistro.addActionListener(e -> {
			Connection con = BD.initBD("NatiShop.db");
			String dni = tfDNI.getText();
			String nom = tfNombre.getText();
			String fNac = sdf.format(jcFecha.getDate());
			String email = tfEmail.getText();
			String tlf = tfTlf.getText();
			String prov = cbProv.getSelectedItem().toString();
			String con1 = tfCon1.getText();
			String con2 = tfCon2.getText();
			String numT = "Tarjeta sin registrar";
			double saldo = 0.0;
			
			if(!dni.equals("") && !nom.equals("") && !con1.equals("") && !con2.equals("") && fNac!=null) {
				if(con1.equals(con2)) {
					Cliente cl = BD.buscarCliente(con, dni);
					
					
					if(cl == null) { 
						//if(comprobarNombre()) {
							if(comprobarDni()) {
								if(letraDNICorrecta()) {
									if(comprobarEmail()) {
										if(comprobarTlf()) {
											Cliente c = new Cliente (dni, nom, fNac , email, tlf, prov, con1, numT, saldo);
											
											BD.insertarCliente(con, c);
											//Tienda.aniadirCliente(c);
											//Tienda.guardarClientes(Tienda.getNomfichclientes());
											JOptionPane.showMessageDialog(null, "Registro realizado con éxito");
											new VentanaInicioSesion(vActual);
											vActual.setVisible(false);
										}else {
											JOptionPane.showMessageDialog(null, "El telefono introducido no es correcto, deve tener al menos 9 numeros");
										}
						
									}else {
										JOptionPane.showMessageDialog(null, "El email introducido no es correcto");
									}
								}else {
									JOptionPane.showMessageDialog(null, "La letra del dni no es correcta");
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "El dni no es correcto");
							}
							
//						}else {
//							JOptionPane.showMessageDialog(null, "El nombre tiene que empezar por una letra mayúscula");
//						}
							
								
						
					}else {
						JOptionPane.showMessageDialog(null, "Ya existe este cliente","ERROR",JOptionPane.ERROR_MESSAGE);
						tfDNI.setText("");
						tfNombre.setText("");
						jcFecha.setDate(null);
						tfEmail.setText("");
						tfTlf.setText("");
						tfCon1.setText("");
						tfCon2.setText("");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Los valores de la contraseña deben coincidir","ERROR",JOptionPane.ERROR_MESSAGE);
					tfCon1.setText("");
					tfCon2.setText("");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos");
			}
			
			BD.closeBD(con);
			
		});
		
		lblAtras.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new VentanaInicioSesion(vActual);
				vActual.setVisible(false);

        		
        	}
        });
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/**
	 * Metodo que comprueba si la letra del DNI introducido en correcta
	 * @return Devuelve un bolean indicando si es correcta o no
	 */
	
	private boolean letraDNICorrecta() {
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		//      012345678
		//dni = 12345678Z
		String dni = tfDNI.getText();
		char letra = dni.charAt(8);
		long digitos = Long.parseLong(dni.substring(0, 8)); //Devuelve el String de 0 a 7
		int resto = (int) (digitos % 23);
		char letraCorrecta = letras.charAt(resto);
		return letra==letraCorrecta;
		
	}
	
	/**
	 * Metodo que coprueba si el DNI introducido es correcto
	 * @return Devuelve un bolean indicando si es correcto o no
	 */
	private boolean comprobarDni() {
		String patron = "[0-9]{8}[A-Z]";
		return Pattern.matches(patron, tfDNI.getText());
	}
	
//	/**
//	 * Metodo que comprueba si el nombre indicado sigue el patron indicado
//	 * @return Devuelve un bolean indicando si sigue el patron o no
//	 */
//	private boolean comprobarNombre() {
//		String patron = "[A-Za-z][0-9]{0,}";
//		return Pattern.matches(patron, tfNombre.getText());
//	}
	
	/**
	 * Metodo que comprueba si el email introducido es correcto
	 * @return Devuelve un bolean indicando si es correcto o no
	 */
	private boolean comprobarEmail() {
		String patron = "[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}.[a-z]{1,}";
		return Pattern.matches(patron, tfEmail.getText());
		
	}
	
	/**
	 * Metodo que comprueba si el numero de telefono introducido es correcto
	 * @return Devuelve un bolean indicando si es correcto o no
	 */
	private boolean comprobarTlf() {
		String patron = "[0-9]{9,}";
		return  Pattern.matches(patron, tfTlf.getText());
		
	}

}

