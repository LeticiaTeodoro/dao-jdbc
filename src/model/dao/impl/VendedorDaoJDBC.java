package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDAO {
	
	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select seller.*, department.Name as DepName from seller inner join department"
					+ " on seller.DepartmentId = department.id where seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Departamento dep = instantiateDepartment(rs);
				
				Vendedor vend = instantiateSeller(rs, dep);
				
				return vend;
			}
			
			return null;
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Vendedor instantiateSeller(ResultSet rs, Departamento dep) throws SQLException {

		Vendedor vend = new Vendedor();
		vend.setId(rs.getInt("Id"));
		vend.setName(rs.getString("Name"));
		vend.setEmail(rs.getString("Email"));
		vend.setBaseSalary(rs.getDouble("BaseSalary"));
		vend.setBirthDate(rs.getDate("BirthDate"));
		vend.setDepartment(dep);
		
		return vend;
	}

	private Departamento instantiateDepartment(ResultSet rs) throws SQLException {
		
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
