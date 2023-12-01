package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

import clases.Articulo;
import clases.Tienda;
import clases.Usuario;

public class VentanaCompras extends JFrame{
	private JPanel pCentro,pSur;
	private JButton btnVolver, btnComprar;
	private JFrame vActual,vAnterior;
	private JScrollPane scroll;
	
	private DefaultTableModel modeloTablaCompras; 
	private JTable tablaCompras; 
	private JScrollPane scrollTablaCompras;
	private JSpinner sCantidad;
	private JButton btnMas, btnMenos;
	
	
	public VentanaCompras(JFrame va) {
		super();
		vActual = this;
		vAnterior = va;	//Ventana tienda
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		btnMas = new JButton("+");
        btnMenos = new JButton("-");
		
		pSur = new JPanel();
		pCentro = new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		scroll = new JScrollPane();
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		Object [] titulos = {"ARTICULOO","CANTIDAD","PRECIO ARTÍCULO"};
		modeloTablaCompras = new DefaultTableModel() {	
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column<=1) 
					return true;
				return false;
			}
		};
		modeloTablaCompras.setColumnIdentifiers(titulos);
		tablaCompras = new JTable(modeloTablaCompras);
		//estas 2 filas es para cargar la imagen en la tabla
		//TableColumnModel columnModel = tablaCompras.getColumnModel();
		//columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
		scrollTablaCompras = new JScrollPane(tablaCompras);
		pCentro.add(scrollTablaCompras);
		//cargarTabla();
		
		btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		
		btnVolver.addActionListener((e)->{
			vAnterior.setVisible(true);
			vActual.dispose();
		});
		
		btnComprar = new JButton("COMPRAR");
		pSur.add(btnComprar);
		
		btnComprar.addActionListener((e)->{
		    Usuario usuarioActual = obtenerUsuarioActual();
		    ArrayList<Articulo> articulosSeleccionados = obtenerArticulosSeleccionados();
		    Tienda.getCompras().put(usuarioActual, articulosSeleccionados);
		    modeloTablaCompras.setRowCount(0);	// hago que una vez realizada la compra se borre
		    new VentanaProcesarElPago();
		    System.out.println("La compra se ha realizado correctamente.");
		});
		
		
		
		TableColumnModel columnModel = tablaCompras.getColumnModel();
        columnModel.getColumn(1).setCellRenderer(new SpinnerRenderer());
        columnModel.getColumn(1).setCellEditor(new SpinnerEditor());   
        
		setVisible(true);
	
	}
	
	

	private Usuario obtenerUsuarioActual() {
    	 return new Usuario();
    }
	 
	private ArrayList<Articulo> obtenerArticulosSeleccionados() {
		ArrayList<Articulo> comprasUsuario = new ArrayList<>();
	    for (int fila = 0; fila < modeloTablaCompras.getRowCount(); fila++) {
	    	Articulo articulo = (Articulo) modeloTablaCompras.getValueAt(fila, 0);
	    	comprasUsuario.add(articulo);
	    }
	    return comprasUsuario;
	}
	

    // Clase interna para personalizar el editor del JSpinner en la tabla
    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel = new JPanel();
        private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
        private final JButton btnMas = new JButton("+");
        private final JButton btnMenos = new JButton("-");

        public SpinnerEditor() {
            panel.setLayout(new BorderLayout());
            panel.add(spinner, BorderLayout.CENTER);
            panel.add(btnMas, BorderLayout.EAST);
            panel.add(btnMenos, BorderLayout.WEST);

            btnMas.addActionListener(e -> spinner.setValue((double) spinner.getValue() + 1));
            btnMenos.addActionListener(e -> spinner.setValue((double) spinner.getValue() - 1));
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
	
	/*private void cargarTabla() {
		for(Usuario u: Tienda.getCompras().keySet()) {
			//ArrayList<Articulo> comprasUsuario = new ArrayList<>();
			for(Articulo a: Tienda.getCompras().get(u)) {
				sCantidad.addChangeListener(e -> actualizarPrecioFila());
				Object [] fila = {sCantidad,a.getPrecio()};	//CARGAR LA IMAGEN
				modeloTablaCompras.addRow(fila);
				// comprasUsuario.add(a);
			}
			  compras.put(u, comprasUsuario);
		}
	}*/


	public static void main(String[] args) {
		/*JFrame ventanaTienda = new VentanaTienda();
        VentanaCompras ventanaCompras = new VentanaCompras(ventanaTienda);*/
        SwingUtilities.invokeLater(() -> {
            JFrame ventanaTienda = new VentanaTienda();
            new VentanaCompras(ventanaTienda);
        });
	};
	
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


//EN LA CLASE TIENDA
//private static Map<Usuario, List<Articulo>> compras = new HashMap<>();
	
/*HACER UN JSLIDER EN LA VENTANA TIENDA CON EL BUSCADOR
		sUnidades = new JSlider(0, 200, 0);
		sUnidades.setPaintLabels(true);
		sUnidades.setPaintTicks(true);
		sUnidades.setMinorTickSpacing(5);
		sUnidades.setMajorTickSpacing(20);
		pNorte.add(sUnidades);
		logger.info("JSlider creado");
		

		
//sUnidades es el campo que hemos elegido lara el JSLIDER
sUnidades.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int unidades = sUnidades.getValue();
				//En la tabla se muestre aquellos productos que tengan al menos ese número de unidades
				if(CadenaSupermercados.getMapa().containsKey(supermercado)) {		//clase CadenaSupermercados dnd e¡tenemos el mapa
				//Mapa de clave nombre del supermercado y valor conjunto de productos que se venden en ese supermercado
				//private static Map<String, Set<Producto>> mapa = new TreeMap<>();
					List<Producto> l = CadenaSupermercados.obtenerListaProductos(supermercado);
					List<Producto> lFiltro = new ArrayList<>();
					for(Producto p: l) {
						if(p.getUnidades() >= unidades) {
							lFiltro.add(p);
						}
					}
					tabla.setModel(new ModeloProductos(lFiltro));*/



