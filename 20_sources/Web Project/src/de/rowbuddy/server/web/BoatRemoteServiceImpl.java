package de.rowbuddy.server.web;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.entities.Boat;

public class BoatRemoteServiceImpl extends RemoteServiceServlet implements BoatRemoteService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<BoatDTO> getBoatOverview() {
		// TODO Auto-generated method stub
		List<BoatDTO> boats = new LinkedList<BoatDTO>();
		BoatDTO boat1 = new BoatDTO();
		boat1.setId(1);
		boat1.setLocked(false);
		boat1.setCoxed(true);
		boat1.setName("Unsinkbar 3");
		boat1.setNumberOfSeats(5);
		boats.add(boat1);
		boats.add(boat1);
		boats.add(boat1);
		
		return boats;
	}

	@Override
	public void addBoat(Boat boat) {
		// TODO Auto-generated method stub
		
	}
	
	

}
