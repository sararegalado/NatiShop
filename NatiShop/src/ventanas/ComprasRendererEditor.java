package ventanas;

import java.awt.Component;
import java.sql.Connection;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import clases.Cliente;
import clases.Compra;

public class ComprasRendererEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private VentanaAdministrador ventAdmin;
	
	public ComprasRendererEditor(VentanaAdministrador ventAdmin) {
		this.ventAdmin = ventAdmin;
	}
	
	private JButton prepare(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		cliente = (Cliente) value;
		JButton button = new JButton("Compras");
		button.setEnabled(true);
		button.setBackground(table.getBackground());
		
		if (isSelected || hasFocus) {
			button.setBackground(table.getSelectionBackground());
		}
		
		button.addActionListener( (e) -> {
			Connection con = BD.initBD("NatiShop.db");
			if (BD.getComprasPorCliente(con, cliente).isEmpty() || BD.getComprasPorCliente(con, cliente) == null ) {
		        JOptionPane.showMessageDialog(null, "El cliente seleccionado no dispone de compras realizadas", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		        
			} else {
				DialogCompras comp = new DialogCompras(cliente);
			}
		});
		
		button.setOpaque(true);
		return button;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return prepare(table, value, isSelected, false, row, column);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return prepare(table, value, isSelected, hasFocus, row, column);		
	}
	
	@Override
	public Object getCellEditorValue() {
		return cliente;
	}
	
    @Override
    public boolean shouldSelectCell(EventObject event) {
        return true;
    }

}


