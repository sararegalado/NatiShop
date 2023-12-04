
package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.TableColumnModel;

import clases.Articulo;
import clases.Cliente;
import clases.Tienda;
import clases.Usuario;

public class VentanaCompras extends JFrame{
	private JPanel pCentro,pSur;
	private JButton btnVolver, btnComprar, btnFinalizarCompra, btnAniadirArticuloAlCarrito;
	private JFrame vActual,vAnterior;
	private JScrollPane scroll;
	
	private DefaultTableModel modeloTablaCompras; 
	private JTable tablaCompras; 
	private JScrollPane scrollTablaCompras;
	private JSpinner sCantidad;
	private JButton btnMas, btnMenos;
	private JSpinner sPrecio;
	
	
	private static int fila;
	
	
	public VentanaCompras(JFrame va) {
		super();
		vActual = this;
		vAnterior = va;	
		setResizable(false);
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//setIconImage(new ImageIcon("imagenes/logo.png").getImage());		
		
		
		btnMas = new JButton("+");
        btnMenos = new JButton("-");
        tablaCompras = new JTable();
		
		pSur = new JPanel();
		pCentro = new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		scroll = new JScrollPane();
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		Object [] titulos = {"ARTICULO","CANTIDAD","PRECIO ARTÍCULO"};
		modeloTablaCompras = new DefaultTableModel();
		modeloTablaCompras.setColumnIdentifiers(titulos);
		scrollTablaCompras = new JScrollPane(tablaCompras);
		pCentro.add(scrollTablaCompras);
		cargarTabla();
		
		//NUEVA TABLA PARA LOS FAVORITOS
		
		/*btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		
		btnVolver.addActionListener((e)->{
			vAnterior.setVisible(true);
			vActual.dispose();
		});*/
		
		
		
		class ImageRenderer extends DefaultTableCellRenderer {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        JLabel label = new JLabel();
		        if (value instanceof ImageIcon) {
		            label.setIcon((ImageIcon) value);
		        }
		        return label;
		    }
		}
		
		/*TableColumnModel columnModel = tablaCompras.getColumnModel();
		columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(1).setCellRenderer(new SpinnerRenderer());
        columnModel.getColumn(1).setCellEditor(new SpinnerEditor()); */  
		
		
		
		btnComprar = new JButton("COMPRAR");
		pSur.add(btnComprar);
		
		btnComprar.addActionListener((e)->{
		    Usuario usuarioActual = obtenerClienteActual();
		    ArrayList<Articulo> articulosSeleccionados = obtenerArticulosSeleccionados();
		    Tienda.getCompras().put((Cliente) usuarioActual, articulosSeleccionados);
		 
		   // modeloTablaCompras.setRowCount(0);	// hago que una vez realizada la compra se borre
		    new VentanaProcesarElPago();
		    System.out.println("La compra se ha realizado correctamente.");
		});
		
		/*btnFinalizarCompra.addActionListener((e)->{
			Tienda.getCompras().put(VentanaInicioSesion.getCliente(), VentanaInicioSesion.getCarrito());
			JOptionPane.showMessageDialog(null, "Compra finalizada correctamente");
		});
		*/
		
		
		
		
		
        
		setVisible(true);
	
	}
	
	
	private void agregarArticuloALaTabla(Articulo articuloSeleccionado) {
	    ImageIcon icono = new ImageIcon(getClass().getResource(articuloSeleccionado.getFoto()));
	    JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); 
	    Object[] fila = {icono, 1, articuloSeleccionado.getPrecio()};
	    //, spinner,  articuloSeleccionado.getFoto()
	    modeloTablaCompras.addRow(fila);
	}





	private Usuario obtenerClienteActual() {
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
	
	
	class SpinnerRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        if (value instanceof JSpinner) {
	            return (Component) value;
	        }
	        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    }
	}

    // Clase interna para personalizar el editor del JSpinner en la tabla
    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel = new JPanel();
        private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
        //private final JButton btnMas = new JButton("+");
        //private final JButton btnMenos = new JButton("-");

        public SpinnerEditor() {
        	    
            panel.setLayout(new BorderLayout());
            panel.add(spinner, BorderLayout.CENTER);
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    fireEditingStopped();
                }
            });
           /* panel.add(btnMas, BorderLayout.EAST);
            panel.add(btnMenos, BorderLayout.WEST);

            btnMas.addActionListener(e -> spinner.setValue((double) spinner.getValue() + 1));
            btnMenos.addActionListener(e -> spinner.setValue((double) spinner.getValue() - 1));*/
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return panel;
        }
 

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }
	
        private final JPanel panel = new JPanel();
       

    
    
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
	    modeloTablaCompras.setRowCount(0);
	    Usuario usuarioActual = obtenerClienteActual();
	    ArrayList<Articulo> articulosCarrito = Tienda.getCompras().get(usuarioActual);

	    if (articulosCarrito != null) {
	        for (Articulo articulo : articulosCarrito) {
	        	//agregarArticuloALaTabla(articulo);
	        	sCantidad.addChangeListener(e -> actualizarPrecioFila());
	            ImageIcon icono = new ImageIcon(getClass().getResource(articulo.getFoto()));
	            Object[] fila = {icono, sCantidad, articulo.getPrecio()};
	            modeloTablaCompras.addRow(fila);
	        }
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


	
	
};


//VENTANA TIENDA
/*JButton btnAddToFavorites = new JButton("Añadir a Favoritos");

//Add action listener to the button to mark the item as a favorite
btnAddToFavorites.addActionListener(e -> {
   int selectedRow = tablaTienda.getSelectedRow();
   Articulo selectedArticulo = /* Get the selected Articulo from the table ;
   selectedArticulo.setEsFavorito(true);
});*/








