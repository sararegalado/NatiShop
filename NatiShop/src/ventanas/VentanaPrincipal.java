package ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;
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

public class VentanaPrincipal extends JFrame {
	
	private JPanel pnlCentral, pnlArticulos, pnlContenedora, pnlNorte, pnlDchaMenu;
	private JFrame vActual, vAnterior;
	
	private JTextField tfBuscador;
	
	private JScrollPane spArticulos;
	
	private JButton btnDesplegar;
	private List<String> tallasSeleccionadas;
    private JCheckBox chkXS, chkS, chkM, chkL, chkXL, chkXXL;
    
    private String textoBusqueda;

	private static Tienda tienda = new Tienda();
	
	public static Tienda getTienda() {
		return tienda;
	}
	
	public static void setTienda(Tienda t) {
		tienda = t;
	}

	private static boolean clienteHaIniciadoSesion = false;
	
	
    public static boolean isClienteHaIniciadoSesion() {
		return clienteHaIniciadoSesion;
	}


	public static void setClienteHaIniciadoSesion(boolean cHaIniciadoSesion) {
		clienteHaIniciadoSesion = cHaIniciadoSesion;
	}


	private static JLabel lblNomU, lblSaldo;
	private static Object titulos;
	
	public static JLabel getLblNomU() {
		return lblNomU;
	}

	public static void setLblNomU(JLabel lblNomU) {
		VentanaPrincipal.lblNomU = lblNomU;
	}
	
	public static JLabel getLblSaldo() {
		return lblSaldo;
	}
	
	public VentanaPrincipal(JFrame va) {
		
		vActual = this;
		vAnterior = va;
		setBounds(100, 100, 1042, 693);
		((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 30, 20, 30));
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
		
		pnlContenedora = new JPanel(new BorderLayout());
		
		pnlCentral = new JPanel(new BorderLayout());
		pnlCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		pnlArticulos = new JPanel(new BorderLayout());
		JLabelGrafico fotoPortada = new JLabelGrafico("/imagenes/portada.png",1190,450);
        pnlArticulos.add(fotoPortada, BorderLayout.CENTER);
      

		spArticulos = new JScrollPane(pnlArticulos);
        spArticulos.setBounds(72,159,1153,490);
		pnlCentral.add(spArticulos, BorderLayout.CENTER);
		
		
		pnlNorte = new JPanel();
		pnlNorte.setLayout(null);
		pnlNorte.setPreferredSize(new Dimension(pnlNorte.getPreferredSize().width, 100));
		pnlContenedora.add(pnlNorte, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1153, 44);
        menuBar.setFont(new Font("Baskerville", Font.PLAIN, 14));
        pnlCentral.add(menuBar, BorderLayout.NORTH);
        pnlContenedora.add(pnlCentral, BorderLayout.CENTER);
        
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
        tfBuscador.setBounds(701, 26, 220, 26);
        pnlNorte.add(tfBuscador);
        tfBuscador.setColumns(10);
       
        
        JPopupMenu menuCliente = new JPopupMenu();
        JMenuItem perfil = new JMenuItem("Ver perfil");
        JMenuItem compras = new JMenuItem("Ver mis compras");
        JMenuItem saldo = new JMenuItem("Añadir saldo a mi cuenta");
        
        menuCliente.add(perfil);
        menuCliente.add(saldo);
        menuCliente.add(compras);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/nombreTienda.png")));
        lblLogo.setBounds(10, 11, 303, 80);
        pnlNorte.add(lblLogo);
        lblLogo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
        lblLogo.setFont(new Font("Baskerville", Font.PLAIN, 45));
        lblLogo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarPanel(pnlArticulos);
				pnlArticulos.setLayout(new BorderLayout());
				
		        //PORTADA DE LA TIENDA
				JLabelGrafico fotoPortada = new JLabelGrafico("/imagenes/portada.png",1270,490);
		        pnlArticulos.add(fotoPortada, BorderLayout.CENTER);
		        tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
		        //filtrado.setVisible(false);
			}
			
		});
        
        JLabel lblUsuario = new JLabel("");
        lblUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/usuario.png")));
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setBounds(1063, 11, 57, 52);
        pnlNorte.add(lblUsuario);
        
        lblUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        //filtrado.setVisible(false);
                if (clienteHaIniciadoSesion) {
                	menuCliente.show(lblUsuario, 0, lblUsuario.getHeight());
                } else {
                    new VentanaInicioSesion(vActual);
                }
            }
        });
        
        //FILTRO MENU
        pnlDchaMenu= new JPanel(new GridLayout(8, 1));
        pnlDchaMenu.setPreferredSize(new Dimension(225,500));
        pnlDchaMenu.setVisible(false);
        pnlContenedora.add(pnlDchaMenu, BorderLayout.EAST);
		//pnlDchaMenu.setBackground(new Color(220, 220, 220));
		pnlDchaMenu.setBorder(new EmptyBorder(10, 10, 10, 10 ));
		
		JLabel lblTit = new JLabel ("FILTRAR LOS ARTICULOS");
		lblTit.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		pnlDchaMenu.add(lblTit);
		
		JLabel lblGen = new JLabel ("Selecciona un género:");
		pnlDchaMenu.add(lblGen);
		String[] opciones = {"Todos los géneros","Hombre","Mujer","Niños"};
		JComboBox<String> cbGenero = new JComboBox<>(opciones);
		pnlDchaMenu.add(cbGenero);
		
		//pnlDchaMenu.add(new JPanel());
		JLabel lblTalla = new JLabel ("Selecciona una talla:");
		pnlDchaMenu.add(lblTalla);
		tallasSeleccionadas = new ArrayList<>();
        // Crear checkboxes para cada talla
        chkXS = new JCheckBox("XS");
        chkS = new JCheckBox("S");
        chkM = new JCheckBox("M");
        chkL = new JCheckBox("L");
        chkXL = new JCheckBox("XL");
        chkXXL = new JCheckBox("XXL");
        JPanel pnlTallas = new JPanel(new FlowLayout());
        pnlTallas.add(chkXS);
        pnlTallas.add(chkS);
        pnlTallas.add(chkM);
        pnlTallas.add(chkL);
        pnlTallas.add(chkXL);
        pnlTallas.add(chkXXL);
        pnlDchaMenu.add(pnlTallas);


        chkXS.addActionListener((e) -> {
        	tallasSeleccionadas.add("XS");
        	buscarArticulos(textoBusqueda, pnlArticulos, cbGenero, tallasSeleccionadas);
		});
        
        chkS.addActionListener((e) -> {
        	tallasSeleccionadas.add("S");
        	buscarArticulos(textoBusqueda, pnlArticulos, cbGenero, tallasSeleccionadas);
		});
        
        chkM.addActionListener((e) -> {
        	tallasSeleccionadas.add("M");
        	buscarArticulos(textoBusqueda, pnlArticulos, cbGenero, tallasSeleccionadas);
		});
        
        chkL.addActionListener((e) -> {
        	tallasSeleccionadas.add("L");
        	buscarArticulos(textoBusqueda, pnlArticulos, cbGenero, tallasSeleccionadas);
		});
        
        chkXL.addActionListener((e) -> {
        	tallasSeleccionadas.add("XL");
        	buscarArticulos(textoBusqueda, pnlArticulos, cbGenero, tallasSeleccionadas);
		});
        
        chkXXL.addActionListener((e) -> {
        	tallasSeleccionadas.add("XXL");
        	buscarArticulos(textoBusqueda, pnlArticulos, cbGenero, tallasSeleccionadas);
		});        
		
        
        btnDesplegar = new JButton();
		btnDesplegar.setBackground(Color.WHITE);
		btnDesplegar.setPreferredSize(new Dimension(27,27));
		btnDesplegar.setBounds(1147, 11, 52, 52);
		pnlNorte.add(btnDesplegar);
		btnDesplegar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/btnDesplegar.png")));

		btnDesplegar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlDchaMenu.setVisible(!pnlDchaMenu.isVisible());
			}
		});
		
		 //LISTENERS DEL BUSCADOR
        tfBuscador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textoBusqueda = tfBuscador.getText();
				buscarArticulos(textoBusqueda, pnlArticulos, cbGenero, tallasSeleccionadas);
			}
		});
        tfBuscador.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfBuscador.setText(""); // Borrar el texto por defecto al obtener el foco
            }
            
            
        });
       
        
        JLabel lblCarro = new JLabel("");
        lblCarro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/carro.png")));
        lblCarro.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarro.setBounds(955, 11, 67, 52);
        pnlNorte.add(lblCarro);
        
        lblCarro.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaCompras c = new VentanaCompras(vActual);
				c.setVisible(true);
				
			}
		});

        perfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Cliente c = VentanaInicioSesion.getCliente();
            	new VentanaDatosUsuario(vActual, c);
                
            }
        });
        
        compras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaVerMisCompras(VentanaInicioSesion.getCliente());
				
			}
		});
        
        saldo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = VentanaInicioSesion.getCliente();
				if (clienteTieneTarjeta(c)) {
					SpinnerModel spinnerModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.5);
					JSpinner moneySpinner = new JSpinner(spinnerModel);
					
					//Panel para el OptionPane
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					panel.add(new JLabel("Introduce la cantidad de dinero que deseas añadir a tu cuenta:"));
					panel.add(moneySpinner);
					panel.setPreferredSize(new Dimension(200, 80));
					int result = JOptionPane.showOptionDialog( null, panel, "Cantidad de Dinero", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, null, null);
					if (result == JOptionPane.OK_OPTION) {
						double saldo = (double) moneySpinner.getValue();
						
						Connection con = BD.initBD("NatiShop.db");
						BD.modificarSaldo(con, c.getDni(), saldo);
						BD.closeBD(con);
						lblSaldo.setText(saldo+"€");
						
						JOptionPane.showMessageDialog(null, "Has añadido " + saldo + "€ a tu cuenta");
				        } else {
				            JOptionPane.showMessageDialog(null, "Operación cancelada");
				        }
					
					
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Para añadir saldo a tu cuenta primero debes tener una tarjeta registrada. \n Registra tu tarjeta en: Ver perfil > ¿DESEAS AÑADIR UN NUMERO DE TARJETA A TU CUENTA? ");
				}
				
			}
        	
        });
        

        lblNomU = new JLabel("Iniciar Sesión");
        lblNomU.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomU.setVerticalAlignment(SwingConstants.TOP);
        lblNomU.setBounds(1046, 67, 91, 22);
        pnlNorte.add(lblNomU);
        
        //Saldo
        lblSaldo = new JLabel("");
        lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSaldo.setBounds(1046, 87, 91, 8);
        pnlNorte.add(lblSaldo);
        
        //LISTENERS DE LOS ITEMS DE HOMBRE
        
        
        menuItemCamiH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tfBuscador.setText("BUSCA UN ARTICULO, COLOR...");
 		        //filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
        		pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
            	pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
            	pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
            	pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
            	pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
            	pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
            	limpiarPanel(pnlArticulos);
            	pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
        		pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
        		pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
        		pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
        		pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
 		        //filtrado.setVisible(false);
        		limpiarPanel(pnlArticulos);
        		pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        
        
        getContentPane().add(pnlContenedora);
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Nati Shop");
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("imagenes/icono.png")).getImage());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	   
		public boolean clienteHaIniciadoSesion() {
		    return clienteHaIniciadoSesion;
		}
		
		public static boolean clienteTieneTarjeta(Cliente c) {
			Connection con = BD.initBD("NatiShop.db");
			String tarj = BD.obtenerTarjCliente(con, c.getDni());
			BD.closeBD(con);
			
			if (tarj.equals("Tarjeta sin registrar")) {
				return false;
			}else {
				return true;
			}
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
					VentanaArticulo producto = new VentanaArticulo(articulo);
					producto.setVisible(true);
					
				}
			});
			
			
			
			
			return panelArticulo;	
	 
		}
		
		public void buscarArticulos(String textoBusqueda, JPanel pnlArticulos,JComboBox filtrado, List<String> tallasSeleccionadas) {
			limpiarPanel(pnlArticulos);
			pnlArticulos.setBorder(new EmptyBorder(10, 10, 10, 10));
	        pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
	        ArrayList<Articulo> aAnyadidos = new ArrayList<>();
			for(Articulo a : Tienda.getArticulos()) {
				if (a.getNombre().toLowerCase().contains(textoBusqueda.toLowerCase())) {
					JPanel nuevo = crearPanelArticulo(a);
					pnlArticulos.add(nuevo);
					aAnyadidos.add(a);
				}	
			}
			//filtrado.setVisible(true);
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
			
			if(!tallasSeleccionadas.isEmpty()) {
				for(String t : tallasSeleccionadas) {
					limpiarPanel(pnlArticulos);
                    pnlArticulos.setLayout(new GridLayout(0, 4, 10, 10));
                    for(Articulo a : aAnyadidos) {
            			if (a.getTallaStr().equals(t)) {
            				JPanel nuevo = crearPanelArticulo(a);
            				pnlArticulos.add(nuevo);
            			}
                    }
					
				}
				
			}
			
			
			
			
			}	
		
		public void setArticulosQuePuedoComprar(Set<Articulo> art, JPanel panel) {
			this.pnlArticulos = panel;
			for (Articulo a: art) {
				JPanel pnlArticulo = crearPanelArticulo(a);
				panel.add(pnlArticulo);
			}	
		}
	
}