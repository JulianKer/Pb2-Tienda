package ar.edu.unlam.pb2.Parcial01;

import java.util.Objects;

public abstract class Item implements Vendible{

	private Integer codigo;
	private String nombre;
	private Double precio;
	
	public Item(Integer codigo, String nombre, Double precio) {
		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
	}

	// TODO: Completar con los getters y setters necesarios

	@Override
	public Integer getCodigo() {
		return this.codigo;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public Double getPrecio() {
		return this.precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Item other = (Item) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}
	
	
}
