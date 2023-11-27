package ventanas;

import clases.Tienda;

public class Main {

	public static void main(String[] args) {
//		for (int i=0;i<50;i++) {
//			Camiseta c = new Camiseta("Cami"+i, "Camiseta", 2, 3.45, Genero.HOMBRE, Talla.S ,"/imagenes/camiseta.png", Categoria.CAMISETA);
//			Tienda.aniadirArticulos(c);
//		}
//		Tienda.guardarArticulos("articulos.csv");
		Tienda.cargarArticulos("articulos.csv");
//		Tienda.cargarUsuarios("Usuarios.csv");
//		System.out.println(Tienda.getUsuarios());
		System.out.println(Tienda.getCamisetas());
//		
		
		VentanaPrincipal vent = new VentanaPrincipal(null);
		//vent.setVisible(true);
		
	}

	
}
