package ventanas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.*;

import clases.Usuario;

public class ModeloTablaUsuarios extends DefaultTableModel {
	
	private static List<Usuario> usuarios = new ArrayList<>();
	
	private final List<String> titulos= Arrays.asList("DNI","NOMBRE","FECHA DE NACIMIENTO","CORREO");
	
	public ModeloTablaUsuarios(List<Usuario> usuarios){
		this.usuarios= usuarios;
		
	}
	
	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}
	
	@Override
	public int getRowCount() {
		if(usuarios !=null)
			return usuarios.size();
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
		Usuario u = usuarios.get(row);
		switch(column) {
			case 0: return u.getDni(); 
			case 1: return u.getNombre(); 
			case 2: return u.getfNacStr();
			case 3: return u.getCorreo();
			default: return null;
		}
	}

}
