package ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import clases.Cliente;

public class ModeloTablaSolicitudes extends DefaultTableModel {
	private List<Cliente>clientes;
	
	
	
	private final List<String> titulos= Arrays.asList("DNI","NOMBRE","PUESTO DESEADO","RAZON");
	
	public ModeloTablaSolicitudes(List<Cliente> usuarios){
		this.clientes= usuarios;
		
	}
	
	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}
	
	@Override
	public int getRowCount() {
		if (clientes != null) {
			return clientes.size();
		} else { 
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		return titulos.size();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
    	return false;
	}
	
	@Override
	public Object getValueAt(int row,int column) {
		Cliente c = clientes.get(row);
		switch(column) { 
			case 0: return String.valueOf(c.getDni()); 
			case 1: return String.valueOf(c.getNombre()); 
			case 2: 
			case 3: 
			default: return null;
		}
	}

}
