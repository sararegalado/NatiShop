package clases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Tienda {
	//Declaraciones
	private static Set<Articulo> articulos = new TreeSet<>();
	private static List<Usuario> usuarios = new ArrayList<>();
	private static HashMap<Usuario, ArrayList<Articulo>> compras = new HashMap<>();

	//Getters y setters
	public static HashMap<Usuario, ArrayList<Articulo>> getCompras() {
		return compras;
	}
	
	public static Set<Articulo> getArticulos() {
		return articulos;
	}
	
	//Métodos
	public static void aniadirArticulos(Articulo a) {
		articulos.add(a);
	}
	
	public static void aniadirClientes(Usuario u) {
		usuarios.add(u);
	}
	

	/**
	 * Método que añade un nuevo articulo comprado a la lista de articulos del cliente 
	 * @param u Usuario que realiza las compras en NatyShop
	 * @param a Articulo comprado por el usuario que va a ser añadido a la lista de articulos 
	 */
	
	public static void aniadirCompraUsuario(Usuario u, Articulo a) {
		if(! compras.containsKey(u)) {
			compras.put(u, new ArrayList<>());
		}
		compras.get(u).add(a);
	}
	
	/**
	 * Método que  guarad toddos los usuarios del conjunto usuarios en un fichero
	 * @param nomfich
	 */
	public static void guardarUsuarios(String nomfich) {
		try {
			PrintWriter pw= new PrintWriter(nomfich);
			for (Usuario u: usuarios) {
				pw.println(u.getDni()+";"+u.getNombre()+";"+u.getfNacStr()+";"+u.getCorreo()+";"+u.getContrasenia());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Método que carga todoslos usuarios del fichero en la lista Usuarios
	 */
	

	public static void cargarUsuarios(String nomfich) {

		try {
			Scanner sc= new Scanner(new FileReader(nomfich));
			String linea;
			while(sc.hasNext()) {
				linea= sc.nextLine();
				String [] partes= linea.split(";");
				String dni= partes[0];
				String nom= partes[1];
				String fNac= partes[2];
				String correo= partes[3];
				String con= partes[4];
				Usuario u= new Usuario(dni, nom, fNac, con, correo);
				usuarios.add(u);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
		}
		
	}
}
