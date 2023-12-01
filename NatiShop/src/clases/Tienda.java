package clases;

import java.io.*;
import java.util.*;

public class Tienda {
	//Declaraciones
	private static Set<Articulo> articulos = new TreeSet<>();
	private static Set<Camiseta> camisetas = new TreeSet<>();
	private static Set<Jersey> jerseys = new TreeSet<>();
	private static Set<Pantalon> pantalones = new TreeSet<>();
	private static Set<Zapato> zapatos = new TreeSet<>();



	private static List<Usuario> usuarios = new ArrayList<>();
	private static HashMap<Usuario, ArrayList<Articulo>> compras = new HashMap<>();
	
	//private static final String nomfichUsuarios = "Usuarios.csv";

	//Getters y setters
	public static HashMap<Usuario, ArrayList<Articulo>> getCompras() {
		return compras;
	}
	
	public static Set<Articulo> getArticulos() {
		return articulos;
	}
	
	
	/**
	 * Método que guarda todas las camisetas de articulos en un HashSet
	 */
	public static Set<Camiseta> getCamisetas(){
		for (Articulo a: getArticulos()) {
			if(a instanceof Camiseta) {
				camisetas.add((Camiseta) a);
			}
		}
		return camisetas;
	}
	
	public static Set<Jersey> getJerseys(){
		for (Articulo a: getArticulos()) {
			if(a instanceof Jersey) {
				jerseys.add((Jersey) a);
			}
		}
		return jerseys;
	}
	
	public static Set<Pantalon> getPantalones(){
		for (Articulo a: getArticulos()) {
			if(a instanceof Pantalon) {
				pantalones.add((Pantalon) a);
			}
		}
		return pantalones;
	}
	
	public static Set<Zapato> getZapatos(){
		for (Articulo a: getArticulos()) {
			if(a instanceof Zapato) {
				zapatos.add((Zapato) a);
			}
		}
		return zapatos;
	}
	
	
	
	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<Usuario> usuarios) {
		Tienda.usuarios = usuarios;
	}

	//Métodos
	public static void aniadirArticulos(Articulo a) {
		articulos.add(a);
	}
	
	public static void aniadirUsuario(Usuario u) {
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
	 * Método que carga el fichero usuarios en una lista de usuarios
	 * 
	 * @param nomfich Fichero que tiene todos los usuarios registrados 
	 */
	public static void cargarUsuarios(String nomfichUsuarios) {

		try {
			Scanner sc= new Scanner(new FileReader(nomfichUsuarios));
			String linea;
			while(sc.hasNext()) {
				linea= sc.nextLine();
				String [] partes= linea.split(";");
				String dni= partes[0];
				String nom= partes[1];
				String fNac= partes[2];
				String correo= partes[3];
				String con= partes[4];
				Usuario u= new Usuario(dni, nom, fNac, correo, con);
				if(buscarUsuario(dni) == null) {
					usuarios.add(u);
				}
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
		}	
	}
	
	/**
	 * Método que guarda la lista de usuarios en un fichero
	 * 
	 * @param nomfich Fichero en el que vamos a guardar nuestra lista de usuarios
	 */
	public static void guardarUsuarios(String nomfichUsuarios) {
		try {
			PrintWriter pw = new PrintWriter(nomfichUsuarios);
			for(Usuario u : usuarios) {
				pw.println(u.getDni()+";"+u.getNombre()+";"+u.getfNacStr()+";"+u.getCorreo()+";"+u.getContrasenia());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que busca a un usuario por su dni
	 * @param dni DNI del usuari que queremos buscar
	 * @return
	 */
	
	public static Usuario buscarUsuario(String dni) {
		boolean enc = false;
		int pos = 0;
		Usuario c = null;
		while(!enc && pos<usuarios.size()) {
			c = usuarios.get(pos);
			if(c.getDni().equals(dni)) {
				enc = true;
			}else {
				pos++;
			}
		}
		if(enc) {
			return c;
		}else{
			return null;
		}
	}
	
	
	/**
	 * Método que carga el fichero articulos en una lista de articulos
	 * 
	 * @param nomfich Fichero que tiene todos los articulos registrados 
	 */
	public static void cargarArticulos(String nomfichArt) {

		try {
			Scanner sc= new Scanner(new FileReader(nomfichArt));
			String linea;
			while(sc.hasNext()) {
				linea= sc.nextLine();
				String [] partes= linea.split(";");
				String id= partes[0];
				String nom= partes[1];
				String unidades= partes[2];
				String precio= partes[3];
				String genero = partes[4];
				System.out.println(genero);
				String talla = partes[5];
				String foto = partes[6];
				String categoria = partes[7];
				if (Categoria.valueOf(categoria) == Categoria.CAMISETA) {
					Camiseta c = new Camiseta(id, nom, Integer.parseInt(unidades), Double.parseDouble(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto,Categoria.valueOf(categoria));
					aniadirArticulos(c);
				}
				else if (Categoria.valueOf(categoria) == Categoria.JERSEY) {
					Jersey j = new Jersey(id, nom, Integer.parseInt(unidades), Double.parseDouble(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
					aniadirArticulos(j);
				} 
				else if (Categoria.valueOf(categoria) == Categoria.PANTALON) {
					Pantalon p = new Pantalon(id, nom, Integer.parseInt(unidades), Double.parseDouble(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
					aniadirArticulos(p);
				}
				else {
					Zapato z = new Zapato(id, nom, Integer.parseInt(unidades), Double.parseDouble(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
					aniadirArticulos(z);
				}
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
		}	
	}
	
	
	/**
	 * Método que guarda la lista de articulos en un fichero
	 * 
	 * @param nomfich Fichero en el que vamos a guardar nuestra lista de articulos
	 */
	public static void guardarArticulos(String nomFichArticulos) {
		try {
			PrintWriter pw = new PrintWriter(nomFichArticulos);
			for(Articulo a : articulos) {
				pw.println(a.getId()+";"+a.getNombre()+";"+a.getUnidades()+";"+a.getPrecio()+";"+a.genero+";"+a.getTalla()+";"+a.getFoto()+";"+ a.getCategoria());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
