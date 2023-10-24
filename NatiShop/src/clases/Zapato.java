package clases;

public class Zapato extends Articulo {

	public Zapato() {
		super();
	}

	public Zapato(String id, int unidades, double precio, Talla talla) {
		super(id, unidades, precio, talla);
	}

	@Override
	public String toString() {
		return "Zapato [id=" + id + ", unidades=" + unidades + ", precio=" + precio + ", talla=" + talla + "]";
	}
	

}
