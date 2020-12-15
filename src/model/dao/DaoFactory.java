package model.dao;

import DB.DB;


public class DaoFactory {

	public static TicketDao createDepartmentDao() {
		return new TicketDaoJDBC(DB.getConnection());
	}
}