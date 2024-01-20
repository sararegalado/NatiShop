package ventanas;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Cliente;
import clases.Provincia;

public class BDTest {

	private Connection con;
	
	private Cliente c1, c2, c3;      // Usuarios de prueba
	private Cliente[] clientes;
	private List<Cliente> lClientes;
	
	@Before
	public void setUp() throws Exception {
		/*Escribiremos todo aquel código que queremos que se ejecute antes de cada test*/
		c1 = new Cliente( "05323057Y", "Lucia", "2001-03-12", "lucia@gmail.com", "628999865", "Asturias", "lucia", "Tarjeta sin registrar", 100.9);
		c2 = new Cliente( "03958689K", "Andres", "1999-12-23", "andres@gmail.com", "610976549", "Asturias", "andres", "Tarjeta sin registrar", 12.12);
		c3 = new Cliente( "05442340B", "Laura", "1999-11-21", "laura@gmail.com", "645666764", "Asturias", "laura", "Tarjeta sin registrar", 12.12);
		clientes = new Cliente[] { c1, c2, c3};
		lClientes = Arrays.asList( clientes );
		con = BD.initBD("NatiShop.db");
	}

	@After
	public void tearDown() throws Exception {
		/*Escribiremos todo aquel código que queremos que se ejecute después de cada test*/
		BD.closeBD(con);
	}
	
	@Test
	public void testCrearTablas() {
	    assertThrows(AssertionError.class, () -> {
	        assertThrows(SQLException.class, () -> {
	            BD.crearTablas(con);
	        });
	    });
	}
	
	//TESTS PARA CLIENTES
	
	@Test
	public void testInsertarCliente() {
		for(Cliente c: lClientes) {
			BD.insertarCliente(con, c);
			Cliente cl = BD.buscarCliente(con, c.getDni());
			assertNotNull(cl);
		}
		
	}
	
	@Test
	public void testInsertarVariosClientes() {
	    Cliente c4 = new Cliente("12345678L", "Pedro", "2000-01-15", "pedro@gmail.com", "625784951", "Valencia", "pedro", "Tarjeta sin registrar", 50.5);
	    Cliente c5 = new Cliente("87654321P", "Ana", "1998-07-22", "ana@gmail.com", "699887766", "Barcelona", "ana", "Tarjeta sin registrar", 75.3);
	    BD.insertarCliente(con, c4);
	    BD.insertarCliente(con, c5);
	    assertNotNull(BD.buscarCliente(con, c4.getDni()));
	    assertNotNull(BD.buscarCliente(con, c5.getDni()));
	}
	
	@Test(expected = SQLException.class)
	public void testInsertarClienteDuplicado() {
	    BD.insertarCliente(con, c1); 
	}
	
	@Test
	public void testBorrarClienteInexistente() {
	    String dniInexistente = "00000000X";
	    BD.borrarCliente(con, dniInexistente);
	    Cliente c = BD.buscarCliente(con, dniInexistente);
	    assertNull(c);
	}
	
	
	@Test
	public void testActalizacionTrasBorrarCliente() {
	    int totalInicial = BD.contarClientes(con);
	    BD.borrarCliente(con, c3.getDni());
	    int totalFinal = BD.contarClientes(con);
	    assertEquals(totalInicial - 1, totalFinal);
	}
	
	@Test
	public void testBuscarCliente() {
		for(Cliente c: BD.obtenerListaClientes(con)) {
			Cliente cl = BD.buscarCliente(con, c.getDni());
			assertNotNull(cl);
		}
		Cliente cli = BD.buscarCliente(con, "15675432S");
		assertNull(cli);
		
	}
	
	@Test
	public void testBuscarClientePorNomCon() {
		for(Cliente c: BD.obtenerListaClientes(con)) {
			Cliente cl = BD.buscarClientePorNomCon(con, c.getNombre(), c.getContrasenia());
			assertNotNull(cl);
		}
		Cliente cl = BD.buscarClientePorNomCon(con, "Luis", "hola");
		assertNull(cl);
	}
	
	@Test
	public void testModificarEmailCliente() {
		BD.modificarEmailCliente(con, c1.getDni(), "luci22@gmail.com");
		Cliente c = BD.buscarCliente(con, c1.getDni());
		assertNotNull(c);
		assertEquals("luci22@gmail.com", c.getCorreo());
	}
	@Test
	public void testModificarTlfCliente() {
		BD.modificarTlfCliente(con, c1.getDni(), "678787893");
		Cliente c = BD.buscarCliente(con, c1.getDni());
		assertNotNull(c);
		assertEquals("678787893", c.getTlf());
	}
	
	@Test
	public void testModificarContraCliente() {
		BD.modificarContraCliente(con, c1.getDni(), "andreaLopez");
		Cliente c = BD.buscarCliente(con, c1.getDni());
		assertNotNull(c);
		assertEquals("andreaLopez", c.getContrasenia());
	}

	@Test
	public void testModificarNumTarj() {
		BD.modificarNumTarj(con, c1.getDni(), "1456327984123457");
		Cliente c = BD.buscarCliente(con, c1.getDni());
		assertNotNull(c);
		assertEquals("1456327984123457", c.getNumTarjeta());
	}
	
	@Test
	public void testModificarSaldo() {
	    BD.modificarSaldo(con, c1.getDni(), 9.8);
	    Cliente c = BD.buscarCliente(con, c1.getDni());
	    assertNotNull(c);
	    assertEquals(9.8, c.getSaldo(), 0.01); // Usar una tolerancia delta de 0.01
	}

	
	@Test
	public void testBorrarCliente() {
		Cliente c4 = new Cliente( "05442340B", "Sofia", "1999-11-21", "laura@gmail.com", "645666764", "Asturias", "laura", "Tarjeta sin registrar", 12.12);
		BD.insertarCliente(con, c4);
		BD.borrarCliente(con, c4.getDni());
		Cliente c = BD.buscarCliente(con, c4.getDni());
		assertNull(c);
		
	}
	
	
	@Test 
	public void testContarClientes() {
		assertEquals(BD.contarClientes(con), BD.obtenerListaClientes(con).size());
		
	}
	
	@Test
	public void obtenerListaClientes() {
		assertTrue(BD.obtenerListaClientes(con).size() == BD.contarClientes(con));
	}
	


	
}
