package ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import clases.Articulo;
import clases.Categoria;
import clases.Genero;
import clases.Talla;

public class ModeloTablaArticulos extends DefaultTableModel{
//	public Articulo(String id, String nombre, int unidades, float precio, Genero genero, Talla talla, String foto, Categoria categoria) {
	private List<Articulo> articulos;
	private final List<String> headers = Arrays.asList(
			"ID",
			"NOMBRE",
			"UNIDADES",
			"PRECIO",
			"GENERO",
			"TALLA",
			"FOTO",
			"CATEGORIA");
	
	
	public ModeloTablaArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}

	@Override
	public int getRowCount() {
		if (articulos != null) {
			return articulos.size();
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
		Articulo articulo = articulos.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return String.valueOf(articulo.getId());
			case 1: return String.valueOf(articulo.getNombre());
			case 2: return Integer.valueOf(articulo.getUnidades());
			case 3: return Float.valueOf(articulo.getPrecio());
			case 4: return String.valueOf(articulo.getGeneroStr());
			case 5: return String.valueOf(articulo.getTallaStr());
			case 6: return String.valueOf(articulo.getFoto());
			case 7: return String.valueOf(articulo.getCategoriaStr());
			default: return null;
		}
	}
}
	
	

