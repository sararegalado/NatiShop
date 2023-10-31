package ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEmail;
	private JPasswordField pfConstrasena;
	private JPasswordField pfConfirmaCont;
	

	
	public VentanaRegistro(JFrame ventOrigen) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(254, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel pnlInicioSesion = new JPanel();
		pnlInicioSesion.setBackground(new Color(254, 255, 255));
		pnlInicioSesion.setForeground(new Color(254, 255, 255));
		pnlInicioSesion.setLayout(null);
		pnlInicioSesion.setBounds(145, 16, 433, 365);
		contentPane.add(pnlInicioSesion);
		
		JLabel lblNewLabel = new JLabel("CREAR CUENTA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Baskerville", Font.PLAIN, 20));
		lblNewLabel.setBounds(103, 21, 226, 23);
		pnlInicioSesion.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		panel_1.setBounds(81, 57, 255, 285);
		pnlInicioSesion.add(panel_1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblEmail.setBounds(87, 24, 80, 26);
		panel_1.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(43, 52, 180, 26);
		panel_1.add(tfEmail);
		
		JLabel lblContraseña = new JLabel("Crea una nueva contraseña");
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseña.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblContraseña.setBounds(31, 90, 206, 26);
		panel_1.add(lblContraseña);
		
		pfConstrasena = new JPasswordField();
		pfConstrasena.setBounds(70, 128, 130, 26);
		panel_1.add(pfConstrasena);
		
		JButton btnregistro = new JButton("Registrar cuenta");
		btnregistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnregistro.setBounds(55, 250, 153, 29);
		panel_1.add(btnregistro);
		
		JLabel lblConfirmaContr = new JLabel("Confirma tu contraseña");
		lblConfirmaContr.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmaContr.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblConfirmaContr.setBounds(43, 166, 180, 26);
		panel_1.add(lblConfirmaContr);
		
		pfConfirmaCont = new JPasswordField();
		pfConfirmaCont.setBounds(70, 204, 130, 26);
		panel_1.add(pfConfirmaCont);
		
		
		
		this.addWindowListener( new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ventOrigen.setVisible( false );
			}
			@Override
			public void windowClosed(WindowEvent e) {
				ventOrigen.setVisible( true );
			}
		});
	}
}
