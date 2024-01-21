package ventanas;

import javax.swing.*;
import javax.swing.border.LineBorder;

import clases.Cliente;
import clases.Tienda;
import clases.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class VentanaDatosUsuario extends JFrame{
	private static JPanel pnlDatos ;
	private JPanel pnlIzq, pnlCentro, pnlDcha, pnlDchaCtro ;

	private JLabel lblTitulo, lblNombre, lblAtras, lblEmail, lblTlf, lblCon;
	private static JButton btnCorreo, btnTfn, btnContrasenia;
	private static JButton btnModificar;
	private JLabel lblCerrar, lblElim, lblTarj;
	private JTextField tfEmail;
	private static int intModif = 0;
	private JFrame vActual, vAnterior;
	
	private static JTextField tfActual, tfNuevo;
	JTextField tfNumTarj, tftitular, tfCVV;
	private static JPasswordField jpActual, jpNueva;
	
	private boolean panelVisible = false;
	private boolean elementoAgregado = false;
	
	
	public VentanaDatosUsuario(JFrame va, Cliente c) {
		vActual = this;
		vAnterior = va;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,  778, 455);
		
		pnlDatos = new JPanel(new GridLayout(1, 5));
		getContentPane().add(pnlDatos, BorderLayout.CENTER);
		
		
		lblTitulo = new JLabel("TU CUENTA");
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
		
		lblNombre = new JLabel (c.getNombre());
		lblEmail = new JLabel("EMAIL");

		btnCorreo = new JButton(c.getCorreo());
		btnCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		btnCorreo.setBackground(new Color(220, 220, 220));
		btnCorreo.setBorder(new LineBorder(Color.BLACK));

		lblTlf = new JLabel ("TELÉFONO");
		btnTfn = new JButton(c.getTlf());
		btnTfn.setHorizontalAlignment(SwingConstants.LEFT);
		btnTfn.setBackground(new Color(220, 220, 220));
		btnTfn.setBorder(new LineBorder(Color.BLACK));

		lblCon = new JLabel("CONTRASEÑA");
		btnContrasenia = new JButton("*".repeat(c.getContrasenia().length()));
		btnContrasenia.setHorizontalAlignment(SwingConstants.LEFT);
		btnContrasenia.setBackground(new Color(220, 220, 220));
		btnContrasenia.setBorder(new LineBorder(Color.BLACK));

		
		lblCerrar = new JLabel("<html><u>" + "Cerrar sesión" + "</u></html>");
		lblCerrar.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		lblElim = new JLabel("<html><u>" + "Eliminar cuenta" + "</u></html>");
		lblElim.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		
		pnlIzq.add(new JPanel());
		pnlIzq.add(lblNombre);
		pnlIzq.add(lblEmail);
		pnlIzq.add(btnCorreo);
		pnlIzq.add(lblTlf);
		pnlIzq.add(btnTfn);
		pnlIzq.add(lblCon);
		pnlIzq.add(btnContrasenia);
		
		pnlIzq.add(new JPanel());
		pnlIzq.add(lblCerrar);
		pnlIzq.add(lblElim);
		pnlIzq.add(new JPanel());
		
		pnlDatos.add(pnlIzq);
		
		
		pnlCentro = new JPanel(new BorderLayout());

		btnCorreo.addActionListener(e -> {
			intModif = 1;
			pnlCentro.removeAll();
			agregarCampos(pnlCentro, "EMAIL ", c);
			pnlCentro.revalidate();
			pnlCentro.repaint();
			visibilidad();
		});
		
		btnTfn.addActionListener(e -> {
			intModif = 2;
			pnlCentro.removeAll();
			agregarCampos(pnlCentro, "TELEFONO ", c);
			pnlCentro.revalidate();
			pnlCentro.repaint();
			visibilidad();
		});
		
		btnContrasenia.addActionListener(e -> {
			intModif = 3;
			pnlCentro.removeAll();
			agregarCampos(pnlCentro, "CONTRASEÑA ", c);
			pnlCentro.revalidate();
			pnlCentro.repaint();
			visibilidad();
		});
		
		lblCerrar.addMouseListener(new MouseAdapter() {
			
		
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres cerrar tu sesión", "Confirmacion", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					VentanaPrincipal.setClienteHaIniciadoSesion(false);
					VentanaPrincipal.eliminarNombreCliente();
					VentanaPrincipal.getLblSaldo().setText("");
					vActual.setVisible(false);
				}
				
			}
		});
		
		lblElim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres elimiar tu cuenta", "Confirmacion", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {
					Cliente c = VentanaInicioSesion.getCliente();
					Connection con = BD.initBD("NatiShop.db");
					BD.borrarCliente(con, c.getDni());
					BD.closeBD(con);
					VentanaPrincipal.setClienteHaIniciadoSesion(false);
					VentanaPrincipal.eliminarNombreCliente();
					VentanaPrincipal.getLblSaldo().setText("");
					vActual.setVisible(false);
				}
			}
		});
		
		pnlDatos.add(pnlCentro);
		
		
		pnlDcha = new JPanel(new BorderLayout());
		pnlDchaCtro = new JPanel(new GridLayout(14, 1));
		lblTarj = new JLabel("<html><center><u><b>¿DESEAS AÑADIR UN NÚMERO DE TARJETA<br>A TU CUENTA?</b></u></center></html>");
		lblTarj.setFont(new Font("Baskerville", Font.BOLD, 10));
		lblTarj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 if (!elementoAgregado) {
			            agregarElementosAlPanel(c);
			            elementoAgregado = true;
			        } else {
			        	ocultarAñadirT();
			            pnlDchaCtro.revalidate();
			            pnlDchaCtro.repaint();
			            elementoAgregado = false;
			        }
				 
				
			}
		});
		
		pnlDchaCtro.add(new JPanel());
		pnlDchaCtro.add(lblTarj);
		//pnlDchaCtro.add(new JPanel());
		pnlDcha.add(pnlDchaCtro, BorderLayout.CENTER);
		pnlDatos.add(pnlDcha);
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
    private static void agregarCampos(JPanel p, String label, Cliente c) {
    	JPanel panelModif = new JPanel(new GridLayout(7,1));
        JLabel etiqueta = new JLabel(label);
        panelModif.add(etiqueta);
        
        if (intModif == 1 || intModif == 2) {
        	JLabel actual = new JLabel("Introduce tu " + label.toLowerCase() + "actual");
            tfActual = new JTextField(20);
        	JLabel nuevo = new JLabel("Introduce tu " + label.toLowerCase() + "nuevo");
            tfNuevo = new JTextField(20);
            panelModif.add(actual);
            panelModif.add(tfActual);
            panelModif.add(nuevo);
            panelModif.add(tfNuevo);
        	
        } else {
        	JLabel actualC = new JLabel("Introduce tu " + label.toLowerCase() + "actual");
            jpActual = new JPasswordField(20);
        	JLabel nuevaC = new JLabel("Introduce tu " + label.toLowerCase() + "nueva");
            jpNueva = new JPasswordField(20);
            panelModif.add(actualC);
            panelModif.add(jpActual);
            panelModif.add(nuevaC);
            panelModif.add(jpNueva);
        	
        }
        
        btnModificar = new JButton("MODIFICAR");
        btnModificar.addActionListener(e -> {
        	Connection con = BD.initBD("NatiShop.db");
            switch (intModif) {
                case 1:
                    if (c.getCorreo().equals(tfActual.getText())) {
                        BD.modificarEmailCliente(con, c.getDni(), tfNuevo.getText());
                        btnCorreo.setText(tfNuevo.getText());
                        tfActual.setText("");
                        tfNuevo.setText("");
                        JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
                        p.removeAll();
                	    p.revalidate();
                	    p.repaint();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "El email actual introducido no es correcto");
                    }
                    break;

                case 2:
                    if (c.getTlf().equals(tfActual.getText())) {
                        BD.modificarTlfCliente(con, c.getDni(), tfNuevo.getText());
                        btnTfn.setText(tfNuevo.getText());
                        tfActual.setText("");
                        tfNuevo.setText("");
                        JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
                        p.removeAll();
                	    p.revalidate();
                	    p.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "El telefono actual introducido no es correcto");
                    }
                    break;

                case 3:
                    if (c.getContrasenia().equals(jpActual.getText())) {
                        BD.modificarContraCliente(con, c.getDni(), jpNueva.getText());
                        btnContrasenia.setText("*".repeat(jpNueva.getText().length()));
                        jpActual.setText("");
                        jpNueva.setText("");
                        JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
                        p.removeAll();
                	    p.revalidate();
                	    p.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseña actual introducida no es correcta");
                    }
                    break;
            }
            BD.closeBD(con);

        });

		
        JPanel pnlBoton = new JPanel(new BorderLayout());
        pnlBoton.add(btnModificar, BorderLayout.SOUTH);
        
        
        panelModif.add(pnlBoton);
        

        p.add(panelModif, BorderLayout.CENTER);
		p.add(new JPanel(), BorderLayout.WEST);
		p.add(new JPanel(), BorderLayout.EAST);
        
       
    }
    
    private void visibilidad() {
    	panelVisible = !panelVisible;
        pnlCentro.setVisible(panelVisible);
    }
    
    private void agregarElementosAlPanel(Cliente c) {
        // Crear y agregar nuevos elementos al panel
    	JLabel lblT = new JLabel("NUMERO DE TARJETA");
    	pnlDchaCtro.add(lblT);
        tfNumTarj = new JTextField();
        pnlDchaCtro.add(tfNumTarj);
        
        JLabel lblAño = new JLabel("AÑO");
    	pnlDchaCtro.add(lblAño);
        JComboBox <Object> JCbaños = new JComboBox<Object>();
        int añoActual = Calendar.getInstance().get(Calendar.YEAR);
        for (int a = añoActual; a <= añoActual + 20; a++) {
        	JCbaños.addItem(a);   
        }
        pnlDchaCtro.add(JCbaños);
        
        JLabel lblMes = new JLabel("MES");
    	pnlDchaCtro.add(lblMes);
        JComboBox <Object> JCbmeses = new JComboBox<Object>();
        for (int i = 1; i <= 12; i++) {
            String mesFormateado = String.format("%02d", i);
            JCbmeses.addItem(mesFormateado);
        }
        pnlDchaCtro.add(JCbmeses);
        
        JLabel lblTit = new JLabel("TITULAR DE LA TARJETA");
    	pnlDchaCtro.add(lblTit);
        tftitular = new JTextField();
        pnlDchaCtro.add(tftitular);
        
        JLabel lblCVV = new JLabel("CVV");
    	pnlDchaCtro.add(lblCVV);
    	tfCVV = new JTextField();
        pnlDchaCtro.add(tfCVV);
        
        pnlDchaCtro.add(new JPanel());
        
        JButton btnAnadir = new JButton("AÑADIR TARJETA");
        pnlDchaCtro.add(btnAnadir);
        btnAnadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comprobarNTarj()) {
		        	if(comprobarTitular()) {
		        		if(comprobarCVV()) {
		        			if (c.getNumTarjeta().equals("Tarjeta sin registrar")) {
		        				Connection con = BD.initBD("NatiShop.db");
			        			BD.modificarNumTarj(con, c.getDni() , tfNumTarj.getText());
			        			BD.closeBD(con);
			        			VentanaPrincipal.getLblSaldo().setText(c.getSaldo()+"€");
			        			JOptionPane.showMessageDialog(null, "Numero de tarjeta añadido");
			        			dispose();
		        			}else {
		        				int confirmacion = JOptionPane.showConfirmDialog(null, "Ya tienes un numero de tarjeta para tu cuenta, ¿Deseas remplazarlo? \n El dinero que tienes en esta cuenta se te ingrasara al numero de tarjeta que tenias anteriormente.", "Confirmacion", JOptionPane.YES_NO_OPTION);
		        				if (confirmacion == JOptionPane.YES_OPTION) {
		        					Connection con = BD.initBD("NatiShop.db");
				        			BD.modificarNumTarj(con, c.getDni() , tfNumTarj.getText());
				        			BD.closeBD(con);
				        			VentanaPrincipal.getLblSaldo().setText("0.0€");
				        			ocultarAñadirT();
						            pnlDchaCtro.revalidate();
						            pnlDchaCtro.repaint();
						            elementoAgregado = false;
						            dispose();
		        				}else {
		        					System.out.println("No ha modificado la tarjeta");
		        					ocultarAñadirT();
		    			            pnlDchaCtro.revalidate();
		    			            pnlDchaCtro.repaint();
		    			            elementoAgregado = false;
		    			            dispose();
		        				}
		        			}
		        			
		        			
		        		}else {
		        			JOptionPane.showMessageDialog(null, "El CVV introducido no es correcto, debe tener 3 dígitos númericos");
		        		}
		        	}else {
		        		JOptionPane.showMessageDialog(null, "El nombre del tutular introducido no es correcto");
		        	}
		        }else {
		        	JOptionPane.showMessageDialog(null, "El numero de tarjeta introducido no es correcto, debe tener 16 dígitos númericos");
		        }
				
			}
		});
        
        
        
        
        // Actualizar la interfaz para que los cambios sean visibles
        revalidate();
        repaint();
    }
    
    private void ocultarAñadirT() {
        // Obtener los componentes del panel
        Component[] components = pnlDchaCtro.getComponents();

        // Mantener los dos primeros componentes
        List<Component> componentList = new ArrayList<>();
        componentList.add(components[0]); // Primer componente
        componentList.add(components[1]); // Segundo componente

        // Eliminar los demás componentes
        for (int i = 2; i < components.length; i++) {
            pnlDchaCtro.remove(components[i]);
        }

        // Revalidar y repintar el panel
        pnlDchaCtro.revalidate();
        pnlDchaCtro.repaint();
    }
    
	private boolean comprobarNTarj() {
		String patron = "[0-9]{16}";
		return Pattern.matches(patron, tfNumTarj.getText());
	}
	
	private boolean comprobarTitular() {
		String patron = "[A-Za-z]{0,}";
		return Pattern.matches(patron, tftitular.getText());
	}
	
	private boolean comprobarCVV() {
		String patron = "[0-9]{3}";
		return Pattern.matches(patron, tfCVV.getText());
	}
    

}
