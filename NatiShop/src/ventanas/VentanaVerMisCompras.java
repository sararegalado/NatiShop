package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Articulo;
import clases.Cliente;
import clases.Compra;

public class VentanaVerMisCompras extends JFrame {
	
	private JPanel panelCentro, pnlIzq;
	private JTextArea texto;
	private JLabel titulo;
	
	private DefaultListModel<Compra> modeloListaCompras; //La estructura de datos que guarda la información
	private JList<Compra> listaCompras; //La estructura que presenta la información en pantalla
	private JScrollPane scrollListaCompras;
	
	
	public VentanaVerMisCompras(Cliente c) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 455);
		
		panelCentro = new JPanel(new GridLayout(1,2));
		pnlIzq = new JPanel(new BorderLayout());
		pnlIzq.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel titulo = new JLabel("Selecciona la compra para ver su ticket...");
		titulo.setFont(new Font("Calibri", Font.BOLD| Font.ITALIC, 15));
		pnlIzq.add(titulo, BorderLayout.NORTH);
		
		modeloListaCompras = new DefaultListModel<>();
		listaCompras = new JList<>(modeloListaCompras);
		scrollListaCompras = new JScrollPane(listaCompras);
		
		pnlIzq.add(scrollListaCompras, BorderLayout.CENTER);
		
		cargarListaDeCompras(c);
		
		
		
		panelCentro.add(pnlIzq);
		
		texto = new JTextArea();
		texto.setEditable(false);
		panelCentro.add(texto);
		
		
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		listaCompras.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        int selectedIndex = listaCompras.getSelectedIndex();
		        if (selectedIndex != -1) {
		            Connection con = BD.initBD("NatiShop.db");
		            List<Compra> compras = BD.getComprasPorCliente(con, c);
		            BD.closeBD(con);
		            Compra compraSeleccionada = compras.get(selectedIndex);

		            Thread t = new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    StringBuilder contenido = new StringBuilder();
		                    contenido.append("\nNATISHOP\n");
		                    contenido.append("Gran Vía, 23\n");
		                    contenido.append("\nFecha expedición: " + compraSeleccionada.getFechaStr() + "\n");
		                    contenido.append("-----------------------------------------------------------\n");

		                    for (Articulo a : compraSeleccionada.getArticulos()) {
		                        contenido.append(a.toString() + "\n");
		                        actualizarTextArea(contenido.toString(), texto);
		                        try {
		                            Thread.sleep(1000);
		                        } catch (InterruptedException ex) {
		                            ex.printStackTrace();
		                        }
		                    }

		                    contenido.append("\nTOTAL " + "(" + compraSeleccionada.getArticulos().size() + ")				" + compraSeleccionada.getPrecio() + "\n");
		                    actualizarTextArea(contenido.toString(), texto);
		                }

		                private void actualizarTextArea(String texto, JTextArea textoArea) {
		                    SwingUtilities.invokeLater(() -> {
		                        synchronized (texto) {
		                            textoArea.setText(texto);
		                            textoArea.repaint();
		                        }
		                    });
		                }
		            });

		            t.start();
		        }
		    }
		});
		
		setLocationRelativeTo(null);
		setVisible(true);
	}


	private void cargarListaDeCompras(Cliente c) {
		Connection con = BD.initBD("NatiShop.db");
		List<Compra> compras = BD.getComprasPorCliente(con, c);
		BD.closeBD(con);
		for (Compra com : compras) {
			modeloListaCompras.addElement(com);
			
		}
		
	}
	
	

}
