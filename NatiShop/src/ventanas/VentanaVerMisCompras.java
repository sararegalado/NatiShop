package ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clases.Cliente;

public class VentanaVerMisCompras extends JFrame {
	
	private JPanel panelCentro, pnlIzq;
	private TextArea texto;
	
	
	public VentanaVerMisCompras(Cliente c) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		
		panelCentro = new JPanel(new GridLayout(1,2));
		pnlIzq = new JPanel();
		panelCentro.add(pnlIzq);
		
		texto = new TextArea();
		panelCentro.add(texto);
		
		
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		
		
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
