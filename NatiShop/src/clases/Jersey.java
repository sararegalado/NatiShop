
package clases;

public class Jersey extends Articulo {
	public Jersey() {
		super();
	}

	public Jersey(String id, int unidades, double precio, Talla talla) {
		super(id, unidades, precio, talla);
	}

	@Override
	public String toString() {
		return "Jersey [id=" + id + ", unidades=" + unidades + ", precio=" + precio + ", talla=" + talla + "]";
	}
	
	
	

}
