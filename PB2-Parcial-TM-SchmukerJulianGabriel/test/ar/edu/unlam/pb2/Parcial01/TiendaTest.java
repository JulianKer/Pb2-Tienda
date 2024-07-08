package ar.edu.unlam.pb2.Parcial01;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TiendaTest {

	/**
	 * Resolver los siguientes tests
	 */

	@Test (expected = VendedorDeLicenciaException.class)
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException() throws VendedorDeLicenciaException {
		String cuitDeLaTienda = "11111";
		String nombreDeLaTienda = "LaTiendita";
		Tienda tienda = new Tienda(cuitDeLaTienda, nombreDeLaTienda);
		
		Integer codigo = 1313;
		String nombreDelProducto = "producto1";
		Double precio = 215.0;
		Integer puntosDeReposicion = 5;
		Producto producto1 = new Producto(codigo, nombreDelProducto, precio, puntosDeReposicion);
		tienda.agregarProducto(producto1);		
		
		String cuitDelCliente = "46091742";
		String razonSocial = "Julian";
		Cliente cliente = new Cliente(cuitDelCliente, razonSocial);
		tienda.agregarCliente(cliente);
		
		String dniVendedor = "44444";
		String nombreVendedor = "Horacio";
		Vendedor vendedor = new Vendedor(dniVendedor, nombreVendedor);
		// le cambio la licencia
		vendedor.setDeLicencia(true);	
		tienda.agregarVendedor(vendedor);
		
		Venta nuevaVenta = new Venta("1", cliente, vendedor);
		
		tienda.agregarVenta(nuevaVenta);
		
	}

	@Test (expected = VendibleInexistenteException.class)
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException() throws VendibleInexistenteException {
		
		String cuitDeLaTienda = "11111";
		String nombreDeLaTienda = "LaTiendita";
		Tienda tienda = new Tienda(cuitDeLaTienda, nombreDeLaTienda);
		
		// creo el producto pero no lo agrego
		Integer codigo = 1313;
		String nombreDelProducto = "producto1";
		Double precio = 215.0;
		Integer puntosDeReposicion = 5;
		Producto producto1 = new Producto(codigo, nombreDelProducto, precio, puntosDeReposicion);
		
		
		String cuitDelCliente = "46091742";
		String razonSocial = "Julian";
		Cliente cliente = new Cliente(cuitDelCliente, razonSocial);
		tienda.agregarCliente(cliente);
		
		String dniVendedor = "44444";
		String nombreVendedor = "Horacio";
		Vendedor vendedor = new Vendedor(dniVendedor, nombreVendedor);
		tienda.agregarVendedor(vendedor);
		
		Venta nuevaVenta = new Venta("1", cliente, vendedor);
		
		try {
			tienda.agregarVenta(nuevaVenta);
			tienda.agregarProductoAVenta("1", producto1);
			
		} catch (VendedorDeLicenciaException e) {
			e.getMessage();
		}
	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
	}

	@Test
	public void queSePuedaObtenerUnSetDeClientesOrdenadosPorRazonSocialDescendente() {
		String cuitDeLaTienda = "11111";
		String nombreDeLaTienda = "LaTiendita";
		Tienda tienda = new Tienda(cuitDeLaTienda, nombreDeLaTienda);
		
		String cuitDelCliente1 = "46091742";
		String razonSocial1 = "Julian";
		Cliente cliente1 = new Cliente(cuitDelCliente1, razonSocial1);
		tienda.agregarCliente(cliente1);
		
		String cuitDelCliente2 = "4646552";
		String razonSocial2 = "Andy";
		Cliente cliente2 = new Cliente(cuitDelCliente2, razonSocial2);
		tienda.agregarCliente(cliente2);
		
		String cuitDelCliente3 = "46091742";
		String razonSocial3 = "German";
		Cliente cliente3 = new Cliente(cuitDelCliente3, razonSocial3);
		tienda.agregarCliente(cliente3);
		
		List<Cliente> listaObtenida= tienda.obtenerClientesOrdenadosPorRazonSocialDescendente();
		
		List<Cliente> listaEsperada = new ArrayList<>();
		listaEsperada.add(cliente1);
		listaEsperada.add(cliente3);
		listaEsperada.add(cliente2);
		
		assertEquals(listaObtenida, listaEsperada);
	}

	@Test
	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() {
		// TODO: usar como key el vendedor y Set<Venta> para las ventas
		
		// HICE los metodos necesarios para hacer este test pero no me dio tiempo a completarlo
	}

	@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios() {
		String cuitDeLaTienda = "11111";
		String nombreDeLaTienda = "LaTiendita";
		Tienda tienda = new Tienda(cuitDeLaTienda, nombreDeLaTienda);
		
		Integer codigo1 = 3;
		String nombre1 = "serivcio pc";
		Double precio = 2000.0;
		String fechaDeInicio = "10/1";
		String fechaDeFinalizacion = "15/1";
		Servicio servicio1 = new Servicio(codigo1, nombre1, precio, fechaDeInicio, fechaDeFinalizacion);
		tienda.agregarServicio(servicio1);
		
		Integer codigo2 = 3;
		String nombre2 = "serivcio heladera";
		Double precio2 = 2000.0;
		String fechaDeInicio2 = "10/1";
		String fechaDeFinalizacion2 = "15/1";
		Servicio servicio2 = new Servicio(codigo1, nombre1, precio, fechaDeInicio2, fechaDeFinalizacion2);
		tienda.agregarServicio(servicio2);
		
		Integer codigo3 = 3;
		String nombre3 = "serivcio tv";
		Double precio3 = 2000.0;
		String fechaDeInicio3 = "10/1";
		String fechaDeFinalizacion3 = "15/1";
		Servicio servicio3 = new Servicio(codigo1, nombre1, precio, fechaDeInicio, fechaDeFinalizacion);
		tienda.agregarServicio(servicio3);
		
		
		String cuitDelCliente = "46091742";
		String razonSocial = "Julian";
		Cliente cliente = new Cliente(cuitDelCliente, razonSocial);
		tienda.agregarCliente(cliente);
		
		String dniVendedor = "44444";
		String nombreVendedor = "Horacio";
		Vendedor vendedor = new Vendedor(dniVendedor, nombreVendedor);
		tienda.agregarVendedor(vendedor);
		
		Venta nuevaVenta1 = new Venta("1", cliente, vendedor);
		Venta nuevaVenta2 = new Venta("2", cliente, vendedor);
		Venta nuevaVenta3 = new Venta("3", cliente, vendedor);
		
		
		try {
			tienda.agregarVenta(nuevaVenta1);
			tienda.agregarVenta(nuevaVenta2);
			tienda.agregarVenta(nuevaVenta3);
		} catch (VendedorDeLicenciaException e) {
			e.getMessage();
		}
		
		tienda.agregarServicioAVenta("1", servicio1);
		tienda.agregarServicioAVenta("2", servicio2);
		tienda.agregarServicioAVenta("3", servicio3);
		
		Double totalDeVentasDeServicios = tienda.obtenerTotalDeVentasDeServicios();
		Double totalEsperado = 6000.0;
		
		assertEquals(totalEsperado, totalDeVentasDeServicios, 0.001);
		
	}

	@Test
	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() {
		String cuitDeLaTienda = "11111";
		String nombreDeLaTienda = "LaTiendita";
		Tienda tienda = new Tienda(cuitDeLaTienda, nombreDeLaTienda);
		
		Integer codigo = 1313;
		String nombreDelProducto = "producto1";
		Double precio = 215.0;
		Integer puntosDeReposicion = 5;
		Producto producto1 = new Producto(codigo, nombreDelProducto, precio, puntosDeReposicion);
		tienda.agregarProducto(producto1);
		Integer stockAIncrementar = 20;
		tienda.agregarStock(producto1, stockAIncrementar);
		
		
		String cuitDelCliente = "46091742";
		String razonSocial = "Julian";
		Cliente cliente = new Cliente(cuitDelCliente, razonSocial);
		tienda.agregarCliente(cliente);
		
		String dniVendedor = "44444";
		String nombreVendedor = "Horacio";
		Vendedor vendedor = new Vendedor(dniVendedor, nombreVendedor);
		tienda.agregarVendedor(vendedor);
		
		Venta nuevaVenta = new Venta("1", cliente, vendedor);
		
		try {
			tienda.agregarVenta(nuevaVenta); // primero intento agregar la venta
			tienda.agregarProductoAVenta("1", producto1);
		} catch (VendedorDeLicenciaException | VendibleInexistenteException e) {
			e.getMessage();
		}
		
		
		
		Integer stockEsperado = 19;
		Integer stockObtenido = tienda.obtenerStockDeUnProductoPorCodigo(codigo);
		assertEquals(stockEsperado, stockObtenido);
		
	}
}
