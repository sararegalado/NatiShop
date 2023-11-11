package clases;

public class Zapato extends Articulo {

	public Zapato() {
		super();
	}

	public Zapato(String id, String nombre, int unidades, double precio, Talla talla, String foto) {
		super(id,nombre, unidades, precio, talla, foto);
	}

	@Override
	public String toString() {
		return "Zapato [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio + ", talla="
				+ talla + ", foto=" + foto + "]";
	}
	

}
