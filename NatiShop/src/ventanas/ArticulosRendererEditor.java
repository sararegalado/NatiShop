package ventanas;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import clases.Compra;
import ventanas.DialogArticulos;
import ventanas.VentanaAdministrador;

public class ArticulosRendererEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	
	private Compra compra;
	private VentanaAdministrador ventAdmin;
	
	public ArticulosRendererEditor(VentanaAdministrador ventAdmin) {
		this.ventAdmin = ventAdmin;
	}
	
	private JButton prepare(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		compra = (Compra) value;
		JButton button = new JButton("Articulos");
		button.setEnabled(true);
		button.setBackground(table.getBackground());
		
		if (isSelected || hasFocus) {
			button.setBackground(table.getSelectionBackground());
		}
		
		button.addActionListener( (e) -> {
			DialogArticulos art = new DialogArticulos(compra);
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
		return compra;
	}
	
    @Override
    public boolean shouldSelectCell(EventObject event) {
        return true;
    }

}
