package clases;

public class Camiseta extends Articulo {

	public Camiseta() {
		super();
	}

	public Camiseta(String id,String nombre, int unidades, float precio,Genero genero, Talla talla, String foto, Categoria categoria) {
		super(id, nombre, unidades, precio, genero, talla, foto, categoria);
	}

	@Override
	public String toString() {
		return  id + "	" + nombre + "	" + talla + "	" + precio ;
	}
}