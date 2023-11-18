package clases;

public class Zapato extends Articulo {

	public Zapato() {
		super();
	}

	public Zapato(String id, String nombre, int unidades, double precio,Genero genero, Talla talla, String foto, Categoria categoria) {
		super(id,nombre, unidades, precio, genero, talla, foto,categoria);
	}

	@Override
	public String toString() {
		return "Zapato [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio + ", genero="
				+ genero + ", talla=" + talla + ", foto=" + foto + ", categoria=" + categoria + "]";
	}
	

}
