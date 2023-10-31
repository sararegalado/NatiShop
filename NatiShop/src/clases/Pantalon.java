package clases;

public class Pantalon extends Articulo {

	public Pantalon() {
		super();
	}

	public Pantalon(String id, int unidades, double precio, Talla talla) {
		super(id, unidades, precio, talla);
	}

	@Override
	public String toString() {
		return "Pantalon [id=" + id + ", unidades=" + unidades + ", precio=" + precio + ", talla=" + talla + "]";
	}

	
	
	
	
	
	

}
