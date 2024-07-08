package ar.edu.unlam.pb2.Parcial01;

import java.util.Comparator;

public class ComparadorPersonalizado implements Comparator<Cliente>{

	@Override
	public int compare(Cliente c1, Cliente c2) {
		return (c1.getRazonSocial().compareTo(c2.getRazonSocial())*(-1));
	}
	
}
