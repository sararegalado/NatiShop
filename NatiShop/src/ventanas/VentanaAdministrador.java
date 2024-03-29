package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.toedter.calendar.JCalendar;

import clases.Articulo;
import clases.Cliente;
import clases.Compra;
import clases.Puesto;
import clases.Administrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class VentanaAdministrador extends JFrame{

	private JPanel pnlOesteMenu,pnlCentro,pnlOesteArriba,pnlDatos,pnlDatosTitulo,pnlDatosArriba,pnlDatosBotones,pnlEstadisticas, pnlEsOesteArriba,pnlEsOesteAbajo, pnlEsEsteArriba, pnlEsEsteAbajo;

	private JMenuBar menuBarAdmin;
	private JMenu menuClientes,menuArticulos, menuEstadisticas, menuCompras;
	private JMenuItem mItemRegistros,mItemArticulos,mItemStock,mItemCompras, mItemCalendar, mItemGraficos;
	private JLabel lblFoto,lblTitulo,lblDNI,lblnom,lblApellido,lblCorreo,lbltfn,lblProvincia,lblFnac,lblFinic,lblJornada,lblPuesto,lblModifJornada,lblSolicitudes,lblAñadirAdmin,lblTituloOesteArriba,lblTituloEsteArriba,lblTituloOesteAbajo, lblTituloEsteAbajo,lblClienteEstrella;

	private static JLabel lblTop1;

	private static JLabel lblTop2;

	private static JLabel lblTop3;

	private JTextField tfDNI, tfnom, tfApellido, tfCorreo, tfTfn, tfProvincia, tfFnac, tfnInic, tfPuesto;
	private JButton btnDesplegar, btnAñadirAdmin;


	private static JTextField tfJornada;

	
	private JTable tClientes, tStock;
	private ModeloTablaClientes mClientes;
	private ModeloTablaStock mStock;
	private JScrollPane sTablaClientes, sTablaStock;
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
        lblSolicitudes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new VentanaARSolicitud();
				
			}
		});


        lblModifJornada = new JLabel("<html><u>" + "MODIFICAR JORNADA" + "</u></html>");
        lblModifJornada.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
        pnlDatosBotones.add(lblModifJornada);
        lblModifJornada.setHorizontalAlignment(lblModifJornada.CENTER);
        
       

        
        lblModifJornada.addMouseListener(new MouseAdapter() {
        	/**
             * Este método se activa cuando se produce un evento de clic de ratón.
             * Muestra un cuadro de diálogo preguntando al usuario si desea solicitar un cambio de jornada laboral.
             * @param e El evento de clic del ratón que activa el método.
             */
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
        
        lblAñadirAdmin.addMouseListener(new MouseAdapter () {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		abrirVentanaNuevoAdmin();
        	}
        });
       
        pnlCentro = new JPanel(new BorderLayout());
		getContentPane().add(pnlCentro, BorderLayout.CENTER);
        
		//CREACION DE LA TABLA Stock
		mStock = new ModeloTablaStock(new ArrayList<>());
		tStock = new JTable(mStock);
		sTablaStock = new JScrollPane(tStock);
		
		pnlEstadisticas = new JPanel();
		pnlEstadisticas.setLayout(new GridLayout(2,1));
		
		
		pnlEsOesteArriba = new JPanel();
		pnlEsOesteArriba.setLayout(new GridLayout(2,1));
		pnlEsOesteArriba.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    	pnlEsOesteArriba.setBackground(new Color(220, 220, 220));
    	
   
    	lblTituloOesteArriba = new JLabel("<html><u>" + "CLIENTE ESTRELLA" + "</u></html>");
		lblTituloOesteArriba.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 14));
		lblTituloOesteArriba.setHorizontalAlignment(JLabel.CENTER);
		pnlEsOesteArriba.add(lblTituloOesteArriba);
		
		lblClienteEstrella = new JLabel(" ");
		pnlEsOesteArriba.add(lblClienteEstrella);
		lblClienteEstrella.setHorizontalAlignment(JLabel.CENTER);
		
		
		pnlEsOesteAbajo = new JPanel();
		pnlEsOesteAbajo.setLayout(new GridLayout(4,1));
    	pnlEsOesteAbajo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    	pnlEsOesteAbajo.setBackground(new Color(220, 220, 220));
    	
    	lblTituloOesteAbajo = new JLabel("<html><u>" + "TOP ARTICULOS" + "</u></html>");
		lblTituloOesteAbajo.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 14));
		lblTituloOesteAbajo.setHorizontalAlignment(JLabel.CENTER);
		pnlEsOesteAbajo.add(lblTituloOesteAbajo);
    	

    	lblTop1 = new JLabel(" ");
    	lblTop2 = new JLabel(" ");
    	lblTop3 = new JLabel(" ");
    	
    	lblTop1.setHorizontalAlignment(JLabel.CENTER);
    	lblTop2.setHorizontalAlignment(JLabel.CENTER);
    	lblTop3.setHorizontalAlignment(JLabel.CENTER);
    	
    	pnlEsOesteAbajo.add(lblTop1);
    	pnlEsOesteAbajo.add(lblTop2);
    	pnlEsOesteAbajo.add(lblTop3);
    	
    	pnlEstadisticas.add(pnlEsOesteArriba);
    	pnlEstadisticas.add(pnlEsOesteAbajo);
    	
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
		
		
		
		mItemStock = new JMenuItem("GESTION DE STOCK");
		mItemStock.setFont(new Font("Calibri", Font.BOLD, 15));
		menuArticulos.add(mItemStock);
		mItemStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlCentro.removeAll();
				pnlCentro.revalidate();
				pnlCentro.repaint();
				//cargarArbol();
				//pnlCentro.setVisible(true);
				DefaultMutableTreeNode raiz= new DefaultMutableTreeNode();
			    DefaultMutableTreeNode Jersey = new DefaultMutableTreeNode("JERSEY");
			    DefaultMutableTreeNode Camiseta = new DefaultMutableTreeNode("CAMISETA");
			    DefaultMutableTreeNode Calzado = new DefaultMutableTreeNode("CALZADO");
			    DefaultMutableTreeNode Pantalon = new DefaultMutableTreeNode("PANTALON");
			    modeloArbolArticulos = new DefaultTreeModel (raiz);
			    modeloArbolArticulos.insertNodeInto(Calzado, raiz, 0);
			    modeloArbolArticulos.insertNodeInto(Jersey, raiz, 1);
			    modeloArbolArticulos.insertNodeInto(Camiseta, raiz, 2);
			    modeloArbolArticulos.insertNodeInto(Pantalon, raiz, 3);
				arbolArticulos = new JTree(modeloArbolArticulos);
				int anchoArbol = 200;
				int altoArbol= 130;  
			    arbolArticulos.setPreferredSize(new Dimension(anchoArbol, altoArbol));
				sArbolArticulos = new JScrollPane(arbolArticulos);
			    arbolArticulos.setCellRenderer(new arbolArticulosRenderer());
				sArbolArticulos.setVisible(true);
			    pnlCentro.add(arbolArticulos, BorderLayout.WEST);
			    pnlCentro.setVisible(true);
			    System.out.println("Arbol cargado");
				arbolArticulos.setVisible(true);
				
				arbolArticulos.addTreeSelectionListener(new TreeSelectionListener() {
					
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						System.out.println("ARBOL");
						TreePath tp = e.getPath();
						String categoria= tp.getLastPathComponent().toString();
						
						Connection con = BD.initBD("Natishop.db");
						Set<Articulo>articulos = BD.obtenerListaArticulos(con);
						BD.closeBD(con);
						
						List<Articulo> articulosTabla = new ArrayList<>();
						for(Articulo a: articulos) {
							if(a.getCategoriaStr().equals(categoria)) {
								articulosTabla.add(a);
								
							}
						}
						tStock.setModel(new ModeloTablaStock(articulosTabla));
						cargarTablaStock();
					}
					
				});
			   
				
				pnlCentro.setVisible(true);
			
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
		
		mItemGraficos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlCentro.removeAll();
				pnlCentro.revalidate();
				pnlCentro.repaint();
				pnlCentro.add(pnlEstadisticas);
				TresArticulosMasComprados();
				Connection con = BD.initBD("NatiShop.db");
		        List<Compra> compras = BD.obtenerComprasTotales(con);
		        BD.closeBD(con);

		        clienteQueMasHaComprado(compras);
				
				
			}
		});
		
		mClientes = new ModeloTablaClientes(new ArrayList<>());
		tClientes = new JTable(mClientes);
		sTablaClientes = new JScrollPane(tClientes);
		
//		cargarArbol();
		
		
	//LISTENERS
		
		/**
		 * Escucha de eventos de clic del ratón a la tabla tClientes.
		 * Cuando se hace clic en un elemento de esta tabla, se ejecuta el método mouseClicked, que recopila 
		 * y muestra información relacionada con el elemento seleccionado.
		 * @param e El evento de clic del ratón que contiene información sobre el lugar del clic.
		 */
		tClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p= e.getPoint();
				int fila= tClientes.rowAtPoint(p);
				String dni = tClientes.getModel().getValueAt(fila, 0).toString();
				String texto = "";
				
				Connection con = BD.initBD("NatiShop.db");
				Cliente c = BD.buscarCliente(con, dni);
	            List<Compra> compras = BD.getComprasPorCliente(con, c);
	            BD.closeBD(con);
	            
	            JTextArea textArea = new JTextArea(15, 40);
	            textArea.setEditable(false);
	            JScrollPane scroll = new JScrollPane(textArea);
	            JPanel panel = new JPanel();
	            
	            
	            for (Compra com : compras) {
	            	textArea.append(com.toString()+ "\n");
	            }
	            panel.add(scroll);
	            
	            JOptionPane.showMessageDialog(
	                    null,
	                    panel,
	                    "Lista de Compras",
	                    JOptionPane.PLAIN_MESSAGE);
	        
			}
		});
		
		
		
		setVisible(true);
		
		
	}
	
	protected void abrirVentanaNuevoAdmin() {
		VentanaNuevoAdmin ventanaNuevoAdmin = new VentanaNuevoAdmin(this);
		ventanaNuevoAdmin.setVisible(true);
		
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

	
	/**
	 * Crea una interfaz con un componente JCalendar para mostrar un calendario y una tabla JTable para visualizar compras. 
	 * Cuando se selecciona una fecha en el calendario, se actualiza la tabla con las compras correspondientes para esa fecha. 
	 * La fecha seleccionada se obtiene mediante un escuchador de eventos en el JCalendar.
	 */


	/**
	 * Método que Cargar la tabla con rodos los articulos de la tiwnda y sus unidades disponible
	 * 
	 */
	public void cargarTablaStock() {
		Connection con = BD.initBD("Natishop.db");
		Set<Articulo>a = BD.obtenerListaArticulos(con);
		BD.closeBD(con);
		pnlCentro.add(sTablaStock, BorderLayout.CENTER);
		JLabel lblStock = new JLabel("<html><u>" + "STOCK" + "</u></html>");
		lblStock.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 30));
		lblStock.setHorizontalAlignment(JLabel.CENTER);
		pnlCentro.add(lblStock, BorderLayout.NORTH);
		pnlCentro.setVisible(true);
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

	/**
	 * Carga en una tabla las compras realizadas en una fecha específica
	 * @param fecha_Selecionada La fecha seleccionada para filtrar las compras.
	 * @param tablaCompras El JTable donde se mostrarán las compras.
	 */

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

	
	/*
		Método que carga los datos del Administrador registrado
	 
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
		int anchoArbol = 200;
		int altoArbol= 130;  
	    arbolArticulos.setPreferredSize(new Dimension(anchoArbol, altoArbol));
		sArbolArticulos = new JScrollPane(arbolArticulos);
	    arbolArticulos.setCellRenderer(new arbolArticulosRenderer());
		sArbolArticulos.setVisible(true);
	    pnlCentro.add(arbolArticulos, BorderLayout.WEST);
	    pnlCentro.setVisible(true);
	    System.out.println("Arbol cargado");
		
	}

	public static JTextField getTfJornada() {
		return tfJornada;
	}
	
	//MÉTODOS ESTADISTISCAS
	
	
 	

    private void ClienteEstrella(Cliente cliente) {

        if (cliente != null) {
            JLabel lblCliente = new JLabel("DNI: " + cliente.getDni() + ", Nombre: " + cliente.getNombre());
            lblCliente.setFont(new Font("Calibri", Font.PLAIN, 16));
            lblCliente.setHorizontalAlignment(JLabel.CENTER);
            
            pnlEsOesteArriba.setLayout(new GridLayout(2, 1));
            pnlEsOesteArriba.add(lblTitulo);
            pnlEsOesteArriba.add(lblCliente);
            
            
            
        } else {
            JLabel lblNoCompras = new JLabel("No hay compras registradas.");
            lblNoCompras.setFont(new Font("Calibri", Font.PLAIN, 16));
            lblNoCompras.setHorizontalAlignment(JLabel.CENTER);
            pnlEsOesteArriba.setLayout(new GridLayout(1, 1));
            pnlEsOesteArriba.add(lblNoCompras);
        }

       
	
}

	private void clienteQueMasHaComprado(List<Compra> compras) {
        Map<Cliente, Integer> frecuenciaClientes = new HashMap<>();

        for (Compra c : compras) {
            Cliente cliente = c.getCliente();
            int frecuenciaActual = frecuenciaClientes.getOrDefault(cliente, 0);
            frecuenciaClientes.put(cliente, frecuenciaActual + 1);
        }

        Cliente clienteMasCompras = null;
        int maxFrecuencia = 0;

        for (Map.Entry<Cliente, Integer> entry : frecuenciaClientes.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                clienteMasCompras = entry.getKey();
                
            }
            
        }
        if (clienteMasCompras != null) {
            lblClienteEstrella.setText(clienteMasCompras.getNombre());
        } else {
            // Manejar el caso en que no hay clientes o todos los clientes son null
            lblClienteEstrella.setText("NO HAY CLIENTE");
        }

      
    }
	
	
	/**
	 * Metodo que calcula cuantas veces se ha comprado un articulo
	 * @param compras --> Arraylist con todas las compras que se han realizado
	 * @param a --> Articulo que vamos a analizar cuatas veces se ha comprado
	 * @return frecuencia --> Devuelve cuantas veces se ha comprado ese articulo 
	 */
	public static int FrecuenciaCompraArticulos(List<Compra>compras, Articulo a) {
		int frecuencia= 0;
		for (Compra c: compras) {
			for(Articulo art: c.getArticulos() ) {
				if(art.equals(a)) {
					frecuencia ++;
				}
			}
		}
		return frecuencia;
		
	}
	
	
	public static Articulo  ArticuloMasComprado() {
		Connection con = BD.initBD("NatiShop.db");
		List<Compra> compras = BD.obtenerComprasTotales(con);
		BD.closeBD(con);
		Articulo aMasComprado = null;
		int  maxFrecuencia = 0;
		for(Compra c: compras) {
			for(Articulo a: c.getArticulos()) {
				int frecAhora = FrecuenciaCompraArticulos(compras, a);
				if(frecAhora > maxFrecuencia) {
					maxFrecuencia = frecAhora;
					aMasComprado= a;
					System.out.println(aMasComprado);
					
				}
			}   
		}
		
		return aMasComprado;
		
		
	}
	
	public static void   TresArticulosMasComprados(){
		Connection con = BD.initBD("NatiShop.db");
		List<Compra> compras = BD.obtenerComprasTotales(con);
		BD.closeBD(con);
		
		List<Articulo>articulosMasComprados = new ArrayList<>();
		
		for(int i = 0; i<3; i++) {
			Articulo aMasComprado= ArticuloMasComprado();
			if(aMasComprado != null) {
				articulosMasComprados.add(aMasComprado);
			}else {
				articulosMasComprados.add(null);
			}
		if(!articulosMasComprados.isEmpty()) {
			 Articulo articulo1 = articulosMasComprados.get(0);
			 Articulo articulo2 = articulosMasComprados.size() > 1 ? articulosMasComprados.get(1) : null;
			 Articulo articulo3 = articulosMasComprados.size() > 2 ? articulosMasComprados.get(2) : null;
			lblTop1.setText("TOP1: " + (articulo1 != null ? articulo1.getNombre() : "NO HAY ARTICULOS"));
		    lblTop2.setText("TOP2: " + (articulo2 != null ? articulo2.getNombre() : "NO HAY ARTICULOS"));
		    lblTop3.setText("TOP3: " + (articulo3 != null ? articulo3.getNombre() : "NO HAY ARTICULOS"));
			}
		}
		
		
	}
	
	
	
	

}