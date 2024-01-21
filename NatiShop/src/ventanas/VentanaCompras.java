package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import clases.Articulo;
import clases.Cliente;
import clases.Compra;
import clases.Tienda;

public class VentanaCompras extends JFrame {
    private JPanel pCentro, pSur;
    private JButton btnVolver, btnComprar;
    private JFrame vActual, vAnterior;

    private static DefaultTableModel modeloTablaCompras;
    private static JTable tablaCompras;
    private JScrollPane scrollTablaCompras;
    private BD bd;
    private Cliente c;


    public VentanaCompras(JFrame va) {
        super();
        vActual = this;
        vAnterior = va;
        setResizable(false);
        setBounds(300, 200, 600, 400);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);	
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);

        pCentro = new JPanel(new BorderLayout());
        pSur = new JPanel();
        getContentPane().add(pSur, BorderLayout.SOUTH);

        Object[] titulos = { "ID", "ARTICULO", "DESCRIPCION", "TALLA", "CANTIDAD", "PRECIO"};
        setModeloTablaCompras(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4)
                    return true;
                return false;
            }
        });
        getModeloTablaCompras().setColumnIdentifiers(titulos);
        tablaCompras = new JTable(getModeloTablaCompras());
        scrollTablaCompras = new JScrollPane(tablaCompras);
        pCentro.add(scrollTablaCompras, BorderLayout.CENTER);
        pCentro.add(new JPanel(),  BorderLayout.EAST );
        pCentro.add(new JPanel(),  BorderLayout.WEST );
        getContentPane().add(pCentro, BorderLayout.CENTER);
        
        cargarArticuloTabla();

        this.tablaCompras.setRowHeight(80);
        
        bd = new BD();

        // Configurar el editor de la columna "CANTIDAD" para usar JSpinner
        TableColumnModel columnModel = tablaCompras.getColumnModel();
        TableColumn cantidadColumna = columnModel.getColumn(4);

        SpinnerEditor spinnerEditor = new SpinnerEditor();
        cantidadColumna.setCellEditor(spinnerEditor);
        cantidadColumna.setCellRenderer(spinnerEditor);

        tablaCompras.setDefaultRenderer(Object.class, new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                if (column == 1) {
                    try {
                        ImageIcon originalIcon = (ImageIcon) getModeloTablaCompras().getValueAt(row, column);
                        int nuevoAncho = 80;
                        int nuevoAlto = 80;

                        Image imagenOriginal = originalIcon.getImage();
                        Image imagenRedimensionada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto,
                                Image.SCALE_SMOOTH);

                        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

                        JLabel label = new JLabel(iconoRedimensionado);
                        label.setOpaque(true);
                        return label;

                    } catch (Exception e) {
                        e.printStackTrace();
                        JLabel errorLabel = new JLabel("Imagen no disponible");
                        errorLabel.setOpaque(true);
                        return errorLabel;
                    }

                } else {
                    JLabel l = new JLabel(value.toString());
                    l.setOpaque(true);
                    return l;
                }
            }

        });

        btnVolver = new JButton("VOLVER");
        pSur.add(btnVolver);

        btnVolver.addActionListener((e) -> {
        	dispose();
        });

        btnComprar = new JButton("COMPRAR");
        pSur.add(btnComprar);

        btnComprar.addActionListener((e) -> {
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      	Date date = new Date(); // Obtén la fecha actual
  		String f_compra = sdf.format(date);
      	Compra nuevaCompra = new Compra(obtenerClienteActual(), f_compra, Tienda.getCestaPorCliente().get(obtenerClienteActual()), obtenerPrecioCompra());
      	
      	Cliente c= obtenerClienteActual();
        ArrayList<Articulo> articulosComprados = Tienda.getCestaPorCliente().get(c);
        
      	 int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres finalizar la compra?", "Confirmar compra", JOptionPane.YES_NO_OPTION);

           // Comprueba la respuesta del usuario
           if (respuesta == JOptionPane.YES_OPTION) {
          	Connection con = BD.initBD("NatiShop.db");
           	boolean correcto = bd.anyadirCompra(con, nuevaCompra);
           	if (correcto) {
           		JOptionPane.showMessageDialog(null, "Compra finalizada. Gracias por tu compra.");
                System.out.println(obtenerPrecioCompra() );
                getModeloTablaCompras().setRowCount(0);
                  
                   
                for(Articulo  a: articulosComprados) {
	                Tienda.aniadirCompraCliente(c, a);
	                int uCompradas = obtenerUnidadesCompradas(a, articulosComprados);
	                int uActuales = a.getUnidades();
	                a.setUnidades(uActuales - uCompradas);
                 
	                }
	           	}
	               
           } else {
               JOptionPane.showMessageDialog(null, "Compra cancelada.");
           }
         //AÑADIR METODO ANIADIRCOMPRAS
//         Cliente clienteActual = obtenerClienteActual();
//         ArrayList<Articulo> articulosSeleccionados = obtenerArticulosSeleccionados();
//         Tienda.getCompras().put((Cliente) clienteActual, articulosSeleccionados);
           
          
          
           
      	
           
      });
  }
        	
/**
 * Método que craga los articulos que el cliente, que ha iniciado sesion ha seleccionado
 */
    private void cargarArticuloTabla() {
        if (VentanaPrincipal.isClienteHaIniciadoSesion()) {
            for (Cliente c : Tienda.getCestaPorCliente().keySet()) {
                List<Articulo> aCesta = Tienda.getCestaPorCliente().get(c);
                for (Articulo a : aCesta) {
                    try {
                        ImageIcon icono = new ImageIcon(getClass().getResource(a.getFoto()));
                        Object[] fila = {a.getId(), icono, a.getNombre(), a.getTallaStr(), a.getCantidadSeleccionada(), a.getPrecioPorUnidad()};
                        getModeloTablaCompras().addRow(fila);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    
    static class SpinnerEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        private final JSpinner spinner;

        public SpinnerEditor() {
            spinner = new JSpinner();
            spinner.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));  // Establecer modelo para evitar NullPointerException
            spinner.addChangeListener(e -> {
                int fila = tablaCompras.getEditingRow();
                int columna = tablaCompras.getEditingColumn();
                if (fila != -1 && columna == 4) {
                    int nuevaCantidad = (int) spinner.getValue();
                    tablaCompras.getModel().setValueAt(nuevaCantidad, fila, columna);
                    
                    // Actualizar la cantidadSeleccionada en la instancia de Articulo
                    String idArticulo = (String) tablaCompras.getModel().getValueAt(fila, 0);
                    Articulo a = obtenerArticuloPorID(idArticulo); // Ajusta esto según la estructura de tu código
                    a.setCantidadSeleccionada(nuevaCantidad);

                    float precioUnitario = a.getPrecio();
                    float nuevoPrecio = nuevaCantidad * precioUnitario;
                    tablaCompras.getModel().setValueAt(nuevoPrecio, fila, 5);
                    a.setPrecioPorUnidad(nuevoPrecio);

                    // Notificar que se ha realizado la edición
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            int cantidad = (int) value;
            spinner.setValue(cantidad);
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JSpinner newSpinner = new JSpinner();  // Crear un nuevo JSpinner para cada fila
            newSpinner.setValue(value);
            return newSpinner;
        }
    }



    private Cliente obtenerClienteActual() {
        return VentanaInicioSesion.getCliente();
    }

//    private ArrayList<Articulo> obtenerArticulosSeleccionados() {
//        ArrayList<Articulo> comprasUsuario = new ArrayList<>();
//        for (int fila = 0; fila < getModeloTablaCompras().getRowCount(); fila++) {
//            Articulo articulo = (Articulo) getModeloTablaCompras().getValueAt(fila, 0);
//            comprasUsuario.add(articulo);
//        }
//        return comprasUsuario;
//    }

	public static DefaultTableModel getModeloTablaCompras() {
		return modeloTablaCompras;
	}

	public static void setModeloTablaCompras(DefaultTableModel modeloTablaCompras) {
		VentanaCompras.modeloTablaCompras = modeloTablaCompras;
	}
	
	
	
	private static Articulo obtenerArticuloPorID(String id) {
	    for (Cliente c : Tienda.getCestaPorCliente().keySet()) {
	        List<Articulo> aCesta = Tienda.getCestaPorCliente().get(c);
	        for (Articulo a : aCesta) {
	            if (a.getId() == id) {
	                return a;
	            }
	        }
	    }
	    return null; // Manejo del caso en que no se encuentra el Articulo
	}
	
	private static float obtenerPrecioCompra() {
		float precioTotal = (float) 0.0;

	    for (int fila = 0; fila < getModeloTablaCompras().getRowCount(); fila++) {
	    	float valor = (float) getModeloTablaCompras().getValueAt(fila, 5);
	        precioTotal += valor;
	        
	    }

	    return precioTotal;
		
	}
    
	private  int obtenerUnidadesCompradas (Articulo a, ArrayList<Articulo> cesta) {
		for (Articulo articulo : cesta) {
			if(articulo.equals(a)) {
				return articulo.getUnidades();
			}
		}
		return 0;
		
	}
	
    
    
    
}





