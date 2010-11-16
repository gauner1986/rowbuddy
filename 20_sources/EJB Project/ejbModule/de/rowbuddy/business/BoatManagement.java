package de.rowbuddy.business;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.rowbuddy.business.dtos.BoatOverview;
import de.rowbuddy.dao.BoatDAO;
import de.rowbuddy.dao.BoatOverviewConverter;
import de.rowbuddy.entities.Boat;

@Stateless
public class BoatManagement {

	@EJB
	BoatDAO boatDAO;	
	BoatOverviewConverter converter;
	
	public BoatManagement() {
		converter = new BoatOverviewConverter();
	}
	
	public Collection<BoatOverview> getBoatOverview(){
		return converter.getCollection(boatDAO.getAll());
	}
	
	public Boat getBoat(long id){
		return boatDAO.get(id);
	}
	
	public Boat addBoat(Boat boat){
		return boatDAO.add(boat);
	}
	
	public void removeBoat(long id){
		throw new InYourFaceException();
	}
}
