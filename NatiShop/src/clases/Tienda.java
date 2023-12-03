package clases;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Tienda {
	//Declaraciones
	private static Set<Articulo> articulos = new TreeSet<>();
	private static Set<Camiseta> camisetas = new TreeSet<>();
	private static Set<Jersey> jerseys = new TreeSet<>();
	private static Set<Pantalon> pantalones = new TreeSet<>();
	private static Set<Zapato> zapatos = new TreeSet<>();
	
	private static List<Cliente> clientes = new ArrayList<>();
	private static HashMap<Cliente, ArrayList<Articulo>> compras = new HashMap<>();
	private static HashMap<String, HashMap<String, ArrayList<Articulo>>> comprasPorCliente = new HashMap<>();
	private static HashMap<String, Administrador>Administradores = new HashMap<>();
	//mapa admin (clave: correo, valor admin)
	
	private static final String nomfichClientes = "Clientes.csv";
	private  static final String nomfichAdmins= "Administradores.csv";

	public static String getNomfichclientes() {
		return nomfichClientes;
	}
	

	public static String getNomfichadmins() {
		return nomfichAdmins;
	}


	public static HashMap<Cliente, ArrayList<Articulo>> getCompras() {
		return compras;
	}
	
	public static Set<Articulo> getArticulos() {
		return articulos;
	}

	
	//
	public static TreeSet<Talla> tallasPorArticulo(Articulo articulo) {
	    TreeSet<Talla> tallas = new TreeSet<>();
	    
	    Talla tallasArticulo = articulo.getTalla();
	    
	    if (tallasArticulo != null) {
	        tallas.add(tallasArticulo);
	    }
	    
	    return tallas;
	}

	//
	

	public static HashMap<String, Administrador> getAdministradores() {
		return Administradores;
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
	
	
	
	public static List<Cliente> getClientes() {
		return clientes;
	}

	public static void setUsuarios(List<Cliente> clientes) {
		Tienda.clientes = clientes;
	}

	//Métodos
	public static void aniadirArticulos(Articulo a) {
		articulos.add(a);
	}
	
	public static void aniadirCliente(Cliente c) {
		clientes.add(c);
	}
	
	

	public static HashMap<String, HashMap<String, ArrayList<Articulo>>> getComprasPorCliente() {
		return comprasPorCliente;
	}

	/**
	 * Método que añade un nuevo articulo comprado a la lista de articulos del cliente 
	 * @param u Usuario que realiza las compras en NatyShop
	 * @param a Articulo comprado por el usuario que va a ser añadido a la lista de articulos 
	 */
	//METODO QUE HAY QUE AÑADIR AL ACTION LISTENER DEL BOTON COMPRAR
	public static void aniadirCompraCliente(Cliente c, Articulo a) {
		if(! compras.containsKey(c)) {
			compras.put(c, new ArrayList<>());
		}
		compras.get(c).add(a);
		
		if(!comprasPorCliente.containsKey(c)) {
			comprasPorCliente.put(c.getDni(), new HashMap<>());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fActual = new Date();
		String fechaCompra = sdf.format(fActual);
		if(!comprasPorCliente.get(c.getDni()).containsKey(fechaCompra)) {
			comprasPorCliente.get(c.getDni()).put(fechaCompra, new ArrayList<>());
		}
		comprasPorCliente.get(c.getDni()).get(fechaCompra).add(a);
		
	}
	/**
	 * Metodo que cargar el fichero de Admins en un mapa de Administradore
	 * @param nomfichAdmins
	 */
	
	public static void cargarAdministradores(String nomfichAdmins) {
		Scanner sc;
		try {
			sc = new Scanner(new FileReader(nomfichAdmins));
			String linea;
			sc.nextLine();
			while(sc.hasNext()){
				linea= sc.nextLine();
				String[] partes = linea.split(";");
				if(partes.length > 0) {
					String dni = partes[0];
					String nom= partes[1];
					String apellido= partes[2];
					String correo = partes[3];
					String tlf = partes[4];
					String provincia = (partes[5]);
					String Fnac = partes[6];
					String Finic = partes[7];
					String jornada=partes[8];
					String puesto = partes[9];
					String con= partes[10];
					Administrador a = new Administrador(dni,nom,apellido,correo,tlf,provincia,Fnac,Finic,jornada,puesto,con);
					if(!Administradores.containsKey(a.getCorreo())) {
						Administradores.put(a.getCorreo(), a);
					}
					
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
			
		
		
	}


	/**
	 * Método que carga el fichero usuarios en una lista de usuarios
	 * 
	 * @param nomfich Fichero que tiene todos los usuarios registrados 
	 */
	public static void cargarClientes(String nomfichClientes) {

		try {
			Scanner sc= new Scanner(new FileReader(nomfichClientes));
			String linea;
			while(sc.hasNext()) {
				linea = sc.nextLine();
				String [] partes = linea.split(";");
				if(partes.length > 0) {
					String dni = partes[0];
					String nom = partes[1];
					String fNac = partes[2];
					String correo = partes[3];
					String tfn = partes [4];
					String p = partes[5];
					String con = partes[6];
					String numT = partes[7];;
					Cliente c = new Cliente(dni, nom, fNac, correo, tfn, p, con, numT);
					if(buscarCliente(dni) == null) {
						clientes.add(c);
						comprasPorCliente.putIfAbsent(c.getDni(), new HashMap<>());
					}
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
	public static void guardarClientes(String nomfichClientes) {
		try {
			PrintWriter pw = new PrintWriter(nomfichClientes);
			for(Cliente c : clientes) {
				pw.println(c.getDni()+";"+c.getNombre()+";"+c.getfNacStr()+";"+c.getCorreo()+";"+c.getTlf()+";"+c.getProvinciaStr()+";"+c.getContrasenia()+";"+c.getNumTarjeta());
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
	
	public static Cliente buscarCliente(String dni) {
		boolean enc = false;
		int pos = 0;
		Cliente c = null;
		while(!enc && pos<clientes.size()) {
			c = clientes.get(pos);
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
	
	public static Cliente buscarClientePorNomCon(String nombre, String con) {
		boolean enc = false;
		int pos = 0;
		Cliente c = null;
		while(!enc && pos<clientes.size()) {
			c = clientes.get(pos);
			if (c.getNombre().equals(nombre) && c.getContrasenia().equals(con)) {
				enc = true;
			}else {
				pos++;
			}	
		}
		if(enc) {
			return c;
		}else {
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
