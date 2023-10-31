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
//		ventanaInicio = new InicioSesion();
//		ventanaInicio.setVisible(true);
//		JButton btnAccederRegistro = new JButton("¿No tienes cuenta? Haz click aqui para registrarte");
//		btnAccederRegistro.setBounds(202, 355, 334, 43);
//		btnAccederRegistro.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//			reg = new VentanaRegistro(ventanaInicio);
//			reg.setVisible(true);
//				
//			}
//		});
//		ventanaInicio.getContentPane().add(btnAccederRegistro);
		
	}
	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(72, 97, 563, 50);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 604, 44);
        panel.add(menuBar);
        menuBar.setFont(new Font("Baskerville", Font.PLAIN, 14));
        
        JMenu menuHombre = new JMenu("Hombre");
        menuHombre.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuBar.add(menuHombre);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
        mntmNewMenuItem.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuHombre.add(mntmNewMenuItem);
        JMenu menuMujer = new JMenu("Mujer");
        menuMujer.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuBar.add(menuMujer);
        
        JMenu menuNinos = new JMenu("Niños");
        menuNinos.setFont(new Font("Baskerville", Font.PLAIN, 14));
        menuBar.add(menuNinos);
        
        JLabel lblNewLabel = new JLabel("NatiShop");
        lblNewLabel.setBounds(72, 24, 258, 72);
        contentPane.add(lblNewLabel);
        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Baskerville", Font.PLAIN, 40));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(181, 192, 324, 207);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblUsuario = new JLabel("");
        lblUsuario.setBounds(717, 6, 55, 50);
        contentPane.add(lblUsuario);
        lblUsuario.setIcon(new ImageIcon("/Users/sararegalado/Downloads/Captura de pantalla 2023-10-31 a las 13.25.31.png"));
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
