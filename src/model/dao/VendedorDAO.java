package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Vendedor;

public interface VendedorDAO {
	
	void insert(Vendedor seller);
	
	void update(Vendedor seller);
	
	void deleteById(Integer id);

	Vendedor findById(Integer id);
	
	List<Vendedor> findAll();
	
	List<Vendedor> findByDepartment(Departamento department);
}
