package ventanas;

import java.awt.event.ActionEvent;
import clases.Articulo;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import clases.Articulo;
import clases.Camiseta;
import clases.Cliente;
import clases.Genero;
import clases.Jersey;
import clases.Pantalon;
import clases.Tienda;
import clases.Zapato;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Point;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Canvas;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	static VentanaInicioSesion ventanaInicio;
	static VentanaRegistro reg;
	static VentanaCompras ventanaCompras;

	private JFrame vActual, vAnterior;
	
	private JPanel pnlArticulos;
	
	
	private JPanel contentPane;
	
	private JTextField tfBuscador;
	

	
	private static Tienda tienda = new Tienda();
	
	public static Tienda getTienda() {
		return tienda;
	}
	
	public static void setTienda(Tienda t) {
		tienda = t;
	}
	
	//
	
//	private VentanaTienda ventanaTienda;
//	
//	public void abrirVentanaTienda(Articulo articulo) {
//		ventanaTienda = new VentanaTienda(this, articulo);
//		ventanaTienda.mostrarFotoArticulo(articulo);
//		ventanaTienda.setVisible(true);
//		this.setVisible(false);
//	}
//	
//	//
	
	
	

	private static boolean clienteHaIniciadoSesion = false;
	
	
    public static boolean isClienteHaIniciadoSesion() {
		return clienteHaIniciadoSesion;
	}


	public static void setClienteHaIniciadoSesion(boolean cHaIniciadoSesion) {
		clienteHaIniciadoSesion = cHaIniciadoSesion;
	}


	private static JLabel lblNomU;
	private static Object titulos;
	
	public static JLabel getLblNomU() {
		return lblNomU;
	}

	public static void setLblNomU(JLabel lblNomU) {
		VentanaPrincipal.lblNomU = lblNomU;
	}

	public VentanaPrincipal(JFrame va) {
		vActual = this;
		vAnterior = va;
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
        
        tfBuscador = new JTextField();
        tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
        tfBuscador.setBounds(845, 32, 220, 26);
        contentPane.add(tfBuscador);
        tfBuscador.setColumns(10);
        
        String[] categorias = {"Todos los géneros","Hombre","Mujer","Niños"};
		JComboBox<String> filtrado = new JComboBox<>(categorias);
        filtrado.setBounds(845, 63, 220, 27);
        contentPane.add(filtrado);
        filtrado.setVisible(false);
        
        
        //LISTENERS DEL BUSCADOR
        tfBuscador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String textoBusqueda = tfBuscador.getText();
				buscarArticulos(textoBusqueda, pnlArticulos,filtrado);
			}
		});
        tfBuscador.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfBuscador.setText(""); // Borrar el texto por defecto al obtener el foco
            }
            
            
        });
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/nombreTienda.png")));
        lblLogo.setBounds(62, 11, 303, 80);
        contentPane.add(lblLogo);
        lblLogo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
        lblLogo.setFont(new Font("Baskerville", Font.PLAIN, 45));
        lblLogo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarPanel(pnlArticulos);
				pnlArticulos.setLayout(null);
		        JLabelGrafico fotoPortada = new JLabelGrafico("/imagenes/portada.png",1300,490);
		        fotoPortada.setLocation(0, 0);
		        pnlArticulos.add(fotoPortada);
		        tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
		        filtrado.setVisible(false);
			}
		});

        pnlArticulos = new JPanel();
        pnlArticulos.setBounds(72, 159, 1300, 490);
        JScrollPane spArticulos = new JScrollPane(pnlArticulos);
        spArticulos.setBounds(72,159,1153,490);
        contentPane.add(spArticulos);
        

        
        JLabel lblUsuario = new JLabel("");
        lblUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/usuario.png")));
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setBounds(1155, 11, 52, 52);
        
       
        
        JLabel lblCarro = new JLabel("");
        lblCarro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/carro.png")));
        lblCarro.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarro.setBounds(1076, 21, 52, 52);
        contentPane.add(lblCarro);
        
        
        JPopupMenu menuCliente = new JPopupMenu();
        JMenuItem perfil = new JMenuItem("Ver perfil");
        JMenuItem compras = new JMenuItem("Ver mis compras");

        perfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Cliente c = VentanaInicioSesion.getCliente();
            	new VentanaDatosUsuario(vActual, c);
                
            }
        });

//        compras.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                
//            }
//        });

        menuCliente.add(perfil);
        menuCliente.add(compras);

        lblUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
                if (clienteHaIniciadoSesion) {
                	menuCliente.show(lblUsuario, 0, lblUsuario.getHeight());
                } else {
                    new VentanaInicioSesion(vActual);
                }
            }
        });
        
        contentPane.add(lblUsuario);
        
        lblCarro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	new VentanaCompras(vActual);
        
            }
        });
        contentPane.add(lblCarro);
        
        
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        

        
        
        
        //PORTADA DE LA TIENDA
        pnlArticulos.setLayout(null);
        JLabelGrafico fotoPortada = new JLabelGrafico("/imagenes/portada.png",pnlArticulos.getWidth(),pnlArticulos.getHeight());
        fotoPortada.setLocation(0, 0);
        pnlArticulos.add(fotoPortada);
        
  
        lblUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
                if (clienteHaIniciadoSesion) {
                	Cliente c = VentanaInicioSesion.getCliente();
                	new VentanaDatosUsuario(vActual, c);
                } else {
                    new VentanaInicioSesion(vActual);
                }
            }
        });
        
        
        lblNomU = new JLabel("Iniciar Sesión");
        lblNomU.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomU.setVerticalAlignment(SwingConstants.TOP);
        lblNomU.setBounds(1138, 67, 87, 19);
        contentPane.add(lblNomU);
        
        
        
        
     

        //LISTENERS DE LOS ITEMS DE HOMBRE
        
       
        menuItemCamiH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

        		Set<Camiseta> camiH = new TreeSet<>();
        		ArrayList<String> fCH = new ArrayList<>();
        		for (Camiseta c: Tienda.getCamisetas()) {
        			if (c.getGenero()== Genero.HOMBRE ) {
        				camiH.add(c);	
        			}
        		}
        		setCamisetas(camiH,pnlArticulos);
        	}
        });
        
        
        menuItemJersH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

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
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
                pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));

        		
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
        
	public boolean clienteHaIniciadoSesion() {
	    return clienteHaIniciadoSesion;
	}


	public static void asignarNombreCliente(Cliente c) {
		lblNomU.setText(c.getNombre());
	};
	
	public static void eliminarNombreCliente() {
		lblNomU.setText("Iniciar sesión");
	};
	
	public void limpiarPanel(JPanel panel) {
	    panel.removeAll();
	    panel.revalidate();
	    panel.repaint();

	}
	
	public void setCamisetas(Set<Camiseta> art, JPanel panel) {
		this.pnlArticulos = panel;
		for (Camiseta a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
		}	
	}
	
	public void setJerseys(Set<Jersey> art, JPanel panel) {
		this.pnlArticulos = panel;
		for (Jersey a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
		}	
	}
	
	
	public void setPantalones(Set<Pantalon> art, JPanel panel) {
		this.pnlArticulos = panel;
		for (Pantalon a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
		}	
	}
	
	public void setZapatos(Set<Zapato> art, JPanel panel) {
		this.pnlArticulos = panel;
		for (Zapato a: art) {
			JPanel pnlArticulo = crearPanelArticulo(a);
			panel.add(pnlArticulo);
		}	
	}
	
	

	public JPanel crearPanelArticulo(Articulo articulo) {
		JPanel panelArticulo = new JPanel();
		panelArticulo.setPreferredSize(new Dimension(200,240));
		panelArticulo.setLayout(new BorderLayout());
		
		String rutaImagen = articulo.getFoto();
		JLabelGrafico foto = new JLabelGrafico (rutaImagen,195,200);
				
		JLabel titulo = new JLabel("     "+articulo.getNombre());
        titulo.setFont(new Font("Baskerville", Font.PLAIN, 17));
        
        JLabel precio = new JLabel(articulo.getPrecio()+" EUR     ");



		
		panelArticulo.add(foto,BorderLayout.NORTH);
		panelArticulo.add(titulo, BorderLayout.WEST);
		panelArticulo.add(precio,BorderLayout.EAST);
		panelArticulo.setBorder(new LineBorder(Color.BLACK));
		
		panelArticulo.setBackground(Color.WHITE);
		
		panelArticulo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaTienda producto = new VentanaTienda(articulo);
				producto.setVisible(true);
				
			}
		});
		
		
		
		
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
      		JOptionPane.showMessageDialog(vActual, "Por favor, inicie sesión antes de acceder.");
      	}
  	});
  }*/
		
			
		
		
 
	}
	
	public void buscarArticulos(String textoBusqueda, JPanel pnlArticulos,JComboBox filtrado) {
		limpiarPanel(pnlArticulos);
        pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
        ArrayList<Articulo> aAnyadidos = new ArrayList<>();
		for(Articulo a : Tienda.getArticulos()) {
			if (a.getNombre().toLowerCase().contains(textoBusqueda.toLowerCase())) {
				JPanel nuevo = crearPanelArticulo(a);
				pnlArticulos.add(nuevo);
				aAnyadidos.add(a);
			}	
		}
		filtrado.setVisible(true);
		filtrado.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String seleccion = (String) filtrado.getSelectedItem();
                    if (seleccion == "Mujer") {
                    	limpiarPanel(pnlArticulos);
                        pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
                        for (Articulo a: aAnyadidos) {
                        	if (a.getGenero()== Genero.MUJER) {
                        		JPanel nuevo = crearPanelArticulo(a);
                				pnlArticulos.add(nuevo);
                        	}
                        }
                    }
                    
                    if (seleccion == "Hombre") {
                    	limpiarPanel(pnlArticulos);
                        pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
                        for (Articulo a: aAnyadidos) {
                        	if (a.getGenero()== Genero.HOMBRE) {
                        		JPanel nuevo = crearPanelArticulo(a);
                				pnlArticulos.add(nuevo);
                        	}
                        }
                    }
                    
                    if (seleccion == "Niños") {
                    	limpiarPanel(pnlArticulos);
                        pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
                        for (Articulo a: aAnyadidos) {
                        	if (a.getGenero()== Genero.NINOS) {
                        		JPanel nuevo = crearPanelArticulo(a);
                				pnlArticulos.add(nuevo);
                        	}
                        }
                    }
                    
                    if (seleccion == "Todos los géneros") {
                    	limpiarPanel(pnlArticulos);
                        pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
                        for(Articulo a : Tienda.getArticulos()) {
                			if (a.getNombre().toLowerCase().contains(textoBusqueda.toLowerCase())) {
                				JPanel nuevo = crearPanelArticulo(a);
                				pnlArticulos.add(nuevo);
                			}
                        }
                        
                    }
               }
                
            }
                
                
                
                
            
        });
		
		
		}		        
	
}