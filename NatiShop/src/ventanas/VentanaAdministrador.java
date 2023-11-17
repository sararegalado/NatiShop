package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import clases.Tienda;
import clases.Usuario;

import java.util.ArrayList;
import java.util.List;



public class VentanaAdministrador extends JFrame{
	private JPanel pnlOesteMenu,pnlCentro,pnlOesteArriba;
	private JMenuBar menuBarAdmin;
	private JMenu menuUsuarios,MenuArticulos, MenuEstadisticas;
	private JMenuItem mItemRegistros,mItemArticulos,mItemStock,mItemGraficos;
	private JLabel lblfoto;
	private Button btnDesplegar;
	
	private JTable tablaUsuarios;
	private ModeloTablaUsuarios mUsuarios;
	private JScrollPane sTablaUsuarios;
	
	
	
	
	
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
		
		JPanel pnlOesteArriba= new JPanel();
		pnlOesteArriba.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlOesteArriba.setBackground(Color.WHITE);
		this.add(pnlOesteArriba, BorderLayout.NORTH);
		
		
		JButton btnDesplegar = new JButton();
		btnDesplegar.setBackground(Color.WHITE);
		btnDesplegar.setPreferredSize(new Dimension(27,27));
		pnlOesteArriba.add(btnDesplegar);
		
		btnDesplegar.setIcon(new ImageIcon(VentanaAdministrador.class.getResource("/imagenes/btnDesplegar.png")));
		btnDesplegar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlOesteMenu.setVisible(!pnlOesteMenu.isVisible());
			}
		});
		
		
		


		
		JLabel lblFoto= new JLabel("");
		pnlOesteMenu.add(lblFoto, BorderLayout.CENTER);
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
		
		JMenuItem mItemRegistros = new JMenuItem("USUARIOS REGISTRADOS");
		mItemRegistros.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuUsuarios.add(mItemRegistros);
		mItemRegistros.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTablaUsuarios();
				System.out.println("PULSANDO");
				
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
		
		mUsuarios = new ModeloTablaUsuarios(new ArrayList<>());
		tablaUsuarios = new JTable(mUsuarios);
		sTablaUsuarios = new JScrollPane(tablaUsuarios);
		pnlCentro.add(tablaUsuarios);

		
		
		
		setVisible(true);		
	}
	public static void main(String[] args) {
		VentanaAdministrador ventanaAdministrador= new VentanaAdministrador();
	}
	
	public void cargarTablaUsuarios() {
		List<Usuario>lista = Tienda.getUsuarios();
		for(Usuario u: lista) {
			Object [] fila = {u.getDni(), u.getNombre(), u.getCorreo(), u.getfNacStr()};
			mUsuarios.addRow(fila);
		}
	}
	
	
	/*ERRORES
	 
	 * CARGAR LA TABLA 
	 * SCROLL DE LA TABLA
	
	 * 
	 * 
	  */
	

	
	

}
