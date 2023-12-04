package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import clases.Articulo;
import clases.Cliente;
import clases.Talla;
import clases.Tienda;


public class VentanaTienda extends JFrame {
	private JPanel pSur,pEeste,pOeste,pNorte,pCenter, pFoto, pTallas;
	private JButton btnVolver,btnAniadirArticuloAlCarrito, btnFinalizarCompra, btnVerCarrito;
	private JFrame vActual,vAnterior;
	private JLabel lblFotoArticulo, lblTallas;
	private JComboBox<Talla> cbTallas;
	
	private VentanaCompras ventanaCompras;
	
	
	/*private DefaultListModel<Articulo> modeloListaArticulos; 
	private JList<Articulo> listaArticulos; 
	private JScrollPane scrollListaArticulos;*/
	
	public VentanaTienda(VentanaPrincipal ventanaPrincipal, Articulo articulo) {
		super();
		vActual = this;
		vAnterior = ventanaPrincipal;

		if(ventanaPrincipal != null) {
			setSize(ventanaPrincipal.getWidth(), ventanaPrincipal.getHeight());
		} else {
			setSize(800, 600);
		}
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pEeste = new JPanel();
		getContentPane().add(pEeste, BorderLayout.EAST);
		
	
		pOeste = new JPanel();
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		pCenter = new JPanel();
		pCenter.setLayout(new GridBagLayout());
		getContentPane().add(pCenter, BorderLayout.CENTER);
		
		
		lblFotoArticulo = new JLabel();
		pOeste.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        pOeste.add(lblFotoArticulo, gbc);
		
		
		
		pSur = new JPanel();
		getContentPane().add(pSur,BorderLayout.SOUTH);
	
		
		btnAniadirArticuloAlCarrito = new JButton("AÑADIR ARTÍCULO AL CARRITO");
		btnVerCarrito = new JButton("VER CARRITO");
		btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		btnFinalizarCompra = new JButton("FINALIZAR COMPRA");
		
		 GridBagConstraints btnConstraints = new GridBagConstraints();
	        btnConstraints.gridx = 0;
	        btnConstraints.gridy = 0;
	        btnConstraints.insets = new Insets(5, 5, 5, 5);

	        pCenter.add(Box.createVerticalGlue(), btnConstraints);
	        btnConstraints.gridy++;
	        pCenter.add(btnAniadirArticuloAlCarrito, btnConstraints);
	        btnConstraints.gridy++;
	        pCenter.add(btnVerCarrito, btnConstraints);
	        btnConstraints.gridy++;
	        pCenter.add(btnFinalizarCompra, btnConstraints);
	        btnConstraints.gridy++;
	        pCenter.add(Box.createVerticalGlue(), btnConstraints);
		
	        JPanel pTallas = new JPanel();
	        pTallas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        pTallas.setBackground(new Color(254, 255, 255));
	        pEeste.add(pTallas);
	        pTallas.setLayout(new FlowLayout(FlowLayout.CENTER));
	        

	        JLabel lblTallas = new JLabel("Tallas disponibles");
	        lblTallas.setFont(new Font("Baskerville", Font.PLAIN, 20));
	        
	        cbTallas = new JComboBox<Talla>();
	        TreeSet<Talla> tallasTree = Tienda.tallasPorArticulo(articulo);
	        for (Talla t : tallasTree) {
	        	cbTallas.addItem(t);
	        	
	        }
	        
	        pCenter.add(lblTallas);
	        pCenter.add(cbTallas);
		
		
	       
		
		//
		btnVolver.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        ventanaPrincipal.setVisible(true);
		        VentanaTienda.this.dispose();
		    }
		});
//
		btnVerCarrito.addActionListener((e)->{
			new VentanaCompras(vActual);
			vActual.setVisible(false);
		});

		
	/*btnAniadirArticuloAlCarrito.addActionListener(new ActionListener() {
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
		

		  
		btnAniadirArticuloAlCarrito.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Talla tallaSeleccionada = (Talla) cbTallas.getSelectedItem();
	            Articulo articuloSeleccionado = obtenerArticuloSeleccionado(tallaSeleccionada);

	            if (articuloSeleccionado != null) {
	                VentanaCompras.agregarArticuloAlCarrito(articuloSeleccionado, 1, articuloSeleccionado.getPrecio());
	                //Logger.getLogger(getClass().getName()).info("Artículo añadido al carrito: " + articuloSeleccionado.getNombre());
	                //mostrarFotoArticulo(articuloSeleccionado);
	            }
	        }

			private Articulo obtenerArticuloSeleccionado(Talla tallaSeleccionada) {				
				 Set<Articulo> todosLosArticulos = Tienda.getArticulos();
				    for (Articulo articulo : todosLosArticulos) {
				        TreeSet<Talla> tallasDisponibles = Tienda.tallasPorArticulo(articulo);
				        if (tallasDisponibles.contains(tallaSeleccionada)) {
				            return articulo;
				        }
				    }
				return null;
			}
	    });

	
	setVisible(true);
		
	}

	public void mostrarFotoArticulo(Articulo articulo) {
		String rutaFoto = articulo.getFoto();
		ImageIcon icono = new ImageIcon(getClass().getResource(rutaFoto));
		lblFotoArticulo.setIcon(icono);
	}

}



	
	


	


