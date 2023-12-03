package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;

import clases.Articulo;
import clases.Cliente;
import clases.Tienda;
import clases.Usuario;
import clases.Administrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class VentanaAdministrador extends JFrame{

	private JPanel pnlOesteMenu,pnlCentro,pnlOesteArriba,pnlDatos,pnlDatosArriba,pnlDatosModificar;

	private JMenuBar menuBarAdmin;
	private JMenu menuClientes,menuArticulos, menuEstadisticas, menuCompras;
	private JMenuItem mItemRegistros,mItemArticulos,mItemStock,mItemCompras,mItemGraficos;
	private JLabel lblFoto,lblDNI,lblnom,lblApellido,lblCorreo,lbltfn,lblProvincia,lblFnac,lblFinic,lblJornada,lblPuesto,lblModifJornada;
	private JTextField tfDNI, tfnom, tfApellido, tfCorreo, tfTfn, tfProvincia, tfnFnac, tfnInic, tfJornada, tfPuesto;
	private JButton btnDesplegar;
	
	private JTable tablaClientes;
	private ModeloTablaClientes mClientes;
	private JScrollPane sTablaUsuarios;
	private JFrame vActual,vAnterior;
	
	public VentanaAdministrador(JFrame va) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 10, 1300, 900);
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	    int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	    setSize(anchoP, altoP);
	    setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout());
	
		
		pnlOesteMenu= new JPanel();
		pnlOesteMenu.setLayout(new GridLayout(2,1));
		pnlOesteMenu.setPreferredSize(new Dimension(225,500));
		getContentPane().add(pnlOesteMenu, BorderLayout.WEST);
		pnlOesteMenu.setBackground(Color.WHITE);
		
		pnlOesteArriba= new JPanel();
		pnlOesteArriba.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlOesteArriba.setBackground(Color.WHITE);
		this.add(pnlOesteArriba, BorderLayout.NORTH);
		
		
		btnDesplegar = new JButton();
		btnDesplegar.setBackground(Color.WHITE);
		btnDesplegar.setPreferredSize(new Dimension(27,27));
		pnlOesteArriba.add(btnDesplegar);
		btnDesplegar.setIcon(new ImageIcon(VentanaAdministrador.class.getResource("/imagenes/btnDesplegar.png")));
		/**
		 * Boton que hace el panel menu visible o no 
		 */
		btnDesplegar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlOesteMenu.setVisible(!pnlOesteMenu.isVisible());
			}
		});
		
		JPanel pnlDatos= new JPanel();
		pnlDatos.setLayout(new GridLayout(2,1));
		pnlDatos.setVisible(false);

        pnlDatosArriba = new JPanel();
        pnlDatosArriba.setLayout(new GridLayout(10,2));
        pnlDatos.add(pnlDatosArriba);


        JLabel lblDNI = new JLabel("DNI:");
        JTextField tfDNI= new JTextField();
        JLabel lblnom = new JLabel("Nombre:");
        JTextField tfnom= new JTextField();
        JLabel lblApellido = new JLabel("Apellido: ");
        JTextField tfApellido = new JTextField();
        JLabel lblCorreo = new JLabel("Correo: ");
        JTextField tfCorreo= new JTextField();
        JLabel lbltfn = new JLabel("Telefono: ");
        JTextField tfTfn = new JTextField();
        JLabel lblProvincia = new JLabel("Provincia: ");
        JTextField tfProvincia= new JTextField();
        JLabel lblFnac = new JLabel("Fecha de Nacimiento: ");
        JTextField tfFnac = new JTextField();
        JLabel lblFinic = new JLabel("Fecha de inicio de en la empresa: ");
        JTextField tfnInic = new JTextField();

        JLabel lblJornada= new JLabel("Jornada: ");
        JTextField tfJornada= new JTextField(); 

        JLabel lblPuesto = new JLabel("Puesto: ");
        JTextField tfPuesto = new JTextField();

        pnlDatosArriba.add(lblDNI);
        pnlDatosArriba.add(tfDNI);
        pnlDatosArriba.add(lblnom);
        pnlDatosArriba.add(tfnom);
        pnlDatosArriba.add(lblApellido);
        pnlDatosArriba.add(tfApellido);
        pnlDatosArriba.add(lblCorreo);
        pnlDatosArriba.add(tfCorreo);
        pnlDatosArriba.add(lbltfn);
        pnlDatosArriba.add(tfTfn);
        pnlDatosArriba.add(lblProvincia);
        pnlDatosArriba.add(tfProvincia);
        pnlDatosArriba.add(lblFnac);
        pnlDatosArriba.add(tfFnac);
        pnlDatosArriba.add(lblFinic);
        pnlDatosArriba.add(tfnInic);
        pnlDatosArriba.add(lblJornada);
        pnlDatosArriba.add(tfJornada);
        pnlDatosArriba.add(lblPuesto);
        pnlDatosArriba.add(tfPuesto);

        pnlDatosModificar = new JPanel();
        pnlDatos.add(pnlDatosModificar);

        lblModifJornada = new JLabel("<html><u>" + "MODIFICAR JORNADA" + "</u></html>");
        lblModifJornada.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
        pnlDatosModificar.add(lblModifJornada);
		
		
		lblFoto= new JLabel("");
		pnlOesteMenu.add(lblFoto, BorderLayout.CENTER);
		lblFoto.setIcon(new ImageIcon(VentanaAdministrador.class.getResource("/imagenes/Admin.png")));
		lblFoto.setHorizontalAlignment(JLabel.CENTER);
        lblFoto.setVerticalAlignment(JLabel.CENTER);
        
        lblFoto.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("PULSANDO");
				pnlCentro.removeAll();
				pnlCentro.revalidate();
				pnlCentro.repaint();
				pnlCentro.add(pnlDatos);
				


				pnlDatos.setVisible(true);

			}
        });

		
		pnlCentro = new JPanel();
		getContentPane().add(pnlCentro, BorderLayout.CENTER);
		pnlCentro.setLayout(new GridLayout(1,1));
		
		
		menuBarAdmin= new JMenuBar();
		pnlOesteMenu.add(menuBarAdmin);
		menuBarAdmin.setFont(new Font("Baskerville", Font.PLAIN, 14));
		menuBarAdmin.setLayout(new GridLayout(5,1));
		
		
		menuClientes = new JMenu("USUARIOS");
		menuClientes.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 15));
		menuBarAdmin.add(menuClientes);
		
		mItemRegistros = new JMenuItem("USUARIOS REGISTRADOS");
		mItemRegistros.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuClientes.add(mItemRegistros);
		mItemRegistros.addActionListener(new ActionListener() {
			/**
			 * 
			 * La tabla se carga y visualiza con todos los usuarios registrados en la tienda al pulsar el menutem Registros
			 */

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlCentro.removeAll();
				pnlCentro.revalidate();
				pnlCentro.repaint();
				cargarTablaUsuarios();
				System.out.println("PULSANDO");
				
			}
			
		});
		
		menuArticulos = new JMenu("ARTICULOS");
		menuArticulos.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuBarAdmin.add(menuArticulos);
		
		
		mItemArticulos = new JMenuItem("ARTICULOS DISPONIBLES");
		mItemArticulos.setFont(new Font("Calibri", Font.BOLD, 15));
		menuArticulos.add(mItemArticulos);
		
		
		mItemStock = new JMenuItem("GESTION DE STOCK");
		mItemStock.setFont(new Font("Calibri", Font.BOLD, 15));
		menuArticulos.add(mItemStock);
		
		//Janire
		menuCompras = new JMenu("COMPRAS");
		menuClientes.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 15));
		menuBarAdmin.add(menuCompras);
		
		mItemCompras = new JMenuItem("VER COMPRAS");
		mItemCompras.setFont(new Font("Calibri", Font.BOLD, 15));
		menuCompras.add(mItemCompras);
		
		
		mItemCompras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlCentro.removeAll();
				pnlCentro.revalidate();
				pnlCentro.repaint();
				cargarCompras();
				
			}
		});
		
		
		
		menuEstadisticas = new JMenu("ESTADISTICAS");
		menuEstadisticas.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuBarAdmin.add(menuEstadisticas);
		
		mItemGraficos = new JMenuItem("Graficos");
		mItemGraficos.setFont(new Font("Calibri", Font.BOLD, 15));
		menuEstadisticas.add(mItemGraficos);
		
		mClientes = new ModeloTablaClientes(new ArrayList<>());
		tablaClientes = new JTable(mClientes);
		sTablaUsuarios = new JScrollPane(tablaClientes);
		//pnlCentro.add(sTablaUsuarios);
		pnlCentro.setVisible(false);
		
		tablaClientes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				int fila = tablaClientes.rowAtPoint(p);
				String dni = tablaClientes.getModel().getValueAt(fila, 0).toString();
				String texto = "";
				for(String fecha: Tienda.getComprasPorCliente().get(dni).keySet()) {
					texto = "FECHA: "+fecha + "\n";
					for(Articulo a: Tienda.getComprasPorCliente().get(dni).get(fecha)) {
						texto = texto + a + "\n";
					}
				}
				JOptionPane.showMessageDialog(null, texto);
			}
		});
		
		setVisible(true);		
	}
	
	/**
	 * Método para cargar los usuarios registrador a la tabla
	 */
	public void cargarTablaUsuarios() {
		Tienda.cargarClientes(Tienda.getNomfichclientes());
		List<Cliente>lista = Tienda.getClientes();
		tablaClientes.setModel(new ModeloTablaClientes(lista));
		pnlCentro.add(sTablaUsuarios);
		pnlCentro.setVisible(true);
	}
	
	public void cargarCompras() {
		JCalendar jcCompras = new JCalendar(new Date());
		pnlCentro.add(jcCompras);
		pnlCentro.setVisible(true);
	}


	public void cargarDatosAdmin() {
		
		
	}
	


	
	/*ERRORES/TAREAS
	 * Inicio de sesion admins
	 * Ventana edit admins
	 * Admins: implemeta al heredar de Usuario ya el compare to?
	 *
	  */
	

	
	

}

