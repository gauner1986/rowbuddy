package de.rowbuddy.server.web;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.entities.Boat;

public class BoatRemoteServiceImpl extends RemoteServiceServlet implements BoatRemoteService{

	@EJB
	BoatManagement boatManagement;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<BoatDTO> getBoatOverview() {
		return boatManagement.getBoatOverview();
	}

	@Override
	public void addBoat(Boat boat) {
		boatManagement.addBoat(boat);
	}
}