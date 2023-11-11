package clases;

public class Camiseta extends Articulo {

	public Camiseta() {
		super();
	}

	public Camiseta(String id,String nombre, int unidades, double precio, Talla talla, String foto) {
		super(id, nombre, unidades, precio, talla, foto);
	}

	@Override
	public String toString() {
		return "Camiseta [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio
				+ ", talla=" + talla + ", foto=" + foto + "]";
	}
}
