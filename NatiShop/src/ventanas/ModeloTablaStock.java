package ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import clases.Articulo;

public class ModeloTablaStock extends DefaultTableModel{
	private List<Articulo>articulos;
	
	private final List<String>titulos= Arrays.asList("DESCRIPCION DEL ARTICULO", "SECCION", "NUMERO DE  ARTICULOS DISPONIBLES");
	
	public ModeloTablaStock(List<Articulo> articulos) {
		this.articulos= articulos;
		
	}

	@Override
	public int getRowCount() {
		if(articulos != null) {
			return articulos.size();
		} else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return titulos.size();
	}

	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Articulo a = articulos.get(row);
		switch(column) {
		case 0: return a.getNombre();
		case 1: return a.getGenero();
		case 2: return a.getUnidades();
		default: return null;
		}
		
	}
	
	
	
	
	
}
