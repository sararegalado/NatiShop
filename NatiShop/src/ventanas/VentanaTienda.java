package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;


import clases.Articulo;
import clases.Talla;
import clases.Tienda;


public class VentanaTienda extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pEeste,pOeste,pNorte,pTallas;
	private JButton btnAniadirArticuloAlCarrito;
	private JLabel lblTallas;
	private JLabelGrafico foto;
	private JComboBox<Talla> cbTallas;
	
	/*private DefaultListModel<Articulo> modeloListaArticulos; 
	private JList<Articulo> listaArticulos; 
	private JScrollPane scrollListaArticulos;*/
	
	public VentanaTienda(Articulo articulo) {
		JFrame vActual = this;
		this.setTitle("Producto");
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
		
		pEeste = new JPanel();
		pEeste.setBackground(Color.WHITE);
		pEeste.setPreferredSize(new Dimension(anchoP/2,3*(altoP/4)));
		pEeste.setLayout(null);
        getContentPane().add(pEeste,BorderLayout.CENTER);
		
	
		pOeste = new JPanel();
		pOeste.setPreferredSize(new Dimension(anchoP/2,3*(altoP/4)));
		pOeste.setBackground(Color.WHITE);
		String rutaImagen = articulo.getFoto();
		foto = new JLabelGrafico(rutaImagen,400,400);	
		pOeste.add(foto,BorderLayout.CENTER);
		getContentPane().add(pOeste,BorderLayout.WEST);
		
		

		pNorte = new JPanel(new GridLayout(1, 3));
		JLabel lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(VentanaInicioSesion.class.getResource("/imagenes/volver.png")));
		JLabel vacio = new JLabel("");
		pNorte.setBackground(Color.WHITE);
        pNorte.setPreferredSize(new Dimension(anchoP,(altoP/6)));
        JLabel lblTitulo = new JLabel(articulo.getNombre());
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Baskerville", Font.BOLD, 30));
        pNorte.add(lblAtras);
        pNorte.add(lblTitulo);
        pNorte.add(vacio);
        
        
        getContentPane().add(pNorte,BorderLayout.NORTH);
        
		
		lblAtras.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				vActual.setVisible(false);
			}
		});
		
		
		
		
		
	
		
		

		
	
        pTallas = new JPanel();
        pTallas.setBounds(35, 20, 438, 371);
        pTallas.setBorder(new LineBorder(new Color(0,0,0)));
        pTallas.setBackground(new Color(254, 255, 255));
        
        pEeste.add(pTallas);
        
        pTallas.setLayout(null);
        

        lblTallas = new JLabel("Tallas disponibles");
        lblTallas.setBounds(143, 57, 170, 43);
        pTallas.add(lblTallas);
        
        lblTallas.setHorizontalAlignment(SwingConstants.CENTER);
        lblTallas.setFont(new Font("Baskerville", Font.PLAIN, 20));
        
        cbTallas = new JComboBox<Talla>();
        TreeSet<Talla> tallasTree = Tienda.tallasPorArticulo(articulo);
        for (Talla t : tallasTree) {
        	cbTallas.addItem(t);
        	
        }
       cbTallas.setBounds(160, 137, 142, 27);
       pTallas.add(cbTallas);
       
       btnAniadirArticuloAlCarrito = new JButton("AÑADIR ARTÍCULO AL CARRITO");
       btnAniadirArticuloAlCarrito.setBounds(71, 279, 325, 29);
       pTallas.add(btnAniadirArticuloAlCarrito);

		/*modeloListaArticulos = new DefaultListModel<>();
		listaArticulos = new JList<>(modeloListaArticulos);
		scrollListaArticulos = new JScrollPane(listaArticulos);
		scrollListaArticulos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollListaArticulos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		cargarArticulos();*/
		
	       


		
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
		

	
//	setVisible(true)
//		
//	}
//
//	public void mostrarFotoArticulo(Articulo articulo) {
//		String rutaFoto = articulo.getFoto();
//		ImageIcon icono = new ImageIcon(getClass().getResource(rutaFoto));
//		lblFotoArticulo.setIcon(icono);
//	}

	}
	
}



	
	


	


