package ventanas;

import java.net.URL;
import java.nio.file.Paths;

import clases.Tienda;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;

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
		
		
//		NO LO BORREIS ES PARA LA PORTADA DEL PRINCIPIO
//        boolean found = (new NativeDiscovery()).discover();
//        if (!found)
//            System.setProperty("jna.library.path", "c:\\Archivos de programa\\videolan\\vlc-2.1.5");
//
//        // Obtén la URL del video desde el classpath
//        URL videoUrl = VentanaPortada.class.getResource("/imagenes/tienda1.mp4");
//
//        // Verifica si la URL es nula
//        if (videoUrl == null) {
//            System.err.println("Error: No se puede encontrar el archivo de video.");
//            return;
//        }
//
//        // Convierte la URL a una ruta de archivo
//        String videoPath = "";
//        try {
//            videoPath = Paths.get(videoUrl.toURI()).toFile().getAbsolutePath();
//        } catch (Exception e) {
//            e.printStackTrace(); 
//            return;
//        }
//
//        VentanaPortada miVentana = new VentanaPortada(null);
//        miVentana.lanza(videoPath);
    }
	
}
