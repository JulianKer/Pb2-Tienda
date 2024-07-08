package ar.edu.unlam.pb2.Parcial01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Tienda {

	/**
	 * En esta ocasion deberemos resolver un producto software que nos permita
	 * administrar la venta de productos o servicios de nuestra tienda. Venderemos
	 * entonces, productos como mouse o teclados y servicios como el soporte tecnico
	 * a domicilio. Sabemos que la tienda cuenta con items Vendibles que pueden ser
	 * del tipo Producto o Servicio. Ademas, podemos registrar el stock de los
	 * productos, los clientes a quienes les vendemos algun producto o servicio, las
	 * ventas y los vendedores de la tienda. Antes de realizar alguna operacion, se
	 * debera obtener el elemento correspondiente de las colecciones. Ejemplo: Si
	 * quisiera realizar alguna operacion con un cliente, el mismo debe obtenerse de
	 * la coleccion de clientes.
	 * 
	 * Cada Venta contiene renglones los cuales representa a los productos o
	 * servicios que se incluyen en la misma. Tambien cuenta con el Cliente y
	 * Vendedor que participan en la Venta. Cuando agregamos un vendible a una
	 * venta, lo haremos con 1 unidad. En una version posterior, admitiremos
	 * cantidades variables.
	 * 
	 * Cada Item debe compararse por nombre y precio, en caso de ser necesario.
	 * Recordar que los items deben ser Vendibles.
	 * 
	 */

	private String cuit;
	private String nombre;
	private Set<Vendible> vendibles;
	private Map<Producto, Integer> stock;
	private List<Cliente> clientes;
	private Set<Venta> ventas;
	private Set<Vendedor> vendedores;

	public Tienda(String cuit, String nombre) {

		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.cuit = cuit;
		this.nombre = nombre;
		
		this.vendibles = new HashSet<>();
		this.stock = new HashMap<>();
		this.clientes = new ArrayList<>();
		this.ventas = new HashSet<>();
		this.vendedores = new HashSet<>();
	}

	// TODO: Completar con los getters y setters necesarios

	public Vendible getVendible(Integer codigo) {
		// TODO: Obtiene un producto o servicio de la coleccion de vendibles utilizando
		// el codigo. En caso de no existir devuelve null.
		
		for (Vendible vendible : this.vendibles) {
			if (vendible.getCodigo().equals(codigo)) {
				return vendible;
			}
		}
		return null;
	}

	public void agregarProducto(Producto producto) {
		this.agregarProducto(producto, 0);
	}

	public void agregarProducto(Producto producto, Integer stockInicial) {
		// TODO: Agrega un producto a la coleccion de vendibles y pone en la coleccion
		// de stocks al producto con su stock inicial
		
		if (producto != null) {
			this.stock.put(producto, stockInicial);
			this.vendibles.add(producto);
			
		}
	}

	public void agregarServicio(Servicio servicio) {
		// TODO: Agrega un servicio a la coleccion de vendibles
		
		if (servicio != null) {
			this.vendibles.add(servicio);
		}
	}

	public Integer getStock(Producto producto) {
		return stock.get(producto);
	}

	public void agregarStock(Producto producto, Integer incremento){
		// TODO: se debe agregar stock a un producto existente
		
		Producto productoBuscado = obtenerProductoPorCodigo(producto.getCodigo());
		if (productoBuscado != null) {
			
			for (Map.Entry<Producto, Integer> entry : this.stock.entrySet()) {
				Producto key = entry.getKey(); // este es mi producto
				Integer val = entry.getValue(); // este seria el stock del producto
				if (key.getCodigo().equals(producto.getCodigo())) { // si coinciden, le incremento su stock
					val += incremento;
					entry.setValue(val);					
				}
			}
		}
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarVendedor(Vendedor vendedor) {
		vendedores.add(vendedor);
	}

	public void agregarVenta(Venta venta) throws VendedorDeLicenciaException {
		// TODO: Agrega una venta a la coleccion correspondiente. En caso de que el
		// vendedor este de licencia, arroja una
		// VendedorDeLicenciaException
		
		if (venta != null && venta.getCliente() != null && venta.getVendedor() != null) { 
			Cliente cliente = buscarClientePorCuit(venta.getCliente().getCuit());
			Vendedor vendedor  = buscarVendedorPorDni(venta.getVendedor().getDni());
			
			if (cliente != null && vendedor != null) {
				
				if (!venta.getVendedor().isDeLicencia()) {
					this.ventas.add(venta);
				}else {
					throw new VendedorDeLicenciaException("Ese vendedor esta de LICENCIA");
				}	
			}
		}
		
	}

	private Vendedor buscarVendedorPorDni(String dni) {
		for (Vendedor vendedor : this.vendedores) {
			if (vendedor.getDni().equals(dni)) {
				return vendedor;
			}
		}
		return null;
	}

	private Cliente buscarClientePorCuit(String cuit) {
		
		for (Cliente cliente : this.clientes) {
			if (cliente.getCuit().equals(cuit)) {
				return cliente;
			}
		}
		return null;
	}

	public Producto obtenerProductoPorCodigo(Integer codigo) {
		// TODO: Obtiene un producto de los posibles por su codigo. En caso de no
		// encontrarlo se debera devolver null
		
		for (Map.Entry<Producto, Integer> entry : this.stock.entrySet()) {
			Producto key = entry.getKey();
			
			if (key.getCodigo().equals(codigo)) {
				return key;
			}
		}				
		return null;
	}
	
	public Integer obtenerStockDeUnProductoPorCodigo(Integer codigo) {
		Integer stockDelProductoEncontrado = 0;
		
		for (Map.Entry<Producto, Integer> entry : this.stock.entrySet()) {
			Producto key = entry.getKey(); // mi producto
			Integer val = entry.getValue(); // 
			
			if (key.getCodigo().equals(codigo)) {
				stockDelProductoEncontrado = val;
			}
		}
		return stockDelProductoEncontrado;
	}

	public void agregarProductoAVenta(String codigoVenta, Producto producto) throws VendibleInexistenteException {

		// TODO: Agrega un producto a una venta. Si el vendible no existe (utilizando su
		// codigo), se debe lanzar una VendibleInexistenteException
		// Se debe actualizar el stock en la tienda del producto que se agrega a la
		// venta
		
		Producto productoEncontrado = obtenerProductoPorCodigo(producto.getCodigo());
		Venta ventaEncontrada = buscarVentaPorCodigo(codigoVenta);
		
		
		if (productoEncontrado == null) {
			throw new VendibleInexistenteException("El vendible NO existe.");
		}else {
			if (ventaEncontrada != null) {
				Producto nuevoProducto = new Producto(productoEncontrado.getCodigo(), productoEncontrado.getNombre(), productoEncontrado.getPrecio(), productoEncontrado.getPuntoDeReposicion());
				ventaEncontrada.agregarRenglon(nuevoProducto, 1);
				decrementarStockDeUnProductoPorCodigo(producto.getCodigo());
			}
			
		}
		
		
	}

	private void decrementarStockDeUnProductoPorCodigo(Integer codigo) {
		for (Map.Entry<Producto, Integer> entry : this.stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			
			if (key.getCodigo().equals(codigo)) {
				val -= 1;
				entry.setValue(val);
			}
			
		}
	}

	private Venta buscarVentaPorCodigo(String codigoVenta) {
		for (Venta venta : this.ventas) {
			if (venta.getCodigo().equals(codigoVenta)) {
				return venta;
			}
		}
		return null;
	}

	public void agregarServicioAVenta(String codigoVenta, Servicio servicio) {
		// TODO: Agrega un servicio a la venta. Recordar que los productos y servicios
		// se traducen en renglones
		Venta ventaEncontrada = buscarVentaPorCodigo(codigoVenta);
		Servicio servicioEncontrado = buscarServicioPorCodigo(servicio.getCodigo());
		
		if (ventaEncontrada != null && servicioEncontrado != null) {
			ventaEncontrada.agregarRenglon(((Vendible)servicioEncontrado), 1);
		}
		
		
	}

	private Servicio buscarServicioPorCodigo(Integer codigo) {
		
		for (Vendible vendible : this.vendibles) {
			if (vendible instanceof Servicio) {
				Servicio servicioCasteado = (Servicio) vendible;
				if (servicioCasteado.getCodigo().equals(codigo)) {
					return servicioCasteado;
				}
			}
		}
		return null;
	}

	public List<Producto> obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		// TODO: Obtiene una lista de productos cuyo stock es menor o igual al punto de
		// reposicion. El punto de reposicion, es un valor que
		// definimos de manera estrategica para que nos indique cuando debemos reponer
		// stock para no quedarnos sin productos
		return null;
	}

	public List<Cliente> obtenerClientesOrdenadosPorRazonSocialDescendente() {
		// TODO: obtiene una lista de clientes ordenados por su razon social de manera
		// descendente
		ComparadorPersonalizado comparadorPersonalizado = new ComparadorPersonalizado();
		Set<Cliente> treeClientesOrdenados = new TreeSet<>(comparadorPersonalizado);
		
		for (Cliente cliente : this.clientes) {
			treeClientesOrdenados.add(cliente);
		}
		
		List<Cliente> listaADevolverDeClientesOrdenados = new ArrayList<>();
		for (Cliente cliente : treeClientesOrdenados) {
			listaADevolverDeClientesOrdenados.add(cliente);
		}
		return listaADevolverDeClientesOrdenados;
	}

	public Map<Vendedor, Set<Venta>> obtenerVentasPorVendedor() {
		// TODO: Obtiene un mapa que contiene las ventas realizadas por cada vendedor.
		
		Map<Vendedor, Set<Venta>> ventasOrdenadasPorVendedor = new TreeMap<>();
		Set<Venta> ventasDelVendedor = new TreeSet<>();
		
		for (Vendedor vendedor : this.vendedores) {
			for (Venta venta : this.ventas) {
				if (venta.getVendedor().getDni().equals(vendedor.getDni())) {
					ventasDelVendedor.add(venta);
				}
			}
			ventasOrdenadasPorVendedor.put(vendedor, ventasDelVendedor);
		}
		
		return ventasOrdenadasPorVendedor;
	}

	public Double obtenerTotalDeVentasDeServicios() {
		// TODO: obtiene el total acumulado de los vendibles que son servicios incluidos
		// en todas las ventas.
		// Si una venta incluye productos y servicios, solo nos interesa saber el total
		// de los servicios
		Double totalADevolver = 0.0;
		
		for (Venta venta : this.ventas) {
			
			for (Map.Entry<Vendible, Integer> entry : venta.getRenglones().entrySet()) {
				Vendible key = entry.getKey();
				Integer val = entry.getValue();
				
				if (key instanceof Servicio) {
					totalADevolver += ((Servicio)key).getPrecio();
				}
			}
		}
		
		return totalADevolver;
	}
}
