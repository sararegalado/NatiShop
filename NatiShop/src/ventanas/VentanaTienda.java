package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import clases.Articulo;
import clases.Tienda;


public class VentanaTienda extends JFrame {
	private JPanel pSur,pEeste,pOeste,pNorte, pFoto;
	private JButton btnVolver,btnAniadirArticuloAlCarrito,btnVerCarrito, btnFinalizarCompra;
	private JFrame vActual,vAnterior;
	private JLabel lblFoto;
	
	/*private DefaultListModel<Articulo> modeloListaArticulos; 
	private JList<Articulo> listaArticulos; 
	private JScrollPane scrollListaArticulos;*/
	
	public VentanaTienda(VentanaPrincipal ventanaPrincipal, Articulo articulo) {
		super();
		vActual = this;
		vAnterior = ventanaPrincipal;
		setBounds(100, 100, 1042, 693);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/*ImageIcon im = new ImageIcon("imagenes/"+a.get());
		lblFoto = new JLabel(im);
		pFoto = new JPanel();
		
		pFoto.add(lblFoto);*/
		
	//
		pOeste = new JPanel();
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		pEeste = new JPanel();
		getContentPane().add(pEeste, BorderLayout.EAST);
		//
		
		lblFoto = new JLabel();
		pOeste.add(lblFoto);
		
		pSur = new JPanel();
		getContentPane().add(pSur,BorderLayout.SOUTH);
		btnAniadirArticuloAlCarrito = new JButton("AÑADIR ARTÍCULO AL CARRITO");
		pEeste.add(btnAniadirArticuloAlCarrito);
		btnVerCarrito = new JButton("VER CARRITO");
		pEeste.add(btnVerCarrito);
		btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		btnFinalizarCompra = new JButton("FINALIZAR COMPRA");
		pEeste.add(btnFinalizarCompra);
		
		
		
		
		
		/*modeloListaArticulos = new DefaultListModel<>();
		listaArticulos = new JList<>(modeloListaArticulos);
		scrollListaArticulos = new JScrollPane(listaArticulos);
		scrollListaArticulos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollListaArticulos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		cargarArticulos();*/
		
	       
		
		//
		btnVolver.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        ventanaPrincipal.setVisible(true);
		        VentanaTienda.this.dispose();
		    }
		});
//

		
	/*	btnAniadirArticuloAlCarrito.addActionListener(new ActionListener() {
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
		}); */
		

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
		
	}
	
	


	/*private void cargarArticulos() {
		for(Articulo a: Tienda.getArticulos()) { 
			modeloListaArticulos.addElement(a); 
		}*/
		
	actualizarFotoArticulo(articulo);
	
	setVisible(true);
		
	}

	private void actualizarFotoArticulo(Articulo articulo) {
		ImageIcon imagen = new ImageIcon(articulo.getFoto());
		lblFoto.setIcon(imagen);
		
	}



	
	

}
	


