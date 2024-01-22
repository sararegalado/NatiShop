package ventanas;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Administrador;
import clases.Articulo;
import clases.Categoria;
import clases.Cliente;
import clases.Compra;
import clases.Genero;
import clases.Jornada;
import clases.Provincia;
import clases.Puesto;
import clases.Talla;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class BDTest {

	private Connection con;
	
	private Cliente c1, c2, c3;      // Usuarios de prueba
	private Cliente[] clientes;
	private List<Cliente> lClientes;
    private Administrador a1, a2, a3;
    private Administrador[] administradores;
	private List<Administrador> lAdministradores;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Articulo art1;
	private Articulo[] Articulos;
	private List<Articulo> lArticulos;
	private Compra com1;
	private Compra[] compras;
	private List<Compra> lCompras;

    
	
	@Before
	public void setUp() throws Exception {
		/*Escribiremos todo aquel código que queremos que se ejecute antes de cada test*/
		c1 = new Cliente( "05323057Y", "Lucia", "2001-03-12", "lucia@gmail.com", "628999865", "Asturias", "lucia", "Tarjeta sin registrar", 100.9);
		c2 = new Cliente( "03958689K", "Andres", "1999-12-23", "andres@gmail.com", "610976549", "Asturias", "andres", "Tarjeta sin registrar", 12.12);
		c3 = new Cliente( "05442340B", "Laura", "1999-11-21", "laura@gmail.com", "645666764", "Asturias", "laura", "Tarjeta sin registrar", 12.12);
		Date fNac1 = sdf.parse("1980-01-01");
	    Date fInicEmp1 = sdf.parse("2020-01-01");
	    Date fNac2 = sdf.parse("1970-01-01");
	    Date fInicEmp2 = sdf.parse("2024-01-20");
	    Date fNac3 = sdf.parse("1970-01-11");
	    Date fInicEmp3 = sdf.parse("2024-51-20");
	    a1 = new Administrador("25728904G", "Ana", "Pérez", fNac1, "ana.perez@example.com", "123456789", Provincia.Albacete, fInicEmp1, Jornada.Jornada_Completa, Puesto.Almacen, "ana");
	    a2 = new Administrador("78095678Y", "Natalia", "Gonzalez", fNac2, "natalia.gonzalez@natyshop.es", "611224455", Provincia.Vizcaya, fInicEmp2, Jornada.Jornada_Completa, Puesto.Supervisor, "natalia");
	    a3 = new Administrador("45626990S", "Lola", "Fernandez", fNac3, "lola.fernandez@natyshop.es", "144114566", Provincia.Vizcaya, fInicEmp2, Jornada.Media_Jornada, Puesto.Supervisor, "lola");
	    com1 = new Compra(c1, "2024-01-21", new ArrayList<>(Arrays.asList(art1)), 59.99f);
	    clientes = new Cliente[] { c1, c2, c3};
		lClientes = Arrays.asList( clientes );
		con = BD.initBD("NatiShop.db");
		art1 = new Articulo("33333", "Zapatillas", 15, (float)59.99f, Genero.MUJER, Talla.M, "zapatillas.png", Categoria.CALZADO);
	    //111;Camiset rayas;4;19.78;HOMBRE;S;/imagenes/camisetasHombre/camiseta1H.png;CAMISETA
	    Articulos = new Articulo[] { art1};
	    lArticulos = Arrays.asList( Articulos );
		administradores = new Administrador[] { a1, a2, a3};
		lAdministradores = Arrays.asList( administradores );
		compras = new Compra[] {com1};
		lCompras = Arrays.asList(compras);
		
		
	}

	@After
	public void tearDown() throws Exception {
		
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
	
	//TEST PARA COMPRAS
	
	@Test
	public void testAnyadirCompra() {
	    Compra nuevaCompra = new Compra(c2, "2024-01-21", new ArrayList<>(Arrays.asList(art1)), 59.99f);
	    boolean resultado = BD.anyadirCompra(con, nuevaCompra);
	    assertTrue("La compra no fue añadida correctamente", resultado);
	}
	
	@Test
	public void testGetComprasPorCliente() {
	    List<Compra> comprasDelCliente = BD.getComprasPorCliente(con, c1);
	    for (Compra compra : comprasDelCliente) {
	        assertEquals("El DNI del cliente no coincide", c2.getDni(), compra.getCliente().getDni());
	    }
	    int numeroEsperadoDeCompras = 0;
	    assertEquals("Número de compras recuperadas no coincide", numeroEsperadoDeCompras, comprasDelCliente.size());
	}
	
	/*
	@Test
	public void testObtenerComprasTotales() {
		List<Compra> comprasTotales = BD.obtenerComprasTotales(con);
	    assertNotNull("La lista de compras no debe ser nula", comprasTotales);
	    System.out.println("Compras recuperadas: " + comprasTotales.size());
	    for (Compra compra : comprasTotales) {
	        System.out.println("Compra ID: " + compra.getIdCompra() + ", Cliente: " + compra.getCliente().getDni());
	    }
	    int numeroEsperadoDeCompras = 2; 
	    assertEquals("El número de compras recuperadas no coincide", numeroEsperadoDeCompras, comprasTotales.size());
	    if (!comprasTotales.isEmpty()) {
	        Compra primeraCompra = comprasTotales.get(0);
	        
	   
	    }
	}*/
 
	
	//TEST PARA ARTICULOS

	@Test
	public void testInsertarArticulo() {
	    BD.insertarArticulo(con, art1);
	    Articulo articulo = BD.buscarArticulo(con, art1.getId());
	    assertNotNull(articulo);
	   
	}
	
	
	@Test
	public void testActualizarPrecioArticulo() {
	    BD.insertarArticulo(con, art1);
	    BD.actualizarPrecioArticulo(con, art1.getId(), 59.99f);
	    Articulo articuloActualizado = BD.buscarArticulo(con, art1.getId());
	    assertEquals(59.99f, articuloActualizado.getPrecio(), 0.01);
	}
	
	
	@Test
	public void testBuscarArticulosPorCategoria() {
	    BD.insertarArticulo(con, art1);
	    List<Articulo> articulosEncontrados = BD.buscarArticulosPorCategoria(con, Categoria.CALZADO);
	    for (Articulo articulo : articulosEncontrados) {
	        assertEquals("La categoría del artículo no coincide", Categoria.CALZADO, articulo.getCategoria());
	    }
	}

	@Test
	public void testObtenerTodosLosArticulos() {
	    Set<Articulo> todosLosArticulos = BD.obtenerListaArticulos(con);
	    assertNotNull(todosLosArticulos);
	    assertFalse(todosLosArticulos.isEmpty());
	}
	
	
	
	
	//TEST PARA ADMINISTRADORES
	
	@Test
	public void testBuscarAdministrador() {
	    Administrador admin = BD.buscarAdministrador(con, a1.getDni());
	    assertNotNull(admin);
	}
	
	
	
	@Test
	public void testInsertarAdministrador() {
	    BD.insertarAdministrador(con, a1);
	    Administrador admin = BD.buscarAdministrador(con, a1.getDni());
	    assertNotNull(admin);
	   
	}
	@Test
	public void testInsertarAdministrador1() {
	    BD.insertarAdministrador(con, a3);
	    Administrador admin = BD.buscarAdministrador(con, a3.getDni());
	    assertNotNull(admin);
	   
	}
	
	
	
	@Test
	public void testAsignacionPuestoAdministrador() {
	    BD.insertarAdministrador(con, a1);
	    Administrador admin = BD.buscarAdministrador(con, a1.getDni());
	    assertEquals(Puesto.Almacen, admin.getPuesto());
	}
	
	@Test
    public void testBorrarAdminExistente() {
        String dni = "23547636W";
        Administrador admin = BD.buscarAdministrador(con, dni);
        BD.borrarAdmin(con, dni);
        assertNull("El administrador no ha sido borrado correctamente", BD.buscarAdministrador(con, dni));
    }

	
	@Test
	public void testModificarJornadaAdministrador() {
	    BD.modificarJornadaAdmin(con, a3.getDni(), Jornada.Media_Jornada);
	    Administrador admin = BD.buscarAdministrador(con, a3.getDni());
	    assertEquals(Jornada.Media_Jornada, admin.getJornadaLaboral());
	}
	/*
	@Test
	public void testEliminarYVerificarListaAdministrador() {
	    BD.insertarAdministrador(con, a2);
	    BD.borrarAdmin(con, a2.getDni());
	    List<Administrador> admins = BD.obtenerlistarAdministradores(con);
	    assertFalse(admins.contains(a2));
	}*/
	
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
	
	
	//TEST ARTICULOS VENDIDOS
	@Test
    public void testConsultarArticulosVendidos() {
        try {
            List<Map<String, Object>> articulosVendidos = BD.consultarArticulosVendidos(con);
            assertNotNull("La lista de artículos vendidos no debe ser nula", articulosVendidos);

        } catch (Exception e) {
            fail("No debería lanzar una excepción: " + e.getMessage());
        }
    }
	

	
}
