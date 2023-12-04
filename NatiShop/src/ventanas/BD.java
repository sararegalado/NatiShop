package ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clases.Categoria;
import clases.Cliente;
import clases.Genero;
import clases.Jornada;
import clases.Provincia;
import clases.Puesto;
import clases.Talla;

public class BD {
	
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS cliente (DNI String, NOMBRE String, FECHA_DE_NACIMIENTO String, EMAIL String, TELEFONO String, PROVINCIA String, CONTRASEÑA String, NUMERO_DE_TARJETA String )";
		String sql2 = "CREATE TABLE IF NOT EXISTS administrador (DNI String, NOMBRE String, FECHA_DE_NACIMIENTO String, TELEFONO String, PROVINCIA Provincia, CONTRASEÑA String, APELLIDO String, JORNADA Jornada, PUESTO Puesto)";
		String sql3 = "CREATE TABLE IF NOT EXISTS articulo (ID String, NOMBRE Integer, UNIDADES Integer, PRECIO Double, GENERO Genero, TALLA Talla, FOTO String, CATEGORIA Categoria)";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param con  Conexión a la BBDD
	 * @param dni  Dni de la persona buscada
	 * @return     null si la persona no está en la BBDD, el objeto Persona si sí lo encuentra
	 */
	public static Cliente buscarCliente(Connection con, String dni) {
		String sql = String.format("SELECT * FROM cliente WHERE DNI = '%s'", dni);
		Cliente c = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql); //Ejecuto la consulta
			if(rs.next()) { //La select ha devuelto 1 ó más tuplas
				String d= rs.getString("DNI");
				String nom = rs.getString("NOMBRE");
				String fNac = rs.getString("FECHA_DE_NACIMIENTO");
				String email = rs.getString("EMAIL");
				String tlf= rs.getString("TELEFONO");
				String p = rs.getString("PROVINCIA");
				String contra = rs.getString("CONTRASEÑA");
				String nTarj = rs.getString("NUMERO_DE_TARJETA");
				c = new Cliente(d, nom,fNac,email, tlf, p, contra, nTarj);
				
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
				String n = rs.getString("NOMBRE");
				String fNac = rs.getString("FECHA_DE_NACIMIENTO");
				String email = rs.getString("EMAIL");
				String tlf= rs.getString("TELEFONO");
				String p = rs.getString("PROVINCIA");
				String contra = rs.getString("CONTRASEÑA");
				String nTarj = rs.getString("NUMERO_DE_TARJETA");
				c = new Cliente(d, n,fNac,email, tlf, p, contra, nTarj);
				
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static void insertarCliente(Connection con, Cliente c) {
		if(buscarCliente(con, c.getDni()) == null) {
			String sql = String.format("INSERT INTO cliente VALUES('%s','%s','%s','%s','%s','%s','%s','%s')", c.getDni(), c.getNombre(), c.getfNacStr(), c.getCorreo(), c.getTlf(), c.getProvinciaStr(), c.getContrasenia(), c.getNumTarjeta() );
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public static void borrarCliente(Connection con, String dni) {
		String sql = String.format("DELETE FROM cliente WHERE dni='%s'", dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificarNombreCliente(Connection con, String dni, String nuevoNombre) {
		String sql = String.format("UPDATE cliente SET nom='%s' WHERE dni='%s'", nuevoNombre,dni);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				Cliente c = new Cliente(d, n,fNac,email, tlf, p, contra, nTarj);
				l.add(c);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	

}
