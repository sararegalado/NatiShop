package ventanas;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import clases.Articulo;
import clases.Tienda;
import clases.Usuario;

public class VentanaCompras extends JFrame{
	private JPanel pCentro,pSur;
	private JButton btnVolver;
	private JFrame vActual,vAnterior;
	
	private DefaultTableModel modeloTablaCompras; 
	private JTable tablaCompras; 
	private JScrollPane scrollTablaCompras;
	//private JSpinner sPrecio;
	
	
	public VentanaCompras(JFrame va) {
		super();
		vActual = this;
		vAnterior = va;	//Ventana tienda
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pSur = new JPanel();
		pCentro = new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		Object [] titulos = {"DNI CLIENTE","ID DEL ARTÍCULO","PRECIO ARTÍCULO"};
		modeloTablaCompras = new DefaultTableModel(); 
		//si queremos editar X columnas:
		/*modeloTabla = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column<=1) //empieza a contar desde 0
					return true;
				return false;
			}
		};*/
		modeloTablaCompras.setColumnIdentifiers(titulos);
		tablaCompras = new JTable(modeloTablaCompras);
		scrollTablaCompras = new JScrollPane(tablaCompras);
		pCentro.add(scrollTablaCompras);
		cargarTabla();
		
		btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		
		btnVolver.addActionListener((e)->{
			vAnterior.setVisible(true);
			vActual.dispose();
		});
	

		setVisible(true);
	
	}

	private void cargarTabla() {
		for(Usuario u: Tienda.getCompras().keySet()) {
			for(Articulo a: Tienda.getCompras().get(u)) {
				Object [] fila = {u.getDni(),a.getId(),a.getPrecio()};
				modeloTablaCompras.addRow(fila);
			}
		}
	}
	
	

}

/*
 * JButton btnCerrar = new JButton("Cerrar");
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
	
HACER UN JSLIDER EN LA VENTANA TIENDA CON EL BUSCADOR
		sUnidades = new JSlider(0, 200, 0);
		sUnidades.setPaintLabels(true);
		sUnidades.setPaintTicks(true);
		sUnidades.setMinorTickSpacing(5);
		sUnidades.setMajorTickSpacing(20);
		pNorte.add(sUnidades);
		logger.info("JSlider creado");
		
CREACIÓN DE UN JSPINNER btn subir y bajar EN CADA TABLA PARA AÑADIRLE ARTICULOS O QUITARLE
		sPrecio = new JSpinner(new SpinnerNumberModel(0, 0, 100, 0.5));
		pNorte.add(sPrecio);*/

