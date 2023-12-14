package ventanas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.*;

import clases.Cliente;

public class ModeloTablaClientes extends DefaultTableModel {
	private List<Cliente>clientes;
	
	
	
	private final List<String> titulos= Arrays.asList("DNI","NOMBRE","FECHA DE NACIMIENTO","EMAIL", "TELEFONO", "PROVINCIA", "SALDO" );
	
	public ModeloTablaClientes(List<Cliente> usuarios){
		this.clientes= usuarios;
		
	}
	
	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}
	
	@Override
	public int getRowCount() {
		if(clientes !=null)
			return clientes.size();
		return 0;
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
			case 0: return c.getDni(); 
			case 1: return c.getNombre(); 
			case 2: return c.getfNacStr();
			case 3: return c.getCorreo();
			case 4: return c.getTlf();
			case 5: return c.getProvinciaStr();
			case 6: return c.getSaldo();
			
			default: return null;
		}
	}

}
