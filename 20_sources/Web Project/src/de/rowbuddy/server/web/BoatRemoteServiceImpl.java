package de.rowbuddy.server.web;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.business.dtos.BoatOverview;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.entities.Boat;

public class BoatRemoteServiceImpl extends RemoteServiceServlet implements BoatRemoteService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<BoatOverview> getBoatOverview() {
		// TODO Auto-generated method stub
		List<BoatOverview> boats = new LinkedList<BoatOverview>();
		BoatOverview boat1 = new BoatOverview();
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
