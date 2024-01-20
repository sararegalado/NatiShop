package ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import clases.Compra;

public class ModeloTablaCompras extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<Compra> compras;
	private final List<String> headers = Arrays.asList(
			"ID COMPRA",
			"CLIENTE",
			"FECHA",
			"VER ARTICULOS");
	
	public ModeloTablaCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}

	@Override
	public int getRowCount() {
		if (compras != null) {
			return compras.size();
		} else { 
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return headers.size(); 
	}
	
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return (columnIndex >= headers.size()-2);
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {    	
    }
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Compra compra = compras.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return Integer.valueOf(compra.getIdCompra());
			case 1: return compra.getCliente().getDni();
			case 2: return compra.getFechaStr();
			case 3: return compra;
			default: return null;
		}
	}
}
	
	

