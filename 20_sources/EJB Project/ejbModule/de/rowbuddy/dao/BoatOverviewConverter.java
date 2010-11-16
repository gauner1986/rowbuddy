package de.rowbuddy.dao;

import java.util.Collection;
import java.util.LinkedList;

import de.rowbuddy.business.dtos.BoatOverview;
import de.rowbuddy.entities.Boat;

public class BoatOverviewConverter {
	
	public BoatOverview getObject(Boat boat){
		BoatOverview bo = new BoatOverview();
		bo.setId(boat.getId());
		bo.setName(boat.getName());
		bo.setCoxed(boat.isCoxed());
		bo.setNumberOfSeats(boat.getNumberOfSeats());
		bo.setLocked(boat.isLocked());
		
		return bo;
	}
	
	public Collection<BoatOverview> getCollection(Collection<Boat>  boats){
		Collection<BoatOverview> collection = new LinkedList<BoatOverview>();
		for (Boat b : boats){
			collection.add(getObject(b));
		}
		return collection;
	}
}
