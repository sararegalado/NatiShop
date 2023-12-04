package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import clases.Jornada;

public class VentanaSolicitudJornada extends JFrame {
	
	private JPanel pnlBoton;
	private JLabel lblTitulo,lblCombo;
	private JComboBox cbJornadas;
	private JTextField tfRazones;
	private JButton btnEnvio;
	
	private JFrame vActual,vAnterior;
	
	public VentanaSolicitudJornada(JFrame va) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 500);
		setLayout(new GridLayout(6,1,10,10));
		
		lblTitulo = new JLabel("<html><u>" + "SOLICITUD DE CAMBIO DE JORNADA" + "</u></html>");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 20));
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		this.add(lblTitulo);
		
		lblCombo= new JLabel("Seleccione la jornada deseada: ");
		lblCombo.setFont(new Font("Calibri", Font.BOLD|Font.ITALIC, 15 ));
		this.add(lblCombo);
		
		cbJornadas = new JComboBox<>(Jornada.values());
		cbJornadas.setPreferredSize(new Dimension(10, 5));
		this.add(cbJornadas);
		
		tfRazones = new JTextField();
		tfRazones.setText("Argumente el motivo de su cambio...");
		this.add(tfRazones);
		tfRazones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfRazones.setText("");
				
			}
			
		});
		
		
		
		btnEnvio= new JButton("ENVIAR");
		btnEnvio.setPreferredSize(new Dimension(50, 50));
		this.add(btnEnvio, BorderLayout.CENTER);
		btnEnvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(tfRazones.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "El campo de comentario es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            	}else{
            		String jornadaSeleccionada = cbJornadas.getSelectedItem().toString();
                    String comentario = tfRazones.getText();
                    JOptionPane.showMessageDialog(null, "Solicitud enviada:\nJornada: " + jornadaSeleccionada + "\nRazones: " + comentario, "ENVIO DE SOLICITUD", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
            	};
            }
        });

		
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
}
