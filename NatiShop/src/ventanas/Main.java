package ventanas;

import clases.Articulo;
import clases.Camiseta;
import clases.Genero;
import clases.Jersey;
import clases.Pantalon;
import clases.Talla;
import clases.Tienda;
import clases.Usuario;
import clases.Zapato;

public class Main {

	public static void main(String[] args) {
		for (int i=0;i<20;i++) {
		Camiseta a = new Camiseta("Cami123"+i,"Camiseta",2,2.45,Genero.HOMBRE, Talla.S,"/imagenes/camiseta.png");
		Pantalon a2 = new Pantalon("P"+i,"Pant",2,2.45,Genero.HOMBRE, Talla.S,"/imagenes/camiseta.png");
		Jersey a3 = new Jersey("h"+i,"Camiseta+i",2,2.45,Genero.HOMBRE, Talla.S,"/imagenes/camiseta.png");
		Zapato a4 = new Zapato("Zap12"+i,"Camiseta",2,2.45,Genero.HOMBRE, Talla.S,"/imagenes/camiseta.png");

		Tienda.aniadirArticulos(a);
		Tienda.aniadirArticulos(a2);
		Tienda.aniadirArticulos(a3);
		Tienda.aniadirArticulos(a4);

		}
//		Usuario newUser = new Usuario("145","Sara","12-10-2034","sara@gmail.com","hbhsa");
//		Tienda.getUsuarios().add(newUser);
		Tienda.cargarUsuarios("usuarios.csv");
		System.out.println(Tienda.getUsuarios());
//		Tienda.guardarUsuarios("usuarios.csv");
//		VentanaPrincipal vent = new VentanaPrincipal(null);
		//vent.setVisible(true);
//		System.out.println(Tienda.getCamisetas());
		
			
	}

	
}
