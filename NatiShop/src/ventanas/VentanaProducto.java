package ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import clases.Articulo;
import clases.Talla;
import clases.Tienda;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class VentanaProducto extends JFrame {
	

	
	public VentanaProducto(Articulo art) {
		this.setTitle("Producto");
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        
        
        JPanel pnlIzquierda = new JPanel(new BorderLayout());
        pnlIzquierda.setBackground(Color.WHITE);
        pnlIzquierda.setPreferredSize(new Dimension(anchoP/2,3*(altoP/4)));
		String rutaImagen = art.getFoto();
        JLabelGrafico foto = new JLabelGrafico (rutaImagen,400,400);        
        pnlIzquierda.add(foto,BorderLayout.CENTER);
        
        
        
        getContentPane().add(pnlIzquierda,BorderLayout.WEST);
        
    	JPanel pnlArriba = new JPanel(new BorderLayout());
        pnlArriba.setBackground(Color.WHITE);

        pnlArriba.setPreferredSize(new Dimension(anchoP,(altoP/6)));
        JLabel lblTitulo = new JLabel(art.getNombre());
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Baskerville", Font.BOLD, 30));

        
        pnlArriba.add(lblTitulo,BorderLayout.CENTER);
       
        getContentPane().add(pnlArriba,BorderLayout.NORTH);


    	JPanel pnlDerecha = new JPanel();
        pnlDerecha.setBackground(Color.WHITE);
        pnlDerecha.setPreferredSize(new Dimension(anchoP/2,3*(altoP/4)));
        pnlDerecha.setLayout(null);
        getContentPane().add(pnlDerecha,BorderLayout.CENTER);
        
        

   
        
       
        JPanel pnlTallas = new JPanel();
        pnlTallas.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlTallas.setBackground(new Color(254, 255, 255));
        pnlTallas.setBounds(151, 122, 437, 361);
        pnlDerecha.add(pnlTallas);
        pnlTallas.setLayout(null);
        JLabel lblTallas = new JLabel("Tallas disponibles");
        lblTallas.setBounds(143, 57, 170, 43);
        pnlTallas.add(lblTallas);
        lblTallas.setHorizontalAlignment(SwingConstants.CENTER);
        lblTallas.setFont(new Font("Baskerville", Font.PLAIN, 20));
        JComboBox<Talla> cbTallas = new JComboBox<Talla>();
        TreeSet<Talla> tallasTree = Tienda.tallasPorArticulo(art);
        System.out.println(Tienda.tallasPorArticulo(art));
        System.out.println(Tienda.getArticulos());
        
        for (Talla t : tallasTree) {
        	cbTallas.addItem(t);
        	
        }
        
        cbTallas.setFont(new Font("Baskerville", Font.PLAIN, 15));
        cbTallas.setBounds(160, 137, 142, 27);
        pnlTallas.add(cbTallas);
        
        JButton bConsultarCesta = new JButton("Consultar cesta");
        bConsultarCesta.setFont(new Font("Baskerville", Font.PLAIN, 20));
        bConsultarCesta.setBounds(243, 270, 170, 29);
        pnlTallas.add(bConsultarCesta);
        
        JButton bAnyadirCesta = new JButton("Añadir a la cesta");
        bAnyadirCesta.setFont(new Font("Baskerville", Font.PLAIN, 20));
        bAnyadirCesta.setBounds(48, 270, 180, 29);
        pnlTallas.add(bAnyadirCesta);

    	
    	
	}
}
