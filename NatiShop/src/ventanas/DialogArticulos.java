package ventanas;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Compra;

public class DialogArticulos extends JDialog {
	private static final long serialVersionUID = 1L;
	private Compra compra;
	private JTable tArticulos;
	
	public DialogArticulos(Compra compra) {
		setResizable(false);
        setBounds(300, 200, 750, 400);
		
		
		this.compra = compra;
		
		tArticulos = new JTable();
		tArticulos.setModel(new ModeloTablaArticulos(compra.getArticulos()));
		JScrollPane spArticulos = new JScrollPane(tArticulos);
		this.add(spArticulos);
//		add(pTabla, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	
	
	
}
