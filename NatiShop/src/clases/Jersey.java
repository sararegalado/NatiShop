
package clases;

public class Jersey extends Articulo {
	public Jersey() {
		super();
	}

	public Jersey(String id, String nombre, int unidades, double precio, Talla talla, String foto) {
		super(id, nombre, unidades, precio, talla, foto);
	}

	@Override
	public String toString() {
		return "Jersey [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio + ", talla="
				+ talla + ", foto=" + foto + "]";
	}

	
	
	
	

}
