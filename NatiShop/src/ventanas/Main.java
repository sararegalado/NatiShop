package ventanas;

import clases.Articulo;
import clases.Talla;
import clases.Tienda;

public class Main {

	public static void main(String[] args) {
		Articulo a1 = new Articulo("123","Camiseta",2,2.45,Talla.S,"/imagenes/camiseta.png");
		Articulo a2 = new Articulo("123","Camiseta",2,2.45,Talla.S,"/imagenes/camiseta.png");
		Tienda.aniadirArticulos(a2);
		Tienda.aniadirArticulos(a1);
		VentanaPrincipal vent = new VentanaPrincipal(null);
		//vent.setVisible(true);
		
			
	}

	
}
