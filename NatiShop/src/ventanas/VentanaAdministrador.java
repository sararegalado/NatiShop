package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.Tienda;
import clases.Usuario;

import java.util.List;



public class VentanaAdministrador extends JFrame{
	private JPanel pnlOesteMenu,pnlCentro;
	private JMenuBar menuBarAdmin;
	private JMenu menuUsuarios,MenuArticulos, MenuEstadisticas;
	private JMenuItem mItemArticulos,mItemStock;
	private JLabel lblfoto;
	
	private JTable tablaUsuarios;
	private ModeloTablaUsuarios mUsuarios;
	private ScrollPane sTablaUsuarios;
	
	
	
	
	
	public VentanaAdministrador() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 10, 1121, 621);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel pnlOesteMenu= new JPanel();
		pnlOesteMenu.setLayout(new GridLayout(2,1));
		pnlOesteMenu.setPreferredSize(new Dimension(225,500));
		getContentPane().add(pnlOesteMenu, BorderLayout.WEST);
		pnlOesteMenu.setBackground(Color.WHITE);
		
		


		
		JLabel lblFoto= new JLabel("");
		pnlOesteMenu.add(lblFoto);
		lblFoto.setIcon(new ImageIcon(VentanaAdministrador.class.getResource("/imagenes/Admin.png")));
		lblFoto.setHorizontalAlignment(JLabel.CENTER);
        lblFoto.setVerticalAlignment(JLabel.CENTER);
		
		
		
		JPanel pnlCentro = new JPanel();
		getContentPane().add(pnlCentro, BorderLayout.CENTER);
		pnlCentro.setLayout(new GridLayout(1,1));
		
		tablaUsuarios = new JTable(new ModeloTablaUsuarios(null));
		pnlCentro.add(tablaUsuarios);
		
		JMenuBar menuBarAdmin= new JMenuBar();
		pnlOesteMenu.add(menuBarAdmin);
		menuBarAdmin.setFont(new Font("Baskerville", Font.PLAIN, 14));
		menuBarAdmin.setLayout(new GridLayout(5,1));
		
		
		JMenu menuUsuarios = new JMenu("USUARIOS");
		menuUsuarios.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 15));
		menuBarAdmin.add(menuUsuarios);
		
		menuUsuarios.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTablaUsuarios();
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
		});
		
	
		
		JMenu MenuArticulos = new JMenu("ARTICULOS");
		MenuArticulos.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuBarAdmin.add(MenuArticulos);
		
		JMenuItem mItemArticulos = new JMenuItem("ARTICULOS DISPONIBLES");
		mItemArticulos.setFont(new Font("Calibri", Font.BOLD, 15));
		MenuArticulos.add(mItemArticulos);
		
		
		JMenuItem mItemStock = new JMenuItem("GESTION DE STOCK");
		mItemStock.setFont(new Font("Calibri", Font.BOLD, 15));
		MenuArticulos.add(mItemStock);
		
		
		JMenu MenuEstadisticas = new JMenu("ESTADISTICAS");
		MenuEstadisticas.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuBarAdmin.add(MenuEstadisticas);
		
		tablaUsuarios= new JTable(mUsuarios);
		pnlCentro.add(tablaUsuarios);
		
		//sTablaUsuarios = new ScrollPane(tablaUsuarios);
		
		
		
		
		setVisible(true);		
	}
	public static void main(String[] args) {
		VentanaAdministrador ventanaAdministrador= new VentanaAdministrador();
	}
	
	public void cargarTablaUsuarios() {
		List<Usuario>lista= Tienda.getUsuarios();
		
		
		mUsuarios = new ModeloTablaUsuarios(lista);
		tablaUsuarios.setModel(mUsuarios);

	}
	
	
	/*ERRORES
	 * ACTIONLISTENER DEL MENU ITEM
	 * CARGAR LA TABLA 
	 * SCROLL DE LA TABLA
	 * REDUCIR EL LISTENER DEL MENU ITEM DE USUARIOS
	 * */
	

	
	

}
