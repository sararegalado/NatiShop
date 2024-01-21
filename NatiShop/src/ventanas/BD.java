package ventanas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

import clases.Administrador;
import clases.Articulo;
import clases.Camiseta;
import clases.Categoria;
import clases.Cliente;
import clases.Compra;
import clases.Genero;
import clases.Jersey;
import clases.Jornada;
import clases.Pantalon;
import clases.Provincia;
import clases.Puesto;
import clases.Talla;
import clases.Zapato;

public class BD {
	
	protected static Logger logger = Logger.getLogger(BD.class.getName());

		
	/**
	 * Método que realiza la conexión con la base de datos
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexión a la base de datos
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			logger.warning(String.format("Error conectando con la BBDD: %s", ex.getMessage()));
		}
		
		return con;
	}
	
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException ex) {
				logger.warning(String.format("Error cerrando conexión con la BBDD: %s", ex.getMessage()));
			}
		}
	}
	
	public static void crearTablas(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS cliente (DNI String, NOMBRE String, FECHA_DE_NACIMIENTO String, EMAIL String, TELEFONO String, PROVINCIA String, CONTRASEÑA String, NUMERO_DE_TARJETA String, SALDO Double)";
		String sql2 = "CREATE TABLE IF NOT EXISTS administrador (DNI String, NOMBRE String, APELLIDO String, FECHA_DE_NACIMIENTO String, EMAIL String, TELEFONO String, PROVINCIA String, FECHA_INICIO_EMPRESA String, JORNADA String, PUESTO String, CONTRASEÑA String)";
		String sqlBorraArticulos = "DROP TABLE IF EXISTS articulo";
		String sql3 = "CREATE TABLE articulo (ID String, NOMBRE String, UNIDADES Integer, PRECIO Double, GENERO String, TALLA String, FOTO String, CATEGORIA String)";
		String sql4 = "CREATE TABLE IF NOT EXISTS compras(ID_COMPRA INTEGER PRIMARY KEY AUTOINCREMENT, CLIENTE String, FECHA String, PRECIO_COMPRA Float)";
		String sql5 = "CREATE TABLE IF NOT EXISTS articulosVendidos(ID_COMPRA Integer, ID_ARTICULO String, NOMBRE String, UNIDADES Integer, PRECIO Double, GENERO String, TALLA String, FOTO String, CATEGORIA String)";
		String sql6 = "CREATE TABLE IF NOT EXISTS solicitudesJornada(DNI_ADMIN String, NOMBRE String, APELLIDO String, CORREO String, TELEFONO String, JORNADA_ACTUAL String, JORNADA_SOLICITADA String, RAZON String)";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.executeUpdate(sql2);
			st.executeUpdate(sqlBorraArticulos);
			st.executeUpdate(sql3);
			st.executeUpdate(sql4);
			st.executeUpdate(sql5);
			st.executeUpdate(sql6);

			st.close();
		} catch (SQLException e) {
			logger.warning("Error creando las tablas");
		}
		logger.info("Tablas creadas correctamenrte");
	}
	
	//METODOS PARA LOS CLIENTES
	
	/**
	 * 
	 * @param con  Conexión a la BBDD
	 * @param dni  Dni de la persona buscada
	 * @return     null si la persona no está en la BBDD, el objeto Persona si sí lo encuentra
	 */
	public static void insertarCliente(Connection con, Cliente c) {
		if(buscarCliente(con, c.getDni()) == null) {
			String sql = String.format("INSERT INTO cliente VALUES (?,?,?,?,?,?,?,?,?)");

		    try {
		    	PreparedStatement st = con.prepareStatement(sql);
		    	st.setString(1, c.getDni());
		    	st.setString(2, c.getNombre());
		    	st.setString(3, c.getfNacStr());
		    	st.setString(4, c.getCorreo());
		    	st.setString(5, c.getTlf());
		    	st.setString(6, c.getProvinciaStr());
		    	st.setString(7, c.getContrasenia());
		    	st.setString(8, c.getNumTarjeta());
		    	st.setDouble(9, c.getSaldo());
		    	
		    	st.execute();
		        st.close();
		    } catch (SQLException e) {
		        logger.warning(String.format("Error insertando cliente %s", c.toString()));
		    }
		    logger.info(String.format("Nuevo cliente insertado: %s", c.toString()));
		}
	
	}
	
	public static Cliente buscarCliente(Connection con, String dni) {
		String sql = String.format("SELECT * FROM cliente WHERE DNI = '%s'", dni);
		Cliente c = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); //Ejecuto la consulta
			if(rs.next()) { //La select ha devuelto 1 ó más tuplas
				String nom = rs.getString("NOMBRE");
				String fNac = rs.getString("FECHA_DE_NACIMIENTO");
				String email = rs.getString("EMAIL");
				String tlf= rs.getString("TELEFONO");
				String p = rs.getString("PROVINCIA");
				String contra = rs.getString("CONTRASEÑA");
				String nTarj = rs.getString("NUMERO_DE_TARJETA");
				double saldo = rs.getDouble("SALDO");
				c = new Cliente(dni, nom, fNac, email, tlf, p, contra, nTarj, saldo);
				
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.info("Error buscando cliente" + e.getMessage());
		}
		return c;
	}
	
	public static Cliente buscarClientePorNomCon (Connection con, String nom, String contr) {
		String sql = String.format("SELECT * FROM cliente WHERE NOMBRE = '%s' AND CONTRASEÑA = '%s'", nom, contr);
		Cliente c = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); //Ejecuto la consulta
			if(rs.next()) { //La select ha devuelto 1 ó más tuplas
				String d= rs.getString("DNI");
				String fNac = rs.getString("FECHA_DE_NACIMIENTO");
				String email = rs.getString("EMAIL");
				String tlf= rs.getString("TELEFONO");
				String p = rs.getString("PROVINCIA");
				String nTarj = rs.getString("NUMERO_DE_TARJETA");
				double saldo = rs.getDouble("SALDO");
				c = new Cliente(d, nom, fNac, email, tlf, p, contr, nTarj, saldo);
				
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.warning("Error buscando cliente" + e.getMessage());
		}
		return c;
	}
	
	public static void modificarEmailCliente(Connection con, String dni, String nuevoEmail) {
		String sql = String.format("UPDATE cliente SET EMAIL='%s' WHERE DNI='%s'", nuevoEmail ,dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error modificando el email del cliente %s", dni));
		}
		logger.info(String.format("Cliente modificado con exito: Nuevo email: %s", nuevoEmail));
	}
	
	public static void modificarTlfCliente(Connection con, String dni, String nuevoTlf) {
		String sql = String.format("UPDATE cliente SET TELEFONO='%s' WHERE DNI='%s'", nuevoTlf ,dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error modificando el telefono del cliente %s", dni));
		}
		logger.info(String.format("Cliente modificado con exito: Nuevo teléfono: %s", nuevoTlf));

	}
	
	public static void modificarContraCliente(Connection con, String dni, String nuevaContra) {
		String sql = String.format("UPDATE cliente SET CONTRASEÑA='%s' WHERE DNI='%s'", nuevaContra ,dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error modificando la contraseña del cliente %s", dni));
		}
		logger.info(String.format("Cliente modificado con exito: Nueva contraseña: %s", nuevaContra));

		
	}
	
	public static void modificarNumTarj(Connection con, String dni, String numT) {
		String sql = String.format("UPDATE cliente SET NUMERO_DE_TARJETA='%s' WHERE DNI='%s'", numT ,dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error modificando el número de tarjeta del cliente %s", dni));
		}
		logger.info(String.format("Cliente modificado con exito: Nuevo nº de tarjeta: %s", numT));

	}
	
	public static void modificarSaldo(Connection con, String dni, double saldo) {
	    // Formatear el saldo como una cadena usando el formato correcto
	    String saldoFormateado = String.format("%.2f", saldo).replace(",", ".");

	    // Construir la consulta SQL con el saldo formateado como cadena
	    String sql = String.format("UPDATE cliente SET SALDO=%s WHERE DNI='%s'", saldoFormateado, dni);

	    try {
	        Statement st = con.createStatement();
	        st.executeUpdate(sql);
	        st.close();
	    } catch (SQLException e) {
			logger.warning(String.format("Error modificando el saldo del cliente %s", dni));
	    }
		logger.info(String.format("Cliente modificado con exito: Nuevo saldo: %s", saldo));

	}
	
	public static int contarClientes(Connection con) {
		String sql = "SELECT COUNT(*) FROM cliente;";
		int cont=0;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			cont = rs.getInt(1);
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.warning("Error contando clientes");
		}
		return cont;
	}


	
	/*Devuelve una lista con los clientes de la tabla Clientes*/
	public static List<Cliente> obtenerListaClientes(Connection con){
		String sql = "SELECT * FROM cliente";
		List<Cliente> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String d= rs.getString("DNI");
				String n = rs.getString("NOMBRE");
				String fNac = rs.getString("FECHA_DE_NACIMIENTO");
				String email = rs.getString("EMAIL");
				String tlf= rs.getString("TELEFONO");
				String p = rs.getString("PROVINCIA");
				String contra = rs.getString("CONTRASEÑA");
				String nTarj = rs.getString("NUMERO_DE_TARJETA");
				double saldo = rs.getDouble("SALDO");
				Cliente c = new Cliente(d, n, fNac, email, tlf, p, contra, nTarj, saldo);
				l.add(c);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.warning("Error obteniendo lista de clientes");
		}
		return l;
	}
	
	
	public static void borrarCliente(Connection con, String dni) {
		String sql = String.format("DELETE FROM cliente WHERE dni='%s'", dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning("Error borrando cliente " + dni);
		}
		logger.info("Cliente borrado correctamente " + dni);

	}
	
	
	//METODOS PARA ADMINISTRADORES
	
//	String dni, String nombre, String apellido, String fNac, String correo, String tlf, String provincia,
//	String fInicEmpresa, String jornadaLaboral, String puesto, String contrasenia)
	
	public static Administrador buscarAdministrador(Connection con, String dni) {
		String sql = String.format("SELECT * FROM administrador WHERE DNI = '%s'", dni);
		Administrador a = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); //Ejecuto la consulta
			if(rs.next()) { //La select ha devuelto 1 ó más tuplas
				String d= rs.getString("DNI");
				String nom = rs.getString("NOMBRE");
				String apell = rs.getString("APELLIDO");
				String fNac = rs.getString("FECHA_DE_NACIMIENTO");
				String email = rs.getString("EMAIL");
				String tlf= rs.getString("TELEFONO");
				String p = rs.getString("PROVINCIA");
				String fIniEmp = rs.getString("FECHA_INICIO_EMPRESA");
				String jornada = rs.getString("JORNADA");
				String puesto = rs.getString("PUESTO");
				String contra = rs.getString("CONTRASEÑA");
				a = new Administrador(d, nom, apell, fNac,email, tlf, p, fIniEmp, jornada, puesto, contra);
				
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.warning("Error buscando administrador" + dni);
		}
		return a;
	}
	
	public static void insertarAdmin(Connection con, Administrador a) {
		if(buscarAdministrador(con, a.getDni()) == null) {
			String sql = String.format("INSERT INTO administrador VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", a.getDni(), a.getNombre(), a.getApellido(), a.getfNacStr(), a.getCorreo(), a.getTlf(), a.getProvinciaStr(), a.getFInicEmpresaStr(), a.getJornadaLaboralStr(), a.getPuestoStr(), a.getContrasenia());
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				logger.warning(String.format("Error insertando administrador: %s", a.toString()));
			}
			logger.info(String.format("Administrador insertado correctamente: %s", a.toString()));

		}
	
	}
	
	public static void borrarAdmin(Connection con, String dni) {
		String sql = String.format("DELETE FROM cliente WHERE DNI='%s'", dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error bottando administrador: %s", dni));
		}
		logger.info(String.format("Administrador borrado correctamente: %s",dni));

	}
	
	public static void modificarJornadaAdmin(Connection con, String dni, String nuevaJornada) {
		String sql = String.format("UPDATE administrador SET JORNADA='%s' WHERE DNI='%s'", nuevaJornada,dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error modificando la jornada laboral del administrador %s",dni));
		}
	}
	
	public static void anyadirAdminSolic(Connection con, String dni, String jornSol, String razon) {
		Administrador a = buscarAdministrador(con, dni);
		//String sql6 = "CREATE TABLE IF NOT EXISTS solicitudesJornada(DNI_ADMIN String, NOMBRE String, APELLIDO String, CORREO String, TELEFONO String, JORNADA_ACTUAL String, JORNADA_SOLICITADA String, RAZON String)";

		String sql = String.format("INSERT INTO solicitudesJornada VALUES('%s','%s','%s','%s','%s','%s','%s','%s')", a.getDni(), a.getNombre(), a.getApellido(), a.getCorreo(), a.getTlf(), a.getJornadaLaboralStr(), jornSol, razon);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error insertando administrador para la solicitud de jornada: %s", a.toString()));
		}
		logger.info(String.format("Administrador insertado correctamente para la solicitud de jornada: %s", a.toString()));

	}
	
	public static void cargarTablaAdminsSolic(Connection con, DefaultTableModel modeloTabla){
		String sql = "SELECT * FROM solicitudesJornada";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String d= rs.getString("DNI_ADMIN");
				String n = rs.getString("NOMBRE");
				String a = rs.getString("APELLIDO");
				String c = rs.getString("CORREO");
				String tlf= rs.getString("TELEFONO");
				String jA = rs.getString("JORNADA_ACTUAL");
				String jS = rs.getString("JORNADA_SOLICITADA");
				String r = rs.getString("RAZON");
				Object[] fila = {d, n, a, c, tlf, jA, jS, r};
				modeloTabla.addRow(fila);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.warning("Error cargando la tabla de solicitudes");
		}
	}
	
	public static void borrarAdminSolic(Connection con, String dni) {
		String sql = String.format("DELETE FROM solicitudesJornada WHERE DNI_ADMIN='%s'", dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			logger.warning(String.format("Error borrando administrador para cambiar la solicitud: %s", dni));
		}
		logger.info(String.format("Administrador para cambiar la solicitud borrado correctamente: %s",dni));

	}
	
	public static void volcarCSVAdmin(Connection con, String nomfich) {
		try {
			Scanner sc= new Scanner(new FileReader(nomfich));
				String linea;
				linea= sc.nextLine(); //titulos
				while(sc.hasNext()) {
					linea= sc.nextLine();
					String [] partes= linea.split(";");
					String dni= partes[0];
					String nombre= partes[1];
					String apellido= partes[2];
					String correo = partes[3];
					String tlf = partes[4];
					String provincia = partes[5];
					String fNac= partes[6];
					String fInicEmpresa = partes[7];
					String jornadaLaboral = partes[8];
					String puesto = partes[9];
					String contrasenia = partes[10];
					
					Administrador a = new Administrador(dni, nombre, apellido, fNac,  correo, tlf, provincia, fInicEmpresa, 
							jornadaLaboral, puesto, contrasenia);
					BD.insertarAdmin(con, a);
						
					}
				
		} catch (FileNotFoundException e) {
			logger.warning("Error volcando csv de administradores a la BBDD");
		}
	}
	
	

	
	public static HashMap<String, Administrador> volcarTablaAdminAMapa(Connection con){
		HashMap<String, Administrador> m = new HashMap<>();
		String sql = "SELECT * FROM administrador";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); //Apunta a la primera de las tuplas devueltas por la select
			while(rs.next()) { //Recorremos todas las tuplas devueltas por la select
				String d= rs.getString("DNI");
				String nom = rs.getString("NOMBRE");
				String apell = rs.getString("APELLIDO");
				String fNac = rs.getString("FECHA_DE_NACIMIENTO");
				String email = rs.getString("EMAIL");
				String tlf= rs.getString("TELEFONO");
				String p = rs.getString("PROVINCIA");
				String fIniEmp = rs.getString("FECHA_INICIO_EMPRESA");
				String jornada = rs.getString("JORNADA");
				String puesto = rs.getString("PUESTO");
				String contra = rs.getString("CONTRASEÑA");
				Administrador a = new Administrador(d, nom, apell, fNac,email, tlf, p, fIniEmp, jornada, puesto, contra);
				m.putIfAbsent(a.getCorreo(), a);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.warning("Error obteniendo datos de la tabla administrador");

		}
		return m;
	}
	
	//METODOS PARA LOS ARTICULOS
//	public Articulo(String id, String nombre, int unidades, double precio, Genero genero, Talla talla, String foto, Categoria categoria) {
//		super();
//		this.id = id;
//		this.nombre = nombre;
//		this.unidades = unidades;
//		this.precio = precio;
//		this.genero = genero;
//		this.talla= talla;
//		this.foto = foto;
//		this.categoria = categoria;
//	}
	//111;Camiset rayas;4;19.78;HOMBRE;S;/imagenes/camisetasHombre/camiseta1H.png;CAMISETA
	public static void insertarArticulo(Connection con, Articulo a) {
		String sql = String.format("INSERT INTO articulo VALUES (?,?,?,?,?,?,?,?)");

	    try {
	    	PreparedStatement st = con.prepareStatement(sql);
	    	st.setString(1, a.getId());
	    	st.setString(2, a.getNombre());
	    	st.setInt(3, a.getUnidades());
	    	st.setFloat(4, a.getPrecio());
	    	st.setString(5, a.getGeneroStr());
	    	st.setString(6, a.getTallaStr());
	    	st.setString(7, a.getFoto());
	    	st.setString(8, a.getCategoriaStr());
	    	
	    	
	    	st.execute();
	        st.close();
	    } catch (SQLException e) {
	    	logger.warning(String.format("Error insertando articulo %s", a.toString()));
	    }
	}

	
	public static void volcarCSVArticulos(Connection con, String nomfich) {
		try {
			Scanner sc= new Scanner(new FileReader(nomfich));
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
						Camiseta c = new Camiseta(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto,Categoria.valueOf(categoria));
						System.out.println(c);
						BD.insertarArticulo(con, c);
						
					}
					else if (Categoria.valueOf(categoria) == Categoria.JERSEY) {
						Jersey j = new Jersey(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
						BD.insertarArticulo(con, j);
					} 
					else if (Categoria.valueOf(categoria) == Categoria.PANTALON) {
						Pantalon p = new Pantalon(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
						BD.insertarArticulo(con, p);
					}
					else {
						Zapato z = new Zapato(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
						BD.insertarArticulo(con, z);
						} 

					}
					
				
				sc.close();
		} catch (FileNotFoundException e) {
			logger.warning("Error volcando csv de articulos a la BBDD");
		}
	}
	
	public static Set<Articulo> obtenerListaArticulos(Connection con){
		String sql = "SELECT * FROM articulo";
		Set<Articulo> articulos = new TreeSet<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String id= rs.getString("ID");
				String nom= rs.getString("NOMBRE");
				String unidades= rs.getString("UNIDADES");
				String precio= rs.getString("PRECIO");
				String genero = rs.getString("GENERO");
				String talla = rs.getString("TALLA");
				String foto = rs.getString("FOTO");
				String categoria = rs.getString("CATEGORIA");
				if (Categoria.valueOf(categoria) == Categoria.CAMISETA) {
					Camiseta c = new Camiseta(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto,Categoria.valueOf(categoria));
					articulos.add(c);
					
				}
				else if (Categoria.valueOf(categoria) == Categoria.JERSEY) {
					Jersey j = new Jersey(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
					articulos.add(j);
				} 
				else if (Categoria.valueOf(categoria) == Categoria.PANTALON) {
					Pantalon p = new Pantalon(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
					articulos.add(p);
				}
				else {
					Zapato z = new Zapato(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
					articulos.add(z);	 

				}
			    
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.warning("Error obteniendo lista de articulos");
		}
		return articulos;
	}
	public static Articulo buscarArticulo(Connection con, String id) {
	    String sql = String.format("SELECT * FROM articulo WHERE ID = '%s'", id);
	    
	    try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
	        if (rs.next()) {
	            String nom = rs.getString("NOMBRE");
	            String unidades = rs.getString("UNIDADES");
	            String precio = rs.getString("PRECIO");
	            String genero = rs.getString("GENERO");
	            String talla = rs.getString("TALLA");
	            String foto = rs.getString("FOTO");
	            String categoria = rs.getString("CATEGORIA");

	            // Definir un tipo genérico Articulo
	            Articulo articulo = null;

	            // Crear instancias específicas según la categoría
	            switch (Categoria.valueOf(categoria)) {
	                case CAMISETA:
	                    articulo = new Camiseta(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio), Genero.valueOf(genero), Talla.valueOf(talla), foto, Categoria.valueOf(categoria));
	                    break;
	                case JERSEY:
	                    articulo = new Jersey(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio), Genero.valueOf(genero), Talla.valueOf(talla), foto, Categoria.valueOf(categoria));
	                    break;
	                case PANTALON:
	                    articulo = new Pantalon(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio), Genero.valueOf(genero), Talla.valueOf(talla), foto, Categoria.valueOf(categoria));
	                    break;
	                case CALZADO:
	                    articulo = new Zapato(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio), Genero.valueOf(genero), Talla.valueOf(talla), foto, Categoria.valueOf(categoria));
	                    break;
	                default:
	                    // Puedes manejar otras categorías aquí según tus necesidades
	                    break;
	            }

	            return articulo;
	        }
	    } catch (SQLException e) {
	    	logger.warning(String.format("Error buscando articulo %s", id));
	    }

	    return null;
	}
	
	public boolean anyadirCompra(Connection con, Compra compra) {
	    String sql1 = "INSERT INTO compras(CLIENTE, FECHA, PRECIO_COMPRA) VALUES (?, ?, ?)";

	    try {
	        PreparedStatement ps = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, compra.getCliente().getDni());
	        ps.setString(2, compra.getFechaStr());
	        ps.setFloat(3, compra.getPrecio());

	        ps.executeUpdate();


	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int idCompra = generatedKeys.getInt(1);
	                compra.setIdCompra(idCompra);
	            }
	        }

	        // Ahora insertamos los artículos vendidos en la tabla articulosVendidos
	        String sql2 = "INSERT INTO articulosVendidos(ID_COMPRA, ID_ARTICULO, NOMBRE, UNIDADES, PRECIO, GENERO, TALLA, FOTO, CATEGORIA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement st = con.prepareStatement(sql2)) {
	            for (Articulo a : compra.getArticulos()) {
	                st.setInt(1, compra.getIdCompra());
	                st.setString(2, a.getId());
	                st.setString(3, a.getNombre());
	                st.setInt(4, a.getUnidades());
	                st.setFloat(5, a.getPrecio());
	                st.setString(6, a.getGeneroStr());
	                st.setString(7, a.getTallaStr());
	                st.setString(8, a.getFoto());
	                st.setString(9, a.getCategoriaStr());
	                st.executeUpdate();
	            }
	        }

	    } catch (SQLException ex) {
	        logger.warning(String.format("Error añadiendo compra %s", ex.getMessage()));
	        return false;
	    }
	    return true;
	}

	
	public static List<Compra> obtenerComprasTotales(Connection con) {
		List<Compra> ret = new ArrayList<>();
		String sql1 = "SELECT * FROM compras";
		String sql2 = "SELECT ID_ARTICULO, NOMBRE, UNIDADES, PRECIO, GENERO, TALLA, FOTO, CATEGORIA FROM articulosVendidos WHERE ID_COMPRA = %d";
		
		try (Statement stmt1 = con.createStatement()) {
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				Cliente cliente = buscarCliente(con, rs1.getString("CLIENTE"));
				int idCompra = rs1.getInt("ID_COMPRA");
				String fecha = rs1.getString("FECHA");
				String pre = rs1.getString("PRECIO_COMPRA");
				Compra nuevaCompra = new Compra(cliente, fecha, new ArrayList<>(), Float.parseFloat(pre));
				nuevaCompra.setIdCompra(idCompra);
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(String.format(sql2, idCompra));
				while (rs2.next()) {
					String id= rs2.getString("ID_ARTICULO");
					String nom= rs2.getString("NOMBRE");
					String unidades= rs2.getString("UNIDADES");
					String precio= rs2.getString("PRECIO");
					String genero = rs2.getString("GENERO");
					String talla = rs2.getString("TALLA");
					String foto = rs2.getString("FOTO");
					String categoria = rs2.getString("CATEGORIA");
					if (Categoria.valueOf(categoria) == Categoria.CAMISETA) {
						Camiseta c = new Camiseta(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto,Categoria.valueOf(categoria));
						nuevaCompra.getArticulos().add(c);
						
					}
					else if (Categoria.valueOf(categoria) == Categoria.JERSEY) {
						Jersey j = new Jersey(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
						nuevaCompra.getArticulos().add(j);
					} 
					else if (Categoria.valueOf(categoria) == Categoria.PANTALON) {
						Pantalon p = new Pantalon(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
						nuevaCompra.getArticulos().add(p);
					}
					else {
						Zapato z = new Zapato(id, nom, Integer.parseInt(unidades), Float.parseFloat(precio),Genero.valueOf(genero),Talla.valueOf(talla),foto, Categoria.valueOf(categoria));
						nuevaCompra.getArticulos().add(z);	 

					}
				    
				}
				
				ret.add(nuevaCompra);	
					
				}
				
				
				
			
			
			
			
		} catch (Exception ex) {
			logger.warning("Error obteniendo compras totales");
		}
		
		
		return ret;
		
	}
	
	
	public static List<Compra> getComprasPorCliente(Connection con, Cliente cliente) {
		List<Compra> comprasTotales = obtenerComprasTotales(con);
		List<Compra> ret = new ArrayList<>();
		for (Compra c : comprasTotales) {
			if (c.getCliente().equals(cliente)) {
				ret.add(c);
			}
			
		}
		
		return ret;
	}

}
