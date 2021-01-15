package model.service;

import java.util.ArrayList;
import java.util.List;

import entities.Ticket;
import model.dao.DaoFactory;
import model.dao.TicketDao;

public class TicketService {
	
	private TicketDao dao = DaoFactory.createTicketDao();
	
	public List<Ticket> findAll(){
	
	return dao.findAll();

	}
	
	public void saveOrUpdate(Ticket obj) {
		if (obj.getId()==null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	

}
