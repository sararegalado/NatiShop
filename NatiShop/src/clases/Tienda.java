package clases;

import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

import ventanas.BD;


public class Tienda {
	//Declaraciones
	private static Set<Articulo> articulos = new TreeSet<>();
	private static Set<Camiseta> camisetas = new TreeSet<>();
	private static Set<Jersey> jerseys = new TreeSet<>();
	private static Set<Pantalon> pantalones = new TreeSet<>();
	private static Set<Zapato> zapatos = new TreeSet<>();
	private static List<Cliente> clientes = new ArrayList<>();
	
	private static HashMap<String, HashMap<String, ArrayList<Articulo>>> comprasPorCliente = new HashMap<>();
	private static HashMap<String, Administrador>administradores = new HashMap<>();
	private static HashMap<Cliente, ArrayList<Articulo>> cestaPorCliente = new HashMap<>();
	//mapa admin (clave: correo, valor admin)
	
	
	
	public static List<Cliente> getClientes() {
		Connection con = BD.initBD("NatiShop.db");
		clientes = BD.obtenerListaClientes(con);
		BD.closeBD(con);
		return clientes;
	}
	

	public static HashMap<Cliente, ArrayList<Articulo>> getCestaPorCliente() {
		return cestaPorCliente;
	}
	
	


	


	public static void setCestaPorCliente(HashMap<Cliente, ArrayList<Articulo>> cestaPorCliente) {
		Tienda.cestaPorCliente = cestaPorCliente;
	}


	public static HashMap<String, Administrador> getAdministradores() {
		Connection con = BD.initBD("NatiShop.db");
		administradores =BD.volcarTablaAdminAMapa(con);
		BD.closeBD(con);
		return administradores;
	}


//	public static HashMap<Cliente, ArrayList<Articulo>> getCompras() {
//		return compras;
//	}
	
	public static Set<Articulo> getArticulos() {
		Connection con = BD.initBD("NatiShop.db");
		articulos = BD.obtenerListaArticulos(con);
		BD.closeBD(con);
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
	 * @param c Cliente que realiza las compras en NatyShop
	 * @param a Articulo comprado por el cliente que va a ser añadido a la lista de articulos 
	 */
	//METODO QUE HAY QUE AÑADIR AL ACTION LISTENER DEL BOTON COMPRAR
	public static void aniadirCompraCliente(Cliente c, Articulo a) {
		
		
		if(! cestaPorCliente.containsKey(c)) {
			cestaPorCliente.put(c, new ArrayList<>());
		}
		cestaPorCliente.get(c).add(a);
		
		if(!comprasPorCliente.containsKey(c.getDni())) {
			comprasPorCliente.put(c.getDni(), new HashMap<>());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fActual = new Date(0); //Cuanda  haya compras hay que cpger la fecha de la compra
		String fechaCompra = sdf.format(fActual);
		if(!comprasPorCliente.get(c.getDni()).containsKey(fechaCompra)) {
			comprasPorCliente.get(c.getDni()).put(fechaCompra, new ArrayList<>());
		}
		comprasPorCliente.get(c.getDni()).get(fechaCompra).add(a);
		
	}
	
	/**
	 * Método que carga el mapa Compras por cliente  desde la Base de datos, cuya clave es el dni del cliente y el valor es otro mapa
	 * con clave la fecha de compra y valor la lista de compras hecha por el cliente
	 */

	public static void cargarKeyMapaClientes() {
		Connection con = BD.initBD("NatiShop.db");
		List<Cliente> clientes = BD.obtenerListaClientes(con);
		System.out.println(clientes);
		BD.closeBD(null);
		for (Cliente c : clientes) {
			comprasPorCliente.putIfAbsent(c.getDni(), new HashMap<>());
		}
		
	}
	
	//RECURSIVIDAD
	public static void combinaciones(List<List<Articulo>> resultado, List<Articulo> elementos, double cantidad, double sobra, List<Articulo> temp) {
		if(cantidad < sobra) {
			Comparator<Articulo> c = new Comparator<Articulo>() {

				@Override
				public int compare(Articulo o1, Articulo o2) {
					return (int) (o1.getPrecio() - o2.getPrecio());
				}
			};
			if(!resultado.contains(temp))
				resultado.add(new ArrayList<>(temp));
			
		}else {
			for(Articulo a: elementos) {
				temp.add(a);
				combinaciones(resultado, elementos, cantidad-a.getPrecio(), sobra, temp);
				temp.remove(temp.size()-1);
			}
		}
	}
	public static List<List<Articulo>> combinaciones(List<Articulo> elementos, double cantidad, double sobra) {
			List<List<Articulo>> resultado = new ArrayList<>();
			combinaciones(resultado, elementos, cantidad, sobra, new ArrayList<>());
			return resultado;
	}

//	public static void main(String[] args) {
//		List<Articulo> lista = Arrays.asList(new Articulo("1", 6),new Articulo("1", 2),new Articulo("1", 4));
//		List<List<Articulo>> resultado = combinaciones(lista,10,5);
//		for(List<Articulo> l: resultado) {
//			System.out.println(l);
//		}
//	}
	
	
	
	
}
