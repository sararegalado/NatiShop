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
import clases.Talla;
import clases.Tienda;


public class VentanaTienda extends JFrame {
	private JPanel pSur,pEeste,pOeste,pNorte,pCenter, pFoto, pTallas;
	private JButton btnVolver,btnAniadirArticuloAlCarrito,btnVerCarrito, btnFinalizarCompra;
	private JFrame vActual,vAnterior;
	private JLabel lblFotoArticulo, lblTallas;
	private JComboBox<Talla> cbTallas;
	
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		

	
	setVisible(true);
		
	}

	public void mostrarFotoArticulo(Articulo articulo) {
		String rutaFoto = articulo.getFoto();
		ImageIcon icono = new ImageIcon(getClass().getResource(rutaFoto));
		lblFotoArticulo.setIcon(icono);
	}

}



	
	


	


