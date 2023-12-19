
package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import clases.Articulo;
import clases.Cliente;
import clases.Tienda;
import clases.Usuario;

public class VentanaCompras extends JFrame {
	private JPanel pCentro,pSur;
	private JButton btnVolver, btnComprar, btnAniadirArticuloAlCarrito;
	private JFrame vActual,vAnterior;
	
	private DefaultTableModel modeloTablaCompras; 
	private JTable tablaCompras; 
	private JScrollPane scrollTablaCompras;
	private JButton btnMas, btnMenos;
	private JSpinner sCantidad;
	
	
	private static int fila;
	
	
	public VentanaCompras(JFrame va) {
		super();
		vActual = this;
		vAnterior = va;	
		setResizable(false);
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);	
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	    int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	    setSize(anchoP, altoP);
	    setExtendedState(MAXIMIZED_BOTH);
		
		
		btnMas = new JButton("+");
        btnMenos = new JButton("-");
        
		pSur = new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		Object [] titulos = {"ARTICULO","CANTIDAD","PRECIO"};
		modeloTablaCompras = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column==1)
					return true;
				return false;
			}
		};
		modeloTablaCompras.setColumnIdentifiers(titulos);
		tablaCompras = new JTable(modeloTablaCompras);
		
		
		this.tablaCompras.setRowHeight(80);
		
		tablaCompras.setDefaultRenderer(Object.class, new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if(column==0) {
		            String imagePath = (String) value;
		            ImageIcon originalIcon = createImageIcon(imagePath);

		            if (originalIcon != null) {
		                // Tamaño deseado para la imagen (ajusta según tus necesidades)
		                int width = 30;
		                int height = 30;

		                // Redimensionar la imagen al tamaño deseado
		                Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		                ImageIcon resizedIcon = new ImageIcon(resizedImage);

		                JLabel l = new JLabel(resizedIcon);
		                l.setOpaque(true);
		                return l;
		            } else {
		                // Si no se puede cargar la imagen, muestra el valor como texto
		                JLabel l = new JLabel(value.toString());
		                l.setOpaque(true);
		                return l;
		            }
				}else if(column==1) {
					JSpinner sCantidad = new JSpinner(new SpinnerNumberModel(Integer.parseInt(value.toString()), 0, 100, 1));
					sCantidad.addChangeListener(e -> actualizarPrecioFila());
					return sCantidad;
					//JLabel l = new JLabel(value.toString());
					//return l;
				}else {
					JLabel l = new JLabel(value.toString());
					l.setOpaque(true);
					return l;
				}
			}
		});
		
		

          
		scrollTablaCompras = new JScrollPane(tablaCompras);
		getContentPane().add(scrollTablaCompras, BorderLayout.CENTER);
		
		cargarTabla();
		
		//NUEVA TABLA PARA LOS FAVORITOS
		
		btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		
		btnVolver.addActionListener((e)->{
			vAnterior.setVisible(true);
			vActual.dispose();
		});
		
		
		
		/*TableColumnModel columnModel = tablaCompras.getColumnModel();
		columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(1).setCellRenderer(new SpinnerRenderer());
        columnModel.getColumn(1).setCellEditor(new SpinnerEditor()); */  
		
		

		btnComprar = new JButton("COMPRAR");
		pSur.add(btnComprar);
		
		btnComprar.addActionListener((e)->{
		    Cliente clienteActual = obtenerClienteActual();
		    ArrayList<Articulo> articulosSeleccionados = obtenerArticulosSeleccionados();
		    Tienda.getCompras().put((Cliente) clienteActual, articulosSeleccionados);
		 
		   // modeloTablaCompras.setRowCount(0);	// hago que una vez realizada la compra se borre
		    //new VentanaProcesarElPago();
		    //Tienda.getCompras().put((Cliente) usuarioActual, articulosSeleccionados);
			//Tienda.getCompras().put(VentanaInicioSesion.getCliente(), VentanaInicioSesion.getCarrito());
		    modeloTablaCompras.setRowCount(0);	// hago que una vez realizada la compra se borre
		    //new VentanaProcesarElPago();
		    System.out.println("La compra se ha realizado correctamente.");
		});
		
		
		
        
		setVisible(true);
	
	}
	
	
	private Cliente obtenerClienteActual() {
    	 return new Cliente();
    }
	 
	private ArrayList<Articulo> obtenerArticulosSeleccionados() {
		ArrayList<Articulo> comprasUsuario = new ArrayList<>();
	    for (int fila = 0; fila < modeloTablaCompras.getRowCount(); fila++) {
	    	Articulo articulo = (Articulo) modeloTablaCompras.getValueAt(fila, 0);
	    	comprasUsuario.add(articulo);
	    }
	    return comprasUsuario;
	}
	
	public void agregarArticuloAlCarrito(Articulo articulo) {
	    ImageIcon icono = new ImageIcon(getClass().getResource(articulo.getFoto()));
	    sCantidad = new JSpinner(); 
	    sCantidad.addChangeListener(e -> actualizarPrecioFila());

	    Object[] fila = {articulo.getFoto(), 1, articulo.getPrecio()};	
		//Object[] fila = {articulo, 1, articulo.getPrecio()};	lo que tiene por defecto
        modeloTablaCompras.addRow(fila);
	}
	
	

	 

	 
    
    
	/*private void cargarTabla() {
		for(Usuario u: Tienda.getCompras().keySet()) {
			//ArrayList<Articulo> comprasUsuario = new ArrayList<>();
			for(Articulo a: Tienda.getCompras().get(u)) {
				sCantidad.addChangeListener(e -> actualizarPrecioFila());
				ImageIcon icono = new ImageIcon(imagen);
				Object [] fila = {icono, sCantidad,a.getPrecio()};	//CARGAR LA IMAGEN
				modeloTablaCompras.addRow(fila);
				// comprasUsuario.add(a);
			}
			  compras.put(u, comprasUsuario);
		}
	}*/
	
	public void cargarTabla() {
	    //modeloTablaCompras.setRowCount(0);
	    Cliente clienteActual = obtenerClienteActual();
	    ArrayList<Articulo> articulosCarrito = Tienda.getCompras().get(clienteActual);

	    if (articulosCarrito != null) {
	        for (Articulo articulo : articulosCarrito) {
	        	//agregarArticuloALaTabla(articulo);
	        	sCantidad.addChangeListener(e -> actualizarPrecioFila());
	            ImageIcon icono = new ImageIcon(getClass().getResource(articulo.getFoto()));
	            Object[] fila = {icono, articulo.getUnidades(), articulo.getPrecio()};	//pero aqui no va a salir el precio actualizado en la 3 columna no ??
	            modeloTablaCompras.addRow(fila);
	        }
	    	//tablaCompras.setModel(new ModeloTablaCompras(articulosCarrito));
	    }
	}
	
	private void actualizarPrecioFila() {
	    for (int fila = 0; fila < modeloTablaCompras.getRowCount(); fila++) {
	        JSpinner sCantidad = (JSpinner) modeloTablaCompras.getValueAt(fila, 1);
	        int cantidad = (int) sCantidad.getValue();
	        double precioUnitario = (double) modeloTablaCompras.getValueAt(fila, 2);
	        double nuevoPrecio = cantidad * precioUnitario;
	        modeloTablaCompras.setValueAt(nuevoPrecio, fila, 2);
	    }
		
		
	}
	
	 
    private ImageIcon createImageIcon(String imagePath) {
        try {
            // Obtener la URL del archivo local usando el protocolo "file:"
            URL url = getClass().getResource(imagePath);
            return new ImageIcon(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }   

	
	
	




	
	
};








