package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;

import clases.Articulo;
import clases.Cliente;
import clases.Puesto;
import clases.Tienda;
import clases.Usuario;
import clases.Administrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class VentanaAdministrador extends JFrame{

	private JPanel pnlOesteMenu,pnlCentro,pnlOesteArriba,pnlDatos,pnlDatosTitulo,pnlDatosArriba,pnlDatosBotones;

	private JMenuBar menuBarAdmin;
	private JMenu menuClientes,menuArticulos, menuEstadisticas, menuCompras;
	private JMenuItem mItemRegistros,mItemArticulos,mItemStock,mItemCompras,mItemGraficos;
	private JLabel lblFoto,lblTitulo,lblDNI,lblnom,lblApellido,lblCorreo,lbltfn,lblProvincia,lblFnac,lblFinic,lblJornada,lblPuesto,lblModifJornada,lblSolicitudes,lblAñadirAdmin;
	private JTextField tfDNI, tfnom, tfApellido, tfCorreo, tfTfn, tfProvincia, tfFnac, tfnInic, tfJornada, tfPuesto;
	private JButton btnDesplegar;
	
	private JTable tablaClientes;
	private ModeloTablaClientes mClientes;
	private JScrollPane sTablaUsuarios;
	private JFrame vActual,vAnterior;
	private Administrador admin;
	
	public VentanaAdministrador(JFrame va, Administrador admin) {
		
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
		
		pnlDatos= new JPanel();
		pnlDatos.setLayout(new GridLayout(3,1));
		pnlDatos.setVisible(false);
		
		pnlDatosTitulo = new JPanel();
		pnlDatosTitulo.setLayout(new GridLayout(1,1));
		pnlDatos.add(pnlDatosTitulo);
		
		lblTitulo = new JLabel("<html><u>" + "DATOS PERSONALES" + "</u></html>");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		pnlDatosTitulo.add(lblTitulo);

        pnlDatosArriba = new JPanel();
        pnlDatosArriba.setLayout(new GridLayout(10,2));
        pnlDatos.add(pnlDatosArriba);
        
        

        lblDNI = new JLabel("DNI:");
        tfDNI= new JTextField();
        tfDNI.setEditable(false);
        lblnom = new JLabel("Nombre:");
        tfnom= new JTextField();
        tfnom.setEditable(false);
        lblApellido = new JLabel("Apellido: ");
        tfApellido = new JTextField();
        tfApellido.setEditable(false);
        lblCorreo = new JLabel("Correo: ");
        tfCorreo= new JTextField();
        tfCorreo.setEditable(false);
        lbltfn = new JLabel("Telefono: ");
        tfTfn = new JTextField();
        tfTfn.setEditable(false);
        lblProvincia = new JLabel("Provincia: ");
        tfProvincia= new JTextField();
        tfProvincia.setEditable(false);
        lblFnac = new JLabel("Fecha de Nacimiento: ");
        tfFnac = new JTextField();
        tfFnac.setEditable(false);
        lblFinic = new JLabel("Fecha de inicio de en la empresa: ");
        tfnInic = new JTextField();
        tfnInic.setEditable(false);

        lblJornada= new JLabel("Jornada: ");
        tfJornada= new JTextField(); 
        tfJornada.setEditable(false);

        lblPuesto = new JLabel("Puesto: ");
        tfPuesto = new JTextField();
        tfPuesto.setEditable(false);

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
				cargarDatosAdmin(admin);
				


				pnlDatos.setVisible(true);

			}
        });

        pnlDatosBotones = new JPanel();
        pnlDatosBotones.setLayout(new GridLayout(1,3));
        pnlDatos.add(pnlDatosBotones);
        
        
        lblSolicitudes= new JLabel("<html><u>" + "SOLICITUDES DE CAMBIO DE JORNADA" + "</u></html>");
        lblSolicitudes.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
        pnlDatosBotones.add(lblSolicitudes);
        lblSolicitudes.setHorizontalAlignment(lblModifJornada.CENTER);

        lblModifJornada = new JLabel("<html><u>" + "MODIFICAR JORNADA" + "</u></html>");
        lblModifJornada.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
        pnlDatosBotones.add(lblModifJornada);
        lblModifJornada.setHorizontalAlignment(lblModifJornada.CENTER);
        lblModifJornada.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
        		int respuesta = JOptionPane.showConfirmDialog(null, "A continuación, podrás solicitar un cambio de jornada laboral, ¿Quieres continuar?", "SOLICITUD DE CAMBIO DE JORNADA", JOptionPane.YES_NO_OPTION);
        		if(respuesta == JOptionPane.YES_OPTION) {
        			new VentanaSolicitudJornada(vActual);
        			
        			
        		};
        		
			}

        	
        });
        
        lblAñadirAdmin = new JLabel("<html><u>" + "AÑADIR NUEVO ADMINISTRADOR" + "</u></html>");
        lblAñadirAdmin.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
        pnlDatosBotones.add(lblAñadirAdmin);
        
        if(admin.getPuesto().equals(Puesto.Gerente)) {
        	lblAñadirAdmin.setVisible(true);
        	lblSolicitudes.setVisible(true);
        }else {
        	lblAñadirAdmin.setVisible(false);
        	lblSolicitudes.setVisible(false);
        }
       
        
        
       
		pnlCentro = new JPanel(new BorderLayout());
		getContentPane().add(pnlCentro, BorderLayout.CENTER);
		//pnlCentro.setLayout(new GridLayout(1,1));
		
		
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
		Connection con = BD.initBD("NatiShop.db");
		List<Cliente> c = BD.obtenerListaClientes(con);
		BD.closeBD(con);
		tablaClientes.setModel(new ModeloTablaClientes(c));
		pnlCentro.add(sTablaUsuarios, BorderLayout.CENTER);
		JLabel lblUsuarios = new JLabel("<html><u>" + "USUARIOS" + "</u></html>");
		lblUsuarios.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 30));
		lblUsuarios.setHorizontalAlignment(JLabel.CENTER);
		pnlCentro.add(lblUsuarios, BorderLayout.NORTH);
		pnlCentro.setVisible(true);
	}
	
	public void cargarCompras() {
		JCalendar jcCompras = new JCalendar(new Date());
		JPanel pnlCalendar = new JPanel(new GridLayout(2,1));
		pnlCalendar.add(jcCompras);
		JTextArea jTaCom = new JTextArea();
		pnlCalendar.add(jTaCom);
		pnlCentro.add(pnlCalendar, BorderLayout.CENTER);
		JLabel lblCompras = new JLabel("<html><u>" + "COMPRAS" + "</u></html>");
		lblCompras.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 30));
		lblCompras.setHorizontalAlignment(JLabel.CENTER);
		pnlCentro.add(lblCompras, BorderLayout.NORTH);
		pnlCentro.setVisible(true);
	}


	public void cargarDatosAdmin(Administrador admin) {
		if(admin != null) {
			System.out.println("NO ES NULO");
			tfDNI.setText(admin.getDni());
			tfnom.setText(admin.getNombre());
			tfApellido.setText(admin.getApellido());
			tfCorreo.setText(admin.getCorreo());
			tfTfn.setText(admin.getTlf());
			tfProvincia.setText(admin.getProvinciaStr());
			tfFnac.setText(admin.getfNacStr());
			tfnInic.setText(admin.getFInicEmpresaStr());
			tfJornada.setText(admin.getJornadaLaboralStr());
			tfPuesto.setText(admin.getPuestoStr());
			
		}
		
		
		
		
	}
	


	
	/*ERRORES/TAREAS
	 * Inicio de sesion admins
	 * Ventana edit admins
	 * Admins: implemeta al heredar de Usuario ya el compare to?
	 *
	  */
	

	
	

}

