package ventanas;

import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import clases.Tienda;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;

public class Main {

	public static void main(String[] args) {
		
		Connection con = BD.initBD("NatiShop.db");
		try {
			BD.crearTablas(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.volcarCSVArticulos(con, "articulos.csv");
		BD.volcarCSVAdmin(con, "Administradores.csv");
		
		

		BD.closeBD(con);
		
//		Tienda.cargarKeyMapaClientes();
//		System.out.println(Tienda.getComprasPorCliente());

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
