package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDAO {
	
	private Connection conn;
	
	private PreparedStatement st = null;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor seller) {
		
		try {
			
			st = conn.prepareStatement("insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ " values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					
					int id = rs.getInt(1);
					seller.setId(id);
				}
				
				DB.closeResultSet(rs);
				
			} else {
				
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
			
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
		}
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
		
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select seller.*, department.Name as DepName from seller inner join department"
					+ " on seller.DepartmentId = department.id order by Name");
			
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Vendedor vend = instantiateSeller(rs, dep);
				
				list.add(vend);
			}
			
			return list;
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> findByDepartment(Departamento department) {
		
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select seller.*, department.Name as DepName from seller inner join department"
					+ " on seller.DepartmentId = department.id where DepartmentId = ? order by Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Vendedor vend = instantiateSeller(rs, dep);
				
				list.add(vend);
			}
			
			return list;
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}