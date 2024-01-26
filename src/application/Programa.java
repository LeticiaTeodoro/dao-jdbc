package application;

import java.util.Date;

import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Departamento depart = new Departamento(1, "Livros");
		
		Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, depart);
		
		System.out.println(depart);
		System.out.println(vendedor);
	}
}