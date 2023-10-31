package ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioSesion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlInicioSesion = new JPanel();
		pnlInicioSesion.setBounds(238, 37, 267, 317);
		contentPane.add(pnlInicioSesion);
		pnlInicioSesion.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ACCEDE A TU CUENTA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 6, 226, 23);
		lblNewLabel.setFont(new Font("Baskerville", Font.PLAIN, 20));
		pnlInicioSesion.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		panel.setBounds(33, 71, 183, 204);
		pnlInicioSesion.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(61, 22, 61, 16);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(33, 51, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Baskerville", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(43, 89, 103, 16);
		panel.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(33, 117, 130, 26);
		panel.add(textField_1);
		
		JButton btnNewButton = new JButton("Iniciar sesión");
		btnNewButton.setBounds(33, 157, 117, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("¿No tienes cuenta? Haz click aqui para registrarte");
		btnNewButton_1.setBounds(202, 355, 334, 43);
		contentPane.add(btnNewButton_1);
	}
}
