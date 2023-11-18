
package clases;

public class Jersey extends Articulo {
	public Jersey() {
		super();
	}

	public Jersey(String id, String nombre, int unidades, double precio, Genero genero, Talla talla, String foto, Categoria categoria) {
		super(id, nombre, unidades, precio, genero, talla, foto, categoria);
	}

	@Override
	public String toString() {
		return "Jersey [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio + ", genero="
				+ genero + ", talla=" + talla + ", foto=" + foto + ", categoria=" + categoria + "]";
	}

	
	
	
	

}
