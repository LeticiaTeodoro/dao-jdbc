package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
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
	}
}