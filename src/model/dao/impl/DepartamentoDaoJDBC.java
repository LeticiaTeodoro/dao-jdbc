package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDAO;
import model.entities.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDAO {
	
	private Connection conn;
	
	private PreparedStatement st = null;

	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Departamento depart) {
		
		try {
			
			st = conn.prepareStatement("insert into department (Name) values (?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, depart.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					
					int id = rs.getInt(1);
					depart.setId(id);
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
	public void update(Departamento depart) {
		
		try {
			
			st = conn.prepareStatement("update department set Name = ? where Id = ?");
			st.setString(1, depart.getName());
			st.setInt(2, depart.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
		}		
	}

	@Override
	public void deleteById(Integer id) {
		
		try {
			
			st = conn.prepareStatement("delete from department where Id = ?");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				
				throw new Error("Id não encontrado");
			}
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
		}		
	}

	@Override
	public Departamento findById(Integer id) {
		
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from department where Id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				return instantiateDepartment(rs);
				
			} else {
				
				throw new Error("Id não encontrado");
			}			
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Departamento> findAll() {
		
		ResultSet rs = null;

		try {
			
			st = conn.prepareStatement("select * from department");
			
			rs = st.executeQuery();	
			
			List<Departamento> listDepart = new ArrayList<Departamento>();
			
			while(rs.next()) {				
				
				listDepart.add(instantiateDepartment(rs));
			}
			
			return listDepart;
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		} finally {
			
			DB.closeStatement(st);
		}
	}
	
	private Departamento instantiateDepartment(ResultSet rs) throws SQLException {
		
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		
		return dep;
	}
}