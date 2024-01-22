package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    
    private int filaABorrar;


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

        Object[] titulos = { "ID", "ARTICULO", "DESCRIPCION", "TALLA", "PRECIO"};
        setModeloTablaCompras(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
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

        tablaCompras.setRowHeight(80);
        
        bd = new BD();


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
        
        tablaCompras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	int opcion = JOptionPane.showConfirmDialog(
                            JOptionPane.getRootFrame(),
                            "¿Quieres borrar este artículo de tu cesta?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {
                    	 int selectedRow = tablaCompras.getSelectedRow();
                         if (selectedRow != -1) {
                             filaABorrar = selectedRow;
                             modeloTablaCompras.removeRow(filaABorrar);
                             Tienda.getCestaPorCliente().get(VentanaInicioSesion.getCliente()).remove(selectedRow);
                         }
                    } else {
                        // Lógica si el usuario selecciona "Cancelar" o cierra la ventana
                        System.out.println("Eliminación cancelada");
                    }


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
        	
          	Connection co = BD.initBD("NatiShop.db");
           	double saldoCliente = BD.obtenerSaldoCliente(co, obtenerClienteActual().getDni());
           	BD.closeBD(co);
        	if(saldoCliente>= obtenerPrecioCompra() ) {
        		Cliente c= obtenerClienteActual();
                ArrayList<Articulo> articulosComprados = Tienda.getCestaPorCliente().get(c);
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              	Date date = new Date(); // Obtén la fecha actual
          		String f_compra = sdf.format(date);
              	Compra nuevaCompra = new Compra(obtenerClienteActual(), f_compra, Tienda.getCestaPorCliente().get(obtenerClienteActual()), obtenerPrecioCompra());
              	
              	 int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres finalizar la compra?", "Confirmar compra", JOptionPane.YES_NO_OPTION);

                   // Comprueba la respuesta del usuario
                   if (respuesta == JOptionPane.YES_OPTION) {
                  	Connection con = BD.initBD("NatiShop.db");
                   	boolean correcto = bd.anyadirCompra(con, nuevaCompra);
                   	BD.closeBD(con);
                   	if (correcto) {
                   		JOptionPane.showMessageDialog(null, "Compra finalizada. Gracias por tu compra.");
                        System.out.println(obtenerPrecioCompra() );
                        Connection conn = BD.initBD("NatiShop.db");
                        BD.modificarSaldo(conn, obtenerClienteActual().getDni(), saldoCliente - obtenerPrecioCompra());
                        
                        double saldo = saldoCliente - obtenerPrecioCompra();
                        VentanaPrincipal.getLblSaldo().setText(String.format("%.2f€", saldo));
                        Tienda.getCestaPorCliente().put(c, new ArrayList<>());
                        for (Articulo a : articulosComprados) {
                        	BD.modificarUnidsArticulo(conn, a.getId(), a.getUnidades()-1);
                        	
                        }
                        BD.closeBD(conn);
                        getModeloTablaCompras().setRowCount(0);
                   	}
                       
                   } else {
                       JOptionPane.showMessageDialog(null, "Compra cancelada.");
                   }
              	
        		
        	}else {
        		JOptionPane.showMessageDialog(null, "No tienes suficiente saldo para realizar esta compra \n" + 
        					"Saldo actual: " + saldoCliente +"€ \n" + "Precio compra: " + obtenerPrecioCompra() + "€");
        	}

           
      });
  }
        	
/**
 * Método que carga los articulos que el cliente, que ha iniciado sesion ha seleccionado
 */
    private void cargarArticuloTabla() {
        if (VentanaPrincipal.isClienteHaIniciadoSesion()) {
            for (Cliente c : Tienda.getCestaPorCliente().keySet()) {
                List<Articulo> aCesta = Tienda.getCestaPorCliente().get(c);
                for (Articulo a : aCesta) {
                    try {
                        ImageIcon icono = new ImageIcon(getClass().getResource(a.getFoto()));
                        Object[] fila = {a.getId(), icono, a.getNombre(), a.getTallaStr(), a.getPrecio()};
                        getModeloTablaCompras().addRow(fila);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Cliente obtenerClienteActual() {
        return VentanaInicioSesion.getCliente();
    }

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
	    	float valor = (float) getModeloTablaCompras().getValueAt(fila, 4);
	        precioTotal += valor;
	        
	    }

	    return precioTotal;
		
	}
	
    
    
    
}




