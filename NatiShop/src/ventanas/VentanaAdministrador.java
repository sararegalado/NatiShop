package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.toedter.calendar.JCalendar;

import clases.Articulo;
import clases.Cliente;
import clases.Compra;
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
	private JMenuItem mItemRegistros,mItemArticulos,mItemStock,mItemCompras, mItemCalendar, mItemGraficos;
	private JLabel lblFoto,lblTitulo,lblDNI,lblnom,lblApellido,lblCorreo,lbltfn,lblProvincia,lblFnac,lblFinic,lblJornada,lblPuesto,lblModifJornada,lblSolicitudes,lblAñadirAdmin;
	private JTextField tfDNI, tfnom, tfApellido, tfCorreo, tfTfn, tfProvincia, tfFnac, tfnInic, tfJornada, tfPuesto;
	private JButton btnDesplegar;
	
	private JTable tClientes;
	private ModeloTablaClientes mClientes;
	private JScrollPane sTablaClientes;
	private JFrame vActual,vAnterior;
	private Administrador admin;
	

	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private DefaultTreeModel modeloArbolArticulos;
	private JTree arbolArticulos;
	private JScrollPane sArbolArticulos;

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
        
		
       
		
		
		
		//DECLARACION + ACTIONLISTENER DE LOS MENUITEM
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
		mItemStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlCentro.removeAll();
				pnlCentro.revalidate();
				pnlCentro.repaint();
				cargarArbol();
				System.out.println("Item funciona");
				
				
			}
			
		});
		
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
				cargarCalendario();
				
			}
		});
		
		
		
		menuEstadisticas = new JMenu("ESTADISTICAS");
		menuEstadisticas.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuBarAdmin.add(menuEstadisticas);
		
		mItemGraficos = new JMenuItem("Graficos");
		mItemGraficos.setFont(new Font("Calibri", Font.BOLD, 15));
		menuEstadisticas.add(mItemGraficos);
		
		mClientes = new ModeloTablaClientes(new ArrayList<>());
		tClientes = new JTable(mClientes);
		sTablaClientes = new JScrollPane(tClientes);
		
		pnlCentro.setVisible(true);
		
		tClientes.addMouseListener(new MouseAdapter() {
					
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p= e.getPoint();
				int fila= tClientes.rowAtPoint(p);
				String dni = tClientes.getModel().getValueAt(fila, 0).toString();
				String texto = "";
				for(String fecha: Tienda.getComprasPorCliente().get(dni).keySet()) {
					texto = "FECHA: " + fecha + "\n";
					for(Articulo a: Tienda.getComprasPorCliente().get(dni).get(fecha)) {
						texto = texto + a + "\n";
					}
				}
				JOptionPane.showMessageDialog(null, texto);
			}
		});
		
		setVisible(true);
		
		
	}
	
	//METODOS 
	
	/**
	 * Método para cargar los usuarios registrador a la tabla de Usuarios que se vidualiza al pulsar el menuitem "registros"
	 */
	public void cargarTablaUsuarios() {
		Connection con = BD.initBD("NatiShop.db");
		List<Cliente> c = BD.obtenerListaClientes(con);
		BD.closeBD(con);
		tClientes.setModel(new ModeloTablaClientes(c));
		pnlCentro.add(sTablaClientes, BorderLayout.CENTER);
		JLabel lblUsuarios = new JLabel("<html><u>" + "USUARIOS" + "</u></html>");
		lblUsuarios.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 30));
		lblUsuarios.setHorizontalAlignment(JLabel.CENTER);
		pnlCentro.add(lblUsuarios, BorderLayout.NORTH);
		pnlCentro.setVisible(true);
		
		TableCellRenderer cellRenderer = (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) -> {
			JLabel label = new JLabel();
			
			label.setText(value.toString());
			
			
			
			label.setOpaque(true);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBackground(table.getBackground());
			return label;
			
			
		};
		
		tClientes.setDefaultRenderer(Object.class, cellRenderer);
		((DefaultTableCellRenderer) tClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
	}


	public void cargarCalendario() {
		JCalendar calendar = new JCalendar(new Date());
		JPanel pnlCalendar = new JPanel(new GridLayout(2,1));
		pnlCalendar.add(calendar);
		
		JTable tablaCompras = new JTable();
		tablaCompras.setModel(new ModeloTablaCompras(null));
		JScrollPane spTablaCompras = new JScrollPane(tablaCompras);
		
		
		pnlCalendar.add(spTablaCompras);

		pnlCentro.add(pnlCalendar, BorderLayout.CENTER);
		JLabel lblCompras = new JLabel("<html><u>" + "COMPRAS" + "</u></html>");
		lblCompras.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 30));
		lblCompras.setHorizontalAlignment(JLabel.CENTER);
		pnlCentro.add(lblCompras, BorderLayout.NORTH);
		
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date nuevaFechaSeleccionada = calendar.getCalendar().getTime();
                String fecha_Selecionada = sdf.format(nuevaFechaSeleccionada);
                cargarComprasDia (fecha_Selecionada, tablaCompras );

                
            }
        });
		
		
		
		pnlCentro.setVisible(true);
		
		
		
		
		
	}

	

	private void cargarComprasDia(String fecha_Selecionada, JTable tablaCompras) {
		Connection con = BD.initBD("NatiShop.db");
		List<Compra> compras = BD.obtenerComprasTotales(con);
		BD.closeBD(con);
		List<Compra> c_Dia = new ArrayList<>();
		
		for (Compra c : compras) {
			Date date = c.getFecha();
			String f_compra = sdf.format(date);
			if (f_compra.equals(fecha_Selecionada)) {
				c_Dia.add(c);
			}
			
		}
		tablaCompras.setModel(new ModeloTablaCompras(c_Dia));
		
		tablaCompras.getColumnModel().getColumn(3).setCellRenderer(new ArticulosRendererEditor(this));		
		tablaCompras.getColumnModel().getColumn(3).setCellEditor(new ArticulosRendererEditor(this));		
		
		TableCellRenderer cellRenderer = (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) -> {
			JLabel label = new JLabel();
			
			if (column == 2) {
				label.setText(value.toString());
			} else {
				label.setText(value.toString());
	
			}
			
			label.setOpaque(true);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBackground(table.getBackground());
			return label;
			
			
		};
		
		tablaCompras.setDefaultRenderer(Object.class, cellRenderer);
		((DefaultTableCellRenderer) tablaCompras.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		
		
	}

	/**
	 * Método que carga los datos del Administrador registrado
	 
	 */
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
	
	/**
	 * Método que crea el Arbol que aparece al pulsar el mItemStock con los tipos de prendas que tiene la tienda como nodos hijos
	 */
	
	public void cargarArbol() {
		 DefaultMutableTreeNode raiz= new DefaultMutableTreeNode();
	     DefaultMutableTreeNode Jersey = new DefaultMutableTreeNode("JERSEY");
	     DefaultMutableTreeNode Camiseta = new DefaultMutableTreeNode("CAMISETA");
	     DefaultMutableTreeNode Zapato = new DefaultMutableTreeNode("ZAPATO");
	     DefaultMutableTreeNode Pantalon = new DefaultMutableTreeNode("PANTALON");
	     modeloArbolArticulos = new DefaultTreeModel (raiz);
	     modeloArbolArticulos.insertNodeInto(Zapato, raiz, 0);
	     modeloArbolArticulos.insertNodeInto(Jersey, raiz, 1);
	     modeloArbolArticulos.insertNodeInto(Camiseta, raiz, 2);
	     modeloArbolArticulos.insertNodeInto(Pantalon, raiz, 3);
  	     arbolArticulos = new JTree(modeloArbolArticulos);
	     int anchoArbol = 200; // ajusta este valor según tus necesidades
	     arbolArticulos.setPreferredSize(new Dimension(anchoArbol, arbolArticulos.getPreferredSize().height));
	     sArbolArticulos = new JScrollPane(arbolArticulos);
	     arbolArticulos.setCellRenderer(new arbolArticulosRenderer());
	     arbolArticulos.setVisible(true);
	     pnlCentro.add(sArbolArticulos, BorderLayout.WEST);
	     pnlCentro.setVisible(true);
	     System.out.println("Método Fuciona");
		
	}
	

	
	/*ERRORES/TAREAS
	 * Falta llamar  al metodi aniadirCompraCliente() al actioonListener del Boton Comprar -- > Parametros?
	 * 
	 * 
	 * -----
	 * Falta añadir arbol al panel centro 
	 * Hacer tabla y añadir el arbol
	  */
	

	
	

}

