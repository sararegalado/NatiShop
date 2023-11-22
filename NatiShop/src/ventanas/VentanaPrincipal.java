package ventanas;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import clases.Articulo;
import clases.Camiseta;
import clases.Genero;
import clases.Jersey;
import clases.Pantalon;
import clases.Tienda;
import clases.Usuario;
import clases.Zapato;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Canvas;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	static VentanaInicioSesion ventanaInicio;
	static VentanaRegistro reg;

	private JFrame vActual, vAnterior;
	
	private JPanel pnlArticulos;
	
	private JPanel contentPane;
	private JTextField tfBuscador;
	
	private boolean usuarioHaIniciadoSesion = false;
	
	
    private static JLabel lblNomU;


	public VentanaPrincipal(JFrame va) {
		vActual = this;
		vAnterior = va;
		setTitle("Carrito de Compras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1042, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);     
        
        JPanel pnlMenuBar = new JPanel();
        pnlMenuBar.setBounds(72, 97, 1300, 43);
        contentPane.add(pnlMenuBar);
        pnlMenuBar.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1153, 44);
        pnlMenuBar.add(menuBar);
        menuBar.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        JMenu menuHombre = new JMenu("Hombre");
        menuHombre.setFont(new Font("Baskerville", Font.PLAIN, 17));
        menuBar.add(menuHombre);
        
        JMenuItem menuItemCamiH = new JMenuItem("Camisetas");
        menuItemCamiH.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuHombre.add(menuItemCamiH);
        
        JMenuItem menuItemJersH = new JMenuItem("Jerséis");
        menuItemJersH.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuHombre.add(menuItemJersH);
        
        JMenuItem menuItemPantH = new JMenuItem("Pantalones");
        menuItemPantH.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuHombre.add(menuItemPantH);
        
        JMenuItem menuItemCalzH = new JMenuItem("Calzado");
        menuItemCalzH.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuHombre.add(menuItemCalzH);
        JMenu menuMujer = new JMenu("Mujer");
        menuMujer.setFont(new Font("Baskerville", Font.PLAIN, 17));
        menuBar.add(menuMujer);
        
        JMenuItem menuItemCamiM = new JMenuItem("Camisetas");
        menuMujer.add(menuItemCamiM);
        menuItemCamiM.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        JMenuItem menuItemJersM = new JMenuItem("Jerséis");
        menuMujer.add(menuItemJersM);
        menuItemJersM.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        JMenuItem menuItemPantM = new JMenuItem("Pantalones");
        menuMujer.add(menuItemPantM);
        menuItemPantM.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        JMenuItem menuItemCalzM = new JMenuItem("Calzado");
        menuMujer.add(menuItemCalzM);
        menuItemCalzM.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        JMenu menuNinos = new JMenu("Niños");
        menuNinos.setFont(new Font("Baskerville", Font.PLAIN, 17));
        menuBar.add(menuNinos);
        
        JMenuItem menuItemCamiN = new JMenuItem("Camisetas");
        menuItemCamiN.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuNinos.add(menuItemCamiN);
        
        JMenuItem menuItemJersN = new JMenuItem("Jerséis");
        menuItemJersN.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuNinos.add(menuItemJersN);
        
        JMenuItem menuItemPantN = new JMenuItem("Pantalones");
        menuItemPantN.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuNinos.add(menuItemPantN);
        
        JMenuItem menuItemCalzN = new JMenuItem("Calzado");
        menuItemCalzN.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuNinos.add(menuItemCalzN);
        
        JLabel lblLogo = new JLabel("NatiShop");
        lblLogo.setBounds(72, 24, 258, 72);
        contentPane.add(lblLogo);
        lblLogo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
        lblLogo.setFont(new Font("Baskerville", Font.PLAIN, 45));
        

        pnlArticulos = new JPanel();
        pnlArticulos.setBounds(72, 159, 1300, 490);
        JScrollPane spArticulos = new JScrollPane(pnlArticulos);
        pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
        spArticulos.setBounds(72,159,1153,490);
        contentPane.add(spArticulos);
        
        JLabel lblUsuario = new JLabel("");
        lblUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/usuario.png")));
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setBounds(1152, 11, 52, 52);
        
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        
        contentPane.add(lblUsuario);
        
        tfBuscador = new JTextField();
        tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
        tfBuscador.setBounds(845, 48, 220, 26);
        contentPane.add(tfBuscador);
        tfBuscador.setColumns(10);

      /*  lblUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (usuarioHaIniciadoSesion) {
                	new VentanaDatosCliente(vActual);
                } else {
                    new VentanaInicioSesion(vActual);
                    // vActual.setVisible(false);
                }
            }
        });*/
        
        lblNomU = new JLabel("Iniciar Sesión");
        lblNomU.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomU.setVerticalAlignment(SwingConstants.TOP);
        lblNomU.setBounds(1138, 67, 87, 19);
        contentPane.add(lblNomU);
        
        
        
        lblUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (! existeUsuario()) {
                	new VentanaInicioSesion(vActual);
                } else {
                   // new VentanaDatosCliente(vActual);
                    // vActual.setVisible(false);
                }
            }
        
        });
     

//        	@Override
//        	public void mouseClicked(MouseEvent e) {
//        		if (! existeUsuario()) { //si no existe usuario
//        			new VentanaInicioSesion(vActual);
//        		}
//        		else {
//        			for (Usuario u : Tienda.getUsuarios()) { //los nombres son unicos, no hay 2 personas con el mismo nombre
//        				if(u.getNombre() == lblNomU.getText()) {
//        					JFrame n = new JFrame();
//        				}
//        			}
//        		}
        		 
        
     
        //LISTENERS DE LOS ITEMS DE HOMBRE
        
        menuItemCamiH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Camiseta> camiH = new TreeSet<>();
        		for (Camiseta c: Tienda.getCamisetas()) {
        			if (c.getGenero()== Genero.HOMBRE) {
        				camiH.add(c);
        			}
        		}
        		setCamisetas(camiH,pnlArticulos);
        	}
        });
        
        
        menuItemJersH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Jersey> jersH = new TreeSet<>();
        		for (Jersey j: Tienda.getJerseys()) {
        			if (j.getGenero()== Genero.HOMBRE) {
        				jersH.add(j);
        			}
        		}
        		setJerseys(jersH,pnlArticulos);
        	}
        });
        
        menuItemPantH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Pantalon> pantH = new TreeSet<>();
        		Set<Pantalon>listaPantalones = Tienda.getPantalones();
        		for (Pantalon p: listaPantalones) {
        			if (p.getGenero()== Genero.HOMBRE) {
        				pantH.add(p);
        			}
        		}
        		setPantalones(pantH,pnlArticulos);
        	}
        });
        
        menuItemCalzH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Zapato> calzH = new TreeSet<>();
        		for (Zapato z: Tienda.getZapatos()) {
        			if (z.getGenero()== Genero.HOMBRE) {
        				calzH.add(z);
        			}
        		}
        		setZapatos(calzH,pnlArticulos);
        	}
        });
        
        


        //LISTENERS DE LOS ITEMS DE MUJER
        
        menuItemCamiM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Camiseta> camiM = new TreeSet<>();
        		for (Camiseta c: Tienda.getCamisetas()) {
        			if (c.getGenero()== Genero.MUJER) {
        				camiM.add(c);
        			}
        		}
        		setCamisetas(camiM,pnlArticulos);
        	}
        });
        
        menuItemJersM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Jersey> jersM = new TreeSet<>();
        		for (Jersey j: Tienda.getJerseys()) {
        			if (j.getGenero()== Genero.MUJER) {
        				jersM.add(j);
        			}
        		}
        		setJerseys(jersM,pnlArticulos);
        	}
        });
        
        menuItemPantM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Pantalon> pantM = new TreeSet<>();
        		Set<Pantalon>listaPantalones = Tienda.getPantalones();
        		for (Pantalon p: listaPantalones) {
        			if (p.getGenero()== Genero.MUJER) {
        				pantM.add(p);
        			}
        		}
        		setPantalones(pantM,pnlArticulos);
        	}
        });
        
        menuItemCalzM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Zapato> calzM = new TreeSet<>();
        		for (Zapato z: Tienda.getZapatos()) {
        			if (z.getGenero()== Genero.MUJER) {
        				calzM.add(z);
        			}
        		}
        		setZapatos(calzM,pnlArticulos);
        	}
        });
        
        
        //LISTENERS DE LOS ITEMS DE NIÑOS
        
        menuItemCamiN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Camiseta> camiN = new TreeSet<>();
        		for (Camiseta c: Tienda.getCamisetas()) {
        			if (c.getGenero()== Genero.NINOS) {
        				camiN.add(c);
        			}
        		}
        		setCamisetas(camiN,pnlArticulos);
        	}
        });
        
        menuItemJersN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Jersey> jersN = new TreeSet<>();
        		for (Jersey j: Tienda.getJerseys()) {
        			if (j.getGenero()== Genero.NINOS) {
        				jersN.add(j);
        			}
        		}
        		setJerseys(jersN,pnlArticulos);
        	}
        });
        
        menuItemPantN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Pantalon> pantN = new TreeSet<>();
        		Set<Pantalon>listaPantalones = Tienda.getPantalones();
        		for (Pantalon p: listaPantalones) {
        			if (p.getGenero()== Genero.NINOS) {
        				pantN.add(p);
        			}
        		}
        		setPantalones(pantN,pnlArticulos);
        	}
        });
        
        menuItemCalzN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("pulsado");
        		System.out.println("Grid puesto");
        		limpiarPanel(pnlArticulos);
        		
        		Set<Zapato> calzN = new TreeSet<>();
        		for (Zapato z: Tienda.getZapatos()) {
        			if (z.getGenero()== Genero.NINOS) {
        				calzN.add(z);
        			}
        		}
        		setZapatos(calzN,pnlArticulos);
        	}
        });
        
        
        
        
        
        
        

       setVisible(true);
	}
	

//	public static void asignarNombreUsuario(Usuario u) {
//		lblNomU.setText(u.getNombre());
//	};


	/*public boolean usuarioHaIniciadoSesion() {
	    return usuarioHaIniciadoSesion;
	}*/


	public static void asignarNombreUsuario(Usuario u) {
		lblNomU.setText(u.getNombre());
	};
	
	
	public boolean existeUsuario() {
		boolean existe = false;
		if (lblNomU.getText() != "Iniciar Sesión") {
			existe = true;	
		}
		return existe;
	}
	
	
	public void limpiarPanel(JPanel panel) {
	    panel.removeAll();
	    panel.revalidate();
	    panel.repaint();
	}
	
	public void setCamisetas(Set<Camiseta> art, JPanel panel) {
		this.pnlArticulos = panel;
//		panel.setLayout(new GridLayout(0, COLUMNAS));
		for (Camiseta a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
//			System.out.println("Añadido");
		}	
	}
	
	public void setJerseys(Set<Jersey> art, JPanel panel) {
		this.pnlArticulos = panel;
//		panel.setLayout(new GridLayout(0, COLUMNAS));
		for (Jersey a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
//			System.out.println("Añadido");
		}	
	}
	
	
	public void setPantalones(Set<Pantalon> art, JPanel panel) {
		this.pnlArticulos = panel;
//		panel.setLayout(new GridLayout(0, COLUMNAS));
		for (Pantalon a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
//			System.out.println("Añadido");
		}	
	}
	
	public void setZapatos(Set<Zapato> art, JPanel panel) {
		this.pnlArticulos = panel;
//		panel.setLayout(new GridLayout(0, COLUMNAS));
		for (Zapato a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
//			System.out.println("Añadido");
		}	
	}
	
	
	
	
	public JPanel crearPanelArticulo(Articulo articulo) {
		JPanel panelArticulo = new JPanel();
		panelArticulo.setPreferredSize(new Dimension(200,240));
		panelArticulo.setLayout(new BorderLayout());
		
		String rutaImagen = articulo.getFoto();
		JLabelGrafico foto = new JLabelGrafico (rutaImagen,220,220);
		System.out.println("Grafico creado");
		
//		ImageIcon foto = new ImageIcon(rutaImagen);
		
//		JLabel etiqueta = new JLabel(foto);
		
		JLabel titulo = new JLabel(articulo.getNombre());
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Baskerville", Font.PLAIN, 17));


		
		panelArticulo.add(foto,BorderLayout.CENTER);
		panelArticulo.add(titulo, BorderLayout.SOUTH);
		panelArticulo.setBorder(new LineBorder(Color.BLACK));
		
		panelArticulo.setBackground(Color.WHITE);
		
		return panelArticulo;

		/* if (UsuarioHaIniciadoSesion()) {
  	  lblUsuario.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new VentanaInicioSesion(vActual);
				//vActual.setVisible(false);
        		
        	}
       });       
  } else {
  	lblUsuario.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		JOptionPane.showMessageDialog(vActual, "Por favor, inicia sesión antes de acceder.");
      	}
  	});
  }*/
  
  
	}
}