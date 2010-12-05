package de.rowbuddy.server.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.boundary.BoatBoundary;
import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.BoatReservation;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Trip;

public class BoatRemoteServiceImpl extends RemoteServiceServlet implements
		BoatRemoteService {

	@EJB
	private BoatManagement boatManagement;
	
	@EJB
	private BoatBoundary boatBoundary;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<BoatDTO> getBoatOverview() {
		return boatBoundary.getBoatOverview();
	}

	@Override
	public void addBoat(Boat boat) throws Exception {
		boatManagement.addBoat(boat);
	}

	@Override
	public void updateBoat(Boat boat) throws Exception {
		boatManagement.updateBoat(boat);
	}

	@Override
	public Boat getBoat(Long id) throws Exception {
		
		Boat boat = null;
		boat = boatManagement.getBoat(id);
		
		List<BoatDamage> damages = new ArrayList<BoatDamage>();
		List<BoatReservation> reservations = new ArrayList<BoatReservation>();
		
		for(BoatDamage damage : boat.getBoatDamages()){
			Member member = damage.getLogger();
			
			LinkedList<Role> roles = new LinkedList<Role>();
			roles.addAll(member.getRoles());
			member.setRoles(roles);
			
			LinkedList<Trip> trips = new LinkedList<Trip>();
			trips.addAll(member.getPublishedTrips());
			member.setPublishedTrips(trips);
			
			damages.add(damage);
		}
		
		reservations.addAll(boat.getBoatReservations());
				
		boat.setBoatDamages(damages);
		boat.setBoatReservations(reservations);
		
		return boat;
	}
	

	@Override
	public void deleteBoat(Long id) throws Exception {
		boatManagement.deleteBoat(id);
	}

	@Override
	public List<BoatDamage> getDamages() {
		List<BoatDamage> damages = boatManagement.getDamages();
		
		return damages;
	}
}
