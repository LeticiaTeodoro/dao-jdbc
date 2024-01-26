package application;

import model.dao.DaoFactory;
import model.dao.VendedorDAO;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDAO sellerDao = DaoFactory.createSellerDAO();
		
		Vendedor seller = sellerDao.findById(3);
		
		System.out.println(seller);
	}
}