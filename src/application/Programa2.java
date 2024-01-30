package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentoDAO;
import model.entities.Departamento;

public class Programa2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartamentoDAO departDAO = DaoFactory.createDepartmentDAO();
		
		
		System.out.println("=== TESTE 1: Departamento findById ===");
		
		Departamento depart = departDAO.findById(3);
		System.out.println(depart);
		
		
		System.out.println("\n=== TESTE 2: Departamento findAll ===");
		
		List<Departamento> listDepart = departDAO.findAll();
		
		for(Departamento departamento : listDepart) {
			
			System.out.println(departamento);
		}
		
		
		System.out.println("\n=== TESTE 4: Departamento insert ===");
		
		Departamento departInsert = new Departamento(null, "RH");
		departDAO.insert(departInsert);
		System.out.println("Inserido! Novo id = " + departInsert.getId());
		
		
		System.out.println("\n=== TESTE 5: Departamento update ===");
		
		depart = departDAO.findById(1);
		depart.setName("Dep 1");
		departDAO.update(depart);
		System.out.println("Update completo!");
		
		
		System.out.println("\n=== TESTE 5: Departamento delete ===");
		System.out.print("Entre com o id do departamento a ser removido: ");
		int id = sc.nextInt();
		
		departDAO.deleteById(id);
		System.out.println("Deleção completa.");
		
		
		sc.close();
	}
}