package clases;

public class Camiseta extends Articulo {
		private Talla talla;

		public Camiseta() {
			super();
		}

		public Camiseta(String id, int unidades, double precio, Talla talla) {
			super(id, unidades, precio);
			this.talla = talla;
		}

		public Talla getTalla() {
			return talla;
		}

		public void setTalla(Talla talla) {
			this.talla = talla;
		}

		@Override
		public String toString() {
			return "Camiseta [" + id + " " + unidades + " " + precio + " " + talla + "]";
		}

	}
}
