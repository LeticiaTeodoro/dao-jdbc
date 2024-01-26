package model.dao;

import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
	
	public static VendedorDAO createSellerDAO() {
		return new VendedorDaoJDBC();
	}

}
