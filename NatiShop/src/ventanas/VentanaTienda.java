package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases.Articulo;
import clases.Tienda;

public class VentanaTienda extends JFrame{
	private JPanel pSur,pOeste,pCentro,pNorte;
	private JButton btnVolver,btnAniadirArticuloAlCarrito,btnVerCarrito,btnFinalizarCompra, btnVerTodasLasCompras;
	private JFrame vActual,vAnterior;
	
	private JTextArea areaCarrito;
	
	private DefaultListModel<Articulo> modeloListaArticulos; 
	private JList<Articulo> listaArticulos; 
	private JScrollPane scrollListaArticulos;
	
	private JLabel lblBusqueda;
	private JTextField txtBusqueda;
	
	public VentanaTienda(JFrame va) {
		super();
		vActual = this;
		vAnterior = va;
		setBounds(300, 200, 800, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pNorte = new JPanel();
		lblBusqueda = new JLabel("Introduce el número mínimo de unidades: ");
		txtBusqueda = new JTextField(20);
		pNorte.add(lblBusqueda);
		pNorte.add(txtBusqueda);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		
		pSur = new JPanel();
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		btnAniadirArticuloAlCarrito = new JButton("AÑADIR ARTÍCULO AL CARRITO");
		pSur.add(btnAniadirArticuloAlCarrito);
		btnVerCarrito = new JButton("VER CARRITO");
		pSur.add(btnVerCarrito);
		btnFinalizarCompra = new JButton("FINALIZAR COMPRA");
		pSur.add(btnFinalizarCompra);
		btnVerTodasLasCompras = new JButton("VER TODAS LAS COMPRAS");
		pSur.add(btnVerTodasLasCompras);
		btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		
		pOeste = new JPanel();
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		pCentro = new JPanel();
		areaCarrito = new JTextArea(20, 30);
		pCentro.add(areaCarrito);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		

		modeloListaArticulos = new DefaultListModel<>();
		listaArticulos = new JList<>(modeloListaArticulos);
		scrollListaArticulos = new JScrollPane(listaArticulos);
		scrollListaArticulos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollListaArticulos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		cargarArticulos();
		pOeste.add(scrollListaArticulos);
		

		btnVolver.addActionListener((e)->{
			vActual.dispose();
			//vActual.setVisible(false);
			vAnterior.setVisible(true);
		});
		
		btnAniadirArticuloAlCarrito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = listaArticulos.getSelectedIndex(); 
				if(pos == -1) {
					JOptionPane.showMessageDialog(null, "Tienes que seleccionar un artículo");
				}else {
					//Articulo a = listaArticulos.getSelectedValue(); 
					Articulo a = modeloListaArticulos.getElementAt(pos);
					if(a.getUnidades() == 0) {
						JOptionPane.showMessageDialog(null, "No nos quedan unidades");
					}else {
					//	Ventana.getCarrito().add(a); 
						a.setUnidades(a.getUnidades()-1);
						modeloListaArticulos.set(pos, a);
						JOptionPane.showMessageDialog(null, "Artículo añadido al carrito");
					}
				}
			}
		});
		
		/*btnVerCarrito.addActionListener((e)->{
			Cliente c = Ventana.getCliente();
			String texto = "CLIENTE: "+c.getDni() +" "+c.getNombre()+"\n\n";
			texto = texto + "ARTICULOS EN EL CARRITO: \n";
			for(Articulo a: Ventana.getCarrito()) {
				texto = texto + a.toString() + "\n";
			}
			areaCarrito.setText(texto);
		});
		*/
		
	/*	btnFinalizarCompra.addActionListener((e)->{
			Tienda.getCompras().put(Ventana.getCliente(), Ventana.getCarrito());
			areaCarrito.setText("");
			JOptionPane.showMessageDialog(null, "Compra finalizada correctamente");
		});
		
		btnVerTodasLasCompras.addActionListener((e)->{
			new VentanaTodasLasCompras(vActual);
			vActual.setVisible(false);
		});
		*/
		

		txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String sUnidades = txtBusqueda.getText();
				int unidades;
				try {
					unidades = Integer.parseInt(sUnidades);
				}catch(NumberFormatException ex) {
					unidades = 0;
				}
				modeloListaArticulos.removeAllElements();
				for(Articulo a: Tienda.getArticulos()) {
					if(a.getUnidades() >= unidades) {
						modeloListaArticulos.addElement(a);
					}
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String sUnidades = txtBusqueda.getText();
				int unidades;
				try {
					unidades = Integer.parseInt(sUnidades);
				}catch(NumberFormatException ex) {
					unidades = 0;
				}
				modeloListaArticulos.removeAllElements();
				for(Articulo a: Tienda.getArticulos()) {
					if(a.getUnidades() >= unidades) {
						modeloListaArticulos.addElement(a);
					}
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("Cambiado");
			}
		});
		
		
		listaArticulos.setCellRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
				
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				
				
				Articulo a = (Articulo) value;
				if(a.getUnidades() == 0) {
					c.setBackground(Color.RED);
					//c.setForeground(Color.RED);
					//c.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
				}else if(a.getUnidades() == 1) {
					c.setBackground(Color.YELLOW);
					//c.setForeground(Color.YELLOW);
				}else {
					c.setBackground(list.getBackground());
					//c.setForeground(list.getForeground());
				}
				
				return c;
				
			}
		});
		
		
		setVisible(true);
	}
	
	private void cargarArticulos() {
		for(Articulo a: Tienda.getArticulos()) { 
			modeloListaArticulos.addElement(a); 
		}
	}
}




