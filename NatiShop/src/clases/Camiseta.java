package clases;

public class Camiseta extends Articulo {
		

	public Camiseta() {
		super();
	}

	public Camiseta(String id, int unidades, double precio, Talla talla) {
		super(id, unidades, precio, talla);
	}

	@Override
	public String toString() {
		return "Camiseta [id=" + id + ", unidades=" + unidades + ", precio=" + precio + ", talla=" + talla + "]";
	}
	
	
}
