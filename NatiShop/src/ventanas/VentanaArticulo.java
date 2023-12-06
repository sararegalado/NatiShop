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
import java.awt.GraphicsEnvironment;


import clases.Articulo;
import clases.Talla;
import clases.Tienda;


public class VentanaArticulo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pCentro,pNorte,pTallas, pnlBoton, panelCT;
	private JButton btnAniadirArticuloAlCarrito;
	private JLabel lblTallas;
	private JLabelGrafico foto;
	private JComboBox<Talla> cbTallas;
	
	/*private DefaultListModel<Articulo> modeloListaArticulos; 
	private JList<Articulo> listaArticulos; 
	private JScrollPane scrollListaArticulos;*/
	
	public VentanaArticulo(Articulo articulo) {
		JFrame vActual = this;
		this.setTitle("Producto");

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 10, 1300, 900);
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	    int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	    setSize(anchoP, altoP);
	    setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout());
		
		pCentro = new JPanel(new GridLayout(1, 2));
		pCentro.setBackground(Color.WHITE);
		
        getContentPane().add(pCentro,BorderLayout.CENTER);
        JPanel vacio = new JPanel();
        vacio.setBackground(Color.WHITE);
		getContentPane().add(vacio, BorderLayout.EAST);
	
		String rutaImagen = articulo.getFoto();
		foto = new JLabelGrafico(rutaImagen,400,400);	
		pCentro.add(foto);


		pNorte = new JPanel(new GridLayout(1, 3));
		JLabel lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(VentanaInicioSesion.class.getResource("/imagenes/volver.png")));
		JLabel vacio2 = new JLabel("");
		pNorte.setBackground(Color.WHITE);
        //pNorte.setPreferredSize(new Dimension(anchoP,(altoP/6)));
        JLabel lblTitulo = new JLabel(articulo.getNombre());
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Baskerville", Font.BOLD, 30));
        pNorte.add(lblAtras);
        pNorte.add(lblTitulo);
        pNorte.add(vacio2);
        
        
        getContentPane().add(pNorte,BorderLayout.NORTH);
        
		
		lblAtras.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				vActual.setVisible(false);
			}
	
		});
		
		panelCT = new JPanel(new GridLayout(3,1));
		
		JPanel vacio3 = new JPanel();
		vacio3.setBackground(Color.WHITE);
		panelCT.add(vacio3);
		pnlBoton = new JPanel();
		pnlBoton.setLayout(null);
		pnlBoton.setBackground(Color.WHITE);
		
	
        pTallas = new JPanel();
        pTallas.setBounds(35, 20, 438, 371);
        pTallas.setBorder(new LineBorder(new Color(0,0,0)));
        pTallas.setBackground(new Color(254, 255, 255));
        panelCT.add(pTallas);
 
        
        pTallas.setLayout(null);
        

        lblTallas = new JLabel("Tallas disponibles");
        lblTallas.setBounds(225, 11, 170, 43);
        pTallas.add(lblTallas);
        
        lblTallas.setHorizontalAlignment(SwingConstants.CENTER);
        lblTallas.setFont(new Font("Baskerville", Font.PLAIN, 20));
        
        cbTallas = new JComboBox<Talla>();
        TreeSet<Talla> tallasTree = Tienda.tallasPorArticulo(articulo);
        for (Talla t : tallasTree) {
        	cbTallas.addItem(t);
        	
        }
       cbTallas.setBounds(241, 85, 142, 27);
       pTallas.add(cbTallas);
       
       btnAniadirArticuloAlCarrito = new JButton("AÑADIR ARTÍCULO AL CARRITO");
       btnAniadirArticuloAlCarrito.setBounds(172, 82, 325, 29);
       
       pnlBoton.add(btnAniadirArticuloAlCarrito, BorderLayout.CENTER);
       panelCT.add(pnlBoton);
       pCentro.add(panelCT);
       

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



	
	


	


