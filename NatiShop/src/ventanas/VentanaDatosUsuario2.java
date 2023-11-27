package ventanas;

import javax.swing.*;

import clases.Tienda;
import clases.Usuario;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class VentanaDatosUsuario2 extends JFrame{
	private JPanel pnlDatos, pnlIzq, pnlDcha, pnlCentrarB1, pnlCentrarB2, pnlCentrarB3, pnlModifE, pnlModifT, pnlModifC ;
	private JLabel lblTitulo, lblNombre, lblAtras, lblEmail, lblTlf, lblCon;
	private JButton btnCorreo, btnTfn, btnContrasenia;
	private static JButton btnModificar;
	private JLabel lblCerrar, lblElim;
	private JTextField tfEmail;
	private static int intModif = 0;
	private JFrame vActual, vAnterior;
	
	private static JTextField tfActual, tfNuevo;
	private static JPasswordField jpActual, jpNueva;
	
	
	public VentanaDatosUsuario2(JFrame va, Usuario u) {
		vActual = this;
		vAnterior = va;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,  778, 455);
		
		pnlDatos = new JPanel(new GridLayout(1, 2));
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
		
		lblNombre = new JLabel (u.getNombre());
		lblEmail = new JLabel("EMAIL");
		pnlCentrarB1 = new JPanel (new GridLayout(1,2));
		btnCorreo = new JButton(u.getCorreo());
		btnCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		pnlCentrarB1.add(btnCorreo);
		pnlCentrarB1.add(new JPanel());
		
		
		pnlCentrarB2 = new JPanel (new GridLayout(1,2));
		lblTlf = new JLabel ("TELÉFONO");
		btnTfn = new JButton(u.getTlf());
		btnTfn.setHorizontalAlignment(SwingConstants.LEFT);
		pnlCentrarB2.add(btnTfn);
		pnlCentrarB2.add(new JPanel());
		
		pnlCentrarB3 = new JPanel (new GridLayout(1,2));
		lblCon = new JLabel("CONTRASEÑA");
		btnContrasenia = new JButton("*".repeat(u.getContrasenia().length()));
		btnContrasenia.setHorizontalAlignment(SwingConstants.LEFT);
		pnlCentrarB3.add(btnContrasenia);
		pnlCentrarB3.add(new JPanel());
		
		lblCerrar = new JLabel("<html><u>" + "Cerrar sesión" + "</u></html>");
		lblCerrar.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		lblElim = new JLabel("<html><u>" + "Eliminar cuenta" + "</u></html>");
		lblElim.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		
		pnlIzq.add(new JPanel());
		pnlIzq.add(lblNombre);
		pnlIzq.add(lblEmail);
		pnlIzq.add(pnlCentrarB1);
		pnlIzq.add(lblTlf);
		pnlIzq.add(pnlCentrarB2);
		pnlIzq.add(lblCon);
		pnlIzq.add(pnlCentrarB3);
		
		pnlIzq.add(new JPanel());
		pnlIzq.add(lblCerrar);
		pnlIzq.add(lblElim);
		pnlIzq.add(new JPanel());
		
		pnlDatos.add(pnlIzq);
		
		
		pnlDcha = new JPanel(new BorderLayout());
//		pnlModifE = new JPanel(new GridLayout(2,1));
//		pnlModifT = new JPanel();
//		pnlModifC = new JPanel();
		
		
		btnCorreo.addActionListener(e -> {
			intModif = 1;
			pnlDcha.removeAll();
			agregarCampos(pnlDcha, "EMAIL ", u);
			pnlDcha.revalidate();
			pnlDcha.repaint();
		});
		
		btnTfn.addActionListener(e -> {
			intModif = 2;
			pnlDcha.removeAll();
			agregarCampos(pnlDcha, "TELEFONO ", u);
			pnlDcha.revalidate();
			pnlDcha.repaint();
		});
		
		btnContrasenia.addActionListener(e -> {
			intModif = 3;
			pnlDcha.removeAll();
			agregarCampos(pnlDcha, "CONTRASEÑA ", u);
			pnlDcha.revalidate();
			pnlDcha.repaint();
		});
		
		lblCerrar.addMouseListener(new MouseAdapter() {
			
		
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Sesión cerrada");
				VentanaPrincipal.setUsuarioHaIniciadoSesion(false);
				VentanaPrincipal.eliminarNombreUsuario();
				vActual.setVisible(false);
			}
		});
		
		lblElim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario u = VentanaInicioSesion.getUsuario();
				Tienda.getUsuarios().remove(u);
				Tienda.guardarUsuarios(Tienda.getNomfichusuarios());
				VentanaPrincipal.setUsuarioHaIniciadoSesion(false);
				VentanaPrincipal.eliminarNombreUsuario();
				vActual.setVisible(false);	
			}
		});
		
		pnlDatos.add(pnlDcha);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
    private static void agregarCampos(JPanel pnlDcha, String label, Usuario u) {
    	JPanel panelModif = new JPanel(new GridLayout(6,1));
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
		btnModificar.addActionListener(e ->{
			if(intModif == 1) {
				System.out.println("Modificar email");
				if (u.getCorreo().equals(tfActual.getText())) {
					u.setCorreo(tfNuevo.getText());
					tfActual.setText("");
					tfNuevo.setText("");
					JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "El email actual introducido no es correcto");
				}
				
				
			}else if (intModif == 2) {
				System.out.println("Modificar tlf");
				if (u.getTlf().equals(tfActual.getText())) {
					u.setTlf(tfNuevo.getText());
					tfActual.setText("");
					tfNuevo.setText("");
					JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "El telefono actual introducido no es correcto");
				}
			}else if (intModif == 3) {
				System.out.println("Modificar contraseña");
				if (u.getContrasenia().equals(jpActual.getText())) {
					u.setTlf(jpNueva.getText());
					jpActual.setText("");
					jpNueva.setText("");
					JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "La contraseña actual introducida no es correcta");
				}
			}
			Tienda.guardarUsuarios(Tienda.getNomfichusuarios());
			
		});
		
        JPanel pnlBoton = new JPanel(new BorderLayout());
        pnlBoton.add(btnModificar, BorderLayout.SOUTH);
        
        
        panelModif.add(pnlBoton);
        

        pnlDcha.add(panelModif, BorderLayout.CENTER);
        
       
    }

}
