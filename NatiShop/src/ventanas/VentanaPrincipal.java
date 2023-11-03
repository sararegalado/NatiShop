package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.List;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Canvas;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	static InicioSesion ventanaInicio;
	static VentanaRegistro reg;
	
	private JPanel contentPane;
	
	
	public static void main(String[] args) {
		
		VentanaPrincipal vent = new VentanaPrincipal();
		vent.setVisible(true);
		/*ventanaInicio = new InicioSesion();
		ventanaInicio.setVisible(true);
		JButton btnAccederRegistro = new JButton("¿No tienes cuenta? Haz click aqui para registrarte");
		btnAccederRegistro.setBounds(202, 355, 334, 43);
		btnAccederRegistro.addActionListener(new ActionListener() {
			
			@Override
		public void actionPerformed(ActionEvent e) {
			reg = new VentanaRegistro(ventanaInicio);
			reg.setVisible(true);
				
			}
	});
	ventanaInicio.getContentPane().add(btnAccederRegistro);*/
		
	}
	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1042, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(72, 97, 900, 43);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 901, 44);
        panel.add(menuBar);
        menuBar.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        JMenu menuHombre = new JMenu("Hombre");
        menuHombre.setFont(new Font("Baskerville", Font.PLAIN, 14));
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
        menuMujer.setFont(new Font("Baskerville", Font.PLAIN, 14));
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
        menuNinos.setFont(new Font("Baskerville", Font.PLAIN, 14));
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
        
        JLabel lblNewLabel = new JLabel("NatiShop");
        lblNewLabel.setBounds(72, 24, 258, 72);
        contentPane.add(lblNewLabel);
        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Baskerville", Font.PLAIN, 40));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(82, 159, 423, 240);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblUsuario = new JLabel("");
        lblUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/ventanas/fotoUsuario.png")));
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setBounds(984, 6, 52, 52);
        contentPane.add(lblUsuario);
        lblUsuario.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		InicioSesion ventUsuario = new InicioSesion();
        		ventUsuario.setVisible(true);
        		JButton btnAccederRegistro = new JButton("¿No tienes cuenta? Haz click aqui para registrarte");
        		btnAccederRegistro.setBounds(202, 355, 334, 43);
        		btnAccederRegistro.addActionListener(new ActionListener() {
        			
        			@Override
        			public void actionPerformed(ActionEvent e) {
        			reg = new VentanaRegistro(ventUsuario);
        			reg.setVisible(true);
        				
        			}
        		});
        		ventUsuario.getContentPane().add(btnAccederRegistro);
        		
        	}
        });
        
        
        
        
	}
}
