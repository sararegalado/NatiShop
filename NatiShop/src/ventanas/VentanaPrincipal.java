package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaPrincipal {
	static InicioSesion ventanaInicio;
	static VentanaRegistro reg;
	
	public static void main(String[] args) {
		ventanaInicio = new InicioSesion();
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
		ventanaInicio.add(btnAccederRegistro);
		
	}

}
