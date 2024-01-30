package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
	
	public static VendedorDAO createSellerDAO() {
		return new VendedorDaoJDBC(DB.getConnection());
	}
	
	public static DepartamentoDAO createDepartmentDAO() {
		return new DepartamentoDaoJDBC(DB.getConnection());
	}
}