package ventanas;

import javax.swing.*;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;


public class VentanaAdmin extends JFrame{
	
	public class MainApp {
	    public static void main(String[] args) {
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                VentanaAdmin ventanaAdmin = new VentanaAdmin();
	                ventanaAdmin.setVisible(true);
	            }
	        });
	    }
	}

	
	
	
	private JPanel contentPane; 
	private JMenuBar menuBar;
	private JMenuItem menuItemUsuarios;
	
	
	
	private JTable tablaUsuarios ;
	private ModeloTablaUsuarios modeloUsuarios;
	private JScrollPane sTablaUsuarios;
	


	
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
		

		JPanel panel_Centro = new JPanel();
		panel_Centro.setBounds(270, 11, 645, 562);
		contentPane.add(panel_Centro);
		
		JMenuBar menuBar= new JMenuBar();
		menuBar.setBounds(0,0,250,562);
		panel.add(menuBar);
		menuBar.setFont(new Font("Baskerville", Font.PLAIN, 14));
		menuBar.setLayout(new GridLayout(5,1));
		
		JMenuItem menuItemUsuarios = new JMenuItem("USUARIOS");
		menuItemUsuarios.setBackground(new Color(240, 240, 240));
		menuItemUsuarios.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		menuItemUsuarios.setBounds(-51, 62, 20, 562);
		menuBar.add(menuItemUsuarios);
		
		menuItemUsuarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tablaUsuarios = new JTable(modeloUsuarios);
				panel_Centro.add(tablaUsuarios);
				
				
				
				
			}
			
		});
		
		
		JMenuItem MenuItemArticulos = new JMenuItem("ARTICULOS");
		menuBar.add(MenuItemArticulos);
		
		
		
		
		 setVisible(true);
		
		}
	
	public static void main(String[] args) {
		VentanaAdmin ventanaAdmin = new VentanaAdmin();
		
	}
	
	
	
	}

