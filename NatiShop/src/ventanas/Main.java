package ventanas;

import clases.Articulo;
import clases.Talla;
import clases.Tienda;

public class Main {

	public static void main(String[] args) {
		for (int i=0;i<20;i++) {
		Articulo a = new Articulo("123"+ i,"Camiseta",2,2.45,Talla.S,"/imagenes/camiseta.png");
//		Articulo a2 = new Articulo("1234","Camiseta",2,2.45,Talla.S,"/imagenes/camiseta.png");
		Tienda.aniadirArticulos(a);
		}
		VentanaPrincipal vent = new VentanaPrincipal(null);
		//vent.setVisible(true);
		
			
	}

	
}
