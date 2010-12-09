package de.rowbuddy.server.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.client.services.BoatRemoteService;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.entities.BoatReservation;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public class BoatRemoteServiceImpl extends AbstractRemoteService implements
		BoatRemoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public List<BoatDTO> getBoatOverview() {
		return getRowBuddyFacade().getBoatOverview();
	}

	@Override
	public void addBoat(Boat boat) throws Exception {
		getRowBuddyFacade().addBoat(boat);
	}

	@Override
	public void updateBoat(Boat boat) throws Exception {
		getRowBuddyFacade().updateBoat(boat);
	}

	@Override
	public Boat getBoat(Long id) throws Exception {

		Boat boat = null;
		boat = getRowBuddyFacade().getBoat(id);

		List<BoatDamage> damages = new ArrayList<BoatDamage>();
		List<BoatReservation> reservations = new ArrayList<BoatReservation>();

		for (BoatDamage damage : boat.getBoatDamages()) {
			Member member = damage.getLogger();
			member.setRoles(new LinkedList<Role>());
			member.setPublishedTrips(new LinkedList<Trip>());
			damages.add(damage);
		}

		reservations.addAll(boat.getBoatReservations());

		boat.setBoatDamages(damages);
		boat.setBoatReservations(reservations);

		return boat;
	}

	@Override
	public void deleteBoat(Long id) throws Exception {
		getRowBuddyFacade().deleteBoat(id);
	}

	@Override
	public List<DamageDTO> getOpenDamages() {
		return getRowBuddyFacade().getOpenDamages();
	}

	@Override
	public List<DamageDTO> getAllDamages() {
		return getRowBuddyFacade().getAllDamages();
	}

	@Override
	public BoatDamage getDamage(Long id) {
		BoatDamage damage = null;
		try {
			damage = getRowBuddyFacade().getDamage(id);
			damage.getLogger().setPublishedTrips(new LinkedList<Trip>());
			damage.getLogger().setRoles(new LinkedList<Role>());
			damage.getBoat().setBoatReservations(
					new LinkedList<BoatReservation>());
			damage.getBoat().setBoatDamages(new LinkedList<BoatDamage>());
		} catch (RowBuddyException ex) {
			ex.printStackTrace();
		}
		return damage;
	}

	@Override
	public void updateDamage(BoatDamage damage) {
		getRowBuddyFacade().updateDamage(damage);
	}

	@Override
	public void addDamage(BoatDamage damage, Long boatId)
			throws RowBuddyException {
		getRowBuddyFacade().addDamage(damage, boatId);
	}

	@Override
	public List<BoatDTO> search(String query) {
		return getRowBuddyFacade().search(query);
	}
}
