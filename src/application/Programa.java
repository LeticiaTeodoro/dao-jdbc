package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		VendedorDAO sellerDao = DaoFactory.createSellerDAO();
		
		System.out.println("=== TESTE 1: Vendedor findById ===");
		
		Vendedor seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		
		
		System.out.println("\n=== TESTE 2: Vendedor findByDepartment ===");
		
		Departamento depart = new Departamento(2, null);
		
		List<Vendedor> list = sellerDao.findByDepartment(depart);
		
		for(Vendedor vend : list) {
			
			System.out.println(vend);
		}
		
		
		
		System.out.println("\n=== TESTE 3: Vendedor findAll ===");
		
		list = sellerDao.findAll();
		
		for(Vendedor vend : list) {
			
			System.out.println(vend);
		}
		
		
		
		System.out.println("\n=== TESTE 4: Vendedor insert ===");		
		Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 40000.0, depart);		
		sellerDao.insert(novoVendedor);
		
		System.out.println("Inserido! Novo id = " + novoVendedor.getId());
		
		
		
		System.out.println("\n=== TESTE 5: Vendedor update ===");
		seller = sellerDao.findById(1);
		seller.setName("Martha Waine");
		sellerDao.update(seller);
		
		System.out.println("Update completo!");		
		
		
		
		System.out.println("\n=== TESTE 5: Vendedor delete ===");
		System.out.print("Entre com o id do funcionário a ser removido: ");
		int id = sc.nextInt();
		
		sellerDao.deleteById(id);
		System.out.println("Deleção completa.");
		
		sc.close();		
	}
}