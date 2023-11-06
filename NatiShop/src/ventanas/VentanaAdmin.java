package ventanas;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class VentanaAdmin extends JFrame{
	
	private JPanel contentPane;
	
	public static void main(String[] args) {
		VentanaAdmin ventana= new VentanaAdmin();
		ventana.setVisible(true);
	}
	public VentanaAdmin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 1121, 621);
		
		contentPane= new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel= new JPanel();
		panel.setBounds(10,11,250,562);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar= new JMenuBar();
		menuBar.setBounds(10,11,250,562);
		panel.add(menuBar);
		menuBar.setFont(new Font("Baskerville", Font.PLAIN, 14));
		
		
		
	}
}
