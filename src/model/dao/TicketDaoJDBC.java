package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.DB;
import DB.DbException;
import DB.DbIntegrityException;
import entities.Ticket;


public class TicketDaoJDBC implements TicketDao {

	private Connection conn;

	public TicketDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Ticket obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("Insert into tbl_Ticket" + "(Name, Client, cnpj, Date, Descricao)" + "VALUES" + "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getClient());
			st.setLong(3, obj.getCnpj());
			st.setDate(4, new java.sql.Date(obj.getDate().getTime()));
			st.setString(5, obj.getDescricao());
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	

	@Override
	public void update(Ticket obj) {
	 
			PreparedStatement st = null;
			
			try {
				st = conn.prepareStatement(
					"UPDATE tbl_Ticket " +
					"SET Name=?, Client=?, cnpj=?, Date=?, Descricao=?" +
					"WHERE Id = ?");

				st.setString(1, obj.getName());
				st.setString(2, obj.getClient());
				st.setLong(3, obj.getCnpj());
				st.setDate(4, new java.sql.Date(obj.getDate().getTime()));
				st.setString(5, obj.getDescricao());
				st.setInt(6, obj.getId());

				st.executeUpdate();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			} 
			finally {
				DB.closeStatement(st);
			}
		}
		

	

	@Override
	public void deleteById(Ticket id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM tbl_Ticket WHERE Id = ?");

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	

	@Override
	public Ticket findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select *from tbl_Ticket Where id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Ticket obj = new Ticket();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setClient(rs.getString("Client"));
				obj.setCnpj(rs.getLong("cnpj"));
				obj.setDate(rs.getDate("Date"));
				obj.setDescricao(rs.getString("Descricao"));
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Ticket> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT *FROM tbl_Ticket");
			rs = st.executeQuery();
			List<Ticket> list = new ArrayList<>();
			while (rs.next()) {
				Ticket obj = new Ticket();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setClient(rs.getString("Client"));
				obj.setCnpj(rs.getLong("cnpj"));
				obj.setDate(rs.getDate("Date"));
				obj.setDescricao(rs.getString("Descricao"));
				list.add(obj);
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
