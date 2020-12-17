package model.dao;

import DB.DB;


public class DaoFactory {

	public static TicketDao createTicketDao() {
		return new TicketDaoJDBC(DB.getConnection());
	}
}