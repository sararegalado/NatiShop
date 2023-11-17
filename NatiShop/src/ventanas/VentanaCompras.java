package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.Articulo;
import clases.Tienda;
import clases.Usuario;

public class VentanaCompras extends JFrame{
	private JPanel pCentro,pSur;
	private JButton btnVolver;
	private DefaultTableModel modeloTablaCompras; //Guardar las compras en una tabla 
	private JTable tablaCompras; 
	private JScrollPane scrollTablaCompras;
	private int filaRaton; //Declaramos las variables que guardar la fila y columna en la que se encuentra el puntero del ratón
	//quiero hacer un boton + y - para añadir y quitar articulos
	
	public VentanaCompras(JFrame va) {
		super();
		
		filaRaton = -1;
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pCentro = new JPanel();
		pSur = new JPanel();
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		String [] titulos = {"ARTICULO", "ID","PRECIO", "CANTIDAD"};
		modeloTablaCompras = new DefaultTableModel();
		modeloTablaCompras.setColumnIdentifiers(titulos);
		tablaCompras = new JTable(modeloTablaCompras);
		scrollTablaCompras = new JScrollPane(tablaCompras);
		pCentro.add(scrollTablaCompras);
		cargarTabla();
		//QUE NO SEA EDITABLE NADA
		
		/*EVENTOS*/
		/*btnVolver.addActionListener((e)->{
			vAnterior.setVisible(true);
			vActual.dispose();
		});*/
		
		
		tablaCompras.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				filaRaton = tablaCompras.rowAtPoint(p);
				tablaCompras.repaint(); 
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		tablaCompras.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// Se ejecuta cuando soltamos el botón del ratón
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// Se ejecuta cuando presionamos el botón del ratón
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// Se ejecuta cuando el ratón sale del componente
				filaRaton = -1;
				tablaCompras.repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// Se ejecuta cuando el ratón entra al componente
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se ejecuta cuando hacemos click sobre el componente
				
			}
		});
		JButton btnCerrar = new JButton("Cerrar");
	    	btnCerrar.addActionListener(e -> cerrarVentana());
	    	pSur.add(btnCerrar, BorderLayout.SOUTH);

	        add(pSur);

		}
		
		
		setVisible(true);
	}

	private void cerrarVentana() {
	    VentanaTienda.setVisible(true);
	    dispose();
	}
	

	
	private void cargarTabla() {
		for(Usuario u: Tienda.getCompras().keySet()) {
			for(Articulo a: Tienda.getCompras().get(u)) {
				Object [] fila = {u.getDni(),a.getId(),a.getPrecio()};
				modeloTablaCompras.addRow(fila);
			}
		}
	}
	
	public static void main(String[] args) {
        // For testing purposes
        SwingUtilities.invokeLater(() -> {
        	JFrame mainFrame = new JFrame();
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 600);
            mainFrame.setVisible(true);

            // Open the shopping cart window
            new VentanaCompras(mainFrame);
        });
    }
}
