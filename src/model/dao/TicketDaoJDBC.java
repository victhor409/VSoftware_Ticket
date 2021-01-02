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
			st = conn.prepareStatement("INSERT INTO Ticket" + "(nome, cliente, cnpj, dataTicket, descricao)" 
					+ "VALUES" + "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getCliente());
			st.setString(3, obj.getCnpj());

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
					"UPDATE Ticket " +
					"SET nome=?, cliente=?, cnpj=?, dataTicket=?, descricao=?" +
					"WHERE Id = ?");

				st.setString(1, obj.getNome());
				st.setString(2, obj.getCliente());
				st.setString(3, obj.getCnpj());
				st.setDate(4, new java.sql.Date(obj.getDataTicket().getTime()));
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
				"DELETE FROM Ticket WHERE Id = ?");

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
			st = conn.prepareStatement("Select *from Ticket Where id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Ticket obj = new Ticket();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCliente(rs.getString("cliente"));
				obj.setCnpj(rs.getString("cnpj"));
				obj.setDataTicket(rs.getDate("date"));
				obj.setDescricao(rs.getString("descricao"));
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
			st = conn.prepareStatement("SELECT * FROM Ticket ;");
			rs = st.executeQuery();
			List<Ticket> list = new ArrayList<>();
			while (rs.next()) {
				Ticket obj = new Ticket();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCliente(rs.getString("cliente"));
				obj.setCnpj(rs.getString("cnpj"));
				obj.setDataTicket(rs.getDate("dataTicket"));
				obj.setDescricao(rs.getString("descricao"));
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
