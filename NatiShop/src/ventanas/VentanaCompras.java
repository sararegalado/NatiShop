package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import clases.Articulo;
import clases.Cliente;
import clases.Tienda;

public class VentanaCompras extends JFrame {
    private JPanel pCentro, pSur;
    private JButton btnVolver, btnComprar;
    private JFrame vActual, vAnterior;

    private static DefaultTableModel modeloTablaCompras;
    private static JTable tablaCompras;
    private JScrollPane scrollTablaCompras;
    private JButton btnMas, btnMenos;
    private JSpinner sCantidad;

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

        btnMas = new JButton("+");
        btnMenos = new JButton("-");

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
        getContentPane().add(scrollTablaCompras, BorderLayout.CENTER);

        cargarArticuloTabla();

        this.tablaCompras.setRowHeight(80);

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
            Cliente clienteActual = obtenerClienteActual();
            ArrayList<Articulo> articulosSeleccionados = obtenerArticulosSeleccionados();
            Tienda.getCompras().put((Cliente) clienteActual, articulosSeleccionados);

            getModeloTablaCompras().setRowCount(0);
            System.out.println("La compra se ha realizado correctamente.");
        });
    }

    private void cargarArticuloTabla() {
    	if (VentanaPrincipal.isClienteHaIniciadoSesion() == true) {
    		for (Cliente c : Tienda.getCestaPorCliente().keySet()) {
            List<Articulo> aCesta = Tienda.getCestaPorCliente().get(c);
            for (Articulo a : aCesta) {
                try {
                    ImageIcon icono = new ImageIcon(getClass().getResource(a.getFoto()));
                    Object[] fila = {a.getId(), icono, a.getNombre(), a.getTallaStr(), 1, a.getPrecio() };
                    getModeloTablaCompras().addRow(fila);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    	}
        
    }

    
    static class SpinnerEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        private JSpinner spinner;

        public SpinnerEditor() {
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            // Obtener el valor actual de la celda
            int cantidad = (int) value;

            // Configurar un nuevo JSpinner con el valor actual de la celda
            spinner = new JSpinner(new SpinnerNumberModel(cantidad, 1, 100, 1));
            spinner.setFocusable(false);

            // Agregar un cambio de estado al nuevo JSpinner
            spinner.addChangeListener(e -> {
            	int fila = tablaCompras.getEditingRow();
                int columna = tablaCompras.getEditingColumn();
                if (fila != -1) {
                	Connection c = BD.initBD("NatiShop.db");
                    Articulo a = BD.buscarArticulo(c, tablaCompras.getValueAt(fila, 0).toString());
                    BD.closeBD(c);
                    double precioUnitario = a.getPrecio();

                    // Asegurarse de que estamos en la columna correcta (1)
                    if (columna == 4) {
                        // Actualizar la cantidad en la columna 1
                        tablaCompras.getModel().setValueAt(spinner.getValue(), fila, columna);

                        // Obtener el precio unitario original de la columna 3
                        //double precioUnitario = (double) tablaCompras.getValueAt(fila, 5);  // Cambiado de 3 a 2

                        // Calcular y actualizar el precio total en la columna 2
                        double nuevoPrecio = (int) spinner.getValue() * precioUnitario;
                        tablaCompras.getModel().setValueAt(nuevoPrecio, fila, 5);

                        // Notificar que se ha realizado la edición
                        fireEditingStopped();
                    }
                }
                
            });
            
            

            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            // Obtener el valor actual de la celda
            int cantidad = (int) value;

            // Configurar un nuevo JSpinner con el valor actual de la celda
            spinner = new JSpinner(new SpinnerNumberModel(cantidad, 1, 100, 1));
            spinner.setFocusable(false);

            return spinner;
        }
    }



    private Cliente obtenerClienteActual() {
        return VentanaInicioSesion.getCliente();
    }

    private ArrayList<Articulo> obtenerArticulosSeleccionados() {
        ArrayList<Articulo> comprasUsuario = new ArrayList<>();
        for (int fila = 0; fila < getModeloTablaCompras().getRowCount(); fila++) {
            Articulo articulo = (Articulo) getModeloTablaCompras().getValueAt(fila, 0);
            comprasUsuario.add(articulo);
        }
        return comprasUsuario;
    }

	public static DefaultTableModel getModeloTablaCompras() {
		return modeloTablaCompras;
	}

	public static void setModeloTablaCompras(DefaultTableModel modeloTablaCompras) {
		VentanaCompras.modeloTablaCompras = modeloTablaCompras;
	}
    
    
    
    
}





