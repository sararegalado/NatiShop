package ventanas;

import java.sql.Connection;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import clases.Cliente;
import clases.Compra;

public class DialogCompras extends JDialog {
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	JTable tCompras;
	private VentanaAdministrador v;
	public DialogCompras(Cliente cliente) {
		setResizable(false);
        setBounds(300, 200, 750, 400);		
		
		this.cliente = cliente;
		
		tCompras = new JTable();
		Connection con = BD.initBD("NatiShop.db");
		List<Compra> compras = BD.getComprasPorCliente(con, cliente);
		tCompras.setModel(new ModeloTablaCompras(compras));
//		tCompras.getColumnModel().getColumn(3).setCellRenderer(new ArticulosRendererEditor(v));		
//		tCompras.getColumnModel().getColumn(3).setCellEditor(new ArticulosRendererEditor(v));		
		JScrollPane spCompras = new JScrollPane(tCompras);
		this.add(spCompras);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	

}

