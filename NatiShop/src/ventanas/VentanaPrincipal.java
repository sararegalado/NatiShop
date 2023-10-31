package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Baskerville", Font.PLAIN, 14));
		menuBar.setBounds(123, 28, 573, 57);
		contentPane.add(menuBar);
		
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
        
	}
}
