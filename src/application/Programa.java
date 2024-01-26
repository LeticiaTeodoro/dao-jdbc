package application;

import model.entities.Departamento;

public class Programa {

	public static void main(String[] args) {
		
		Departamento depart = new Departamento(1, "Livros");
		
		System.out.println(depart);
	}
}