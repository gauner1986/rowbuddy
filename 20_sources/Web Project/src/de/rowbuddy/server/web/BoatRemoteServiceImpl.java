package de.rowbuddy.server.web;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.OneToMany;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.BoatReservation;

public class BoatRemoteServiceImpl extends RemoteServiceServlet implements
		BoatRemoteService {

	@EJB
	private BoatManagement boatManagement;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<BoatDTO> getBoatOverview() {
		return boatManagement.getBoatOverview();
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
		try {
			boat = boatManagement.getBoat(id);			
			LinkedList<BoatDamage> damages = new LinkedList<BoatDamage>();
			LinkedList<BoatReservation> reservations = new LinkedList<BoatReservation>();

			Collections.copy(damages, boat.getBoatDamages());
			Collections.copy(reservations, boat.getBoatReservations());

			boat.setBoatDamages(damages);
			boat.setBoatReservations(reservations);
		} catch (Exception ex) {

		}

		return boat;
	}

	@Override
	public void deleteBoat(Long id) throws Exception {
		boatManagement.deleteBoat(id);
	}
}
