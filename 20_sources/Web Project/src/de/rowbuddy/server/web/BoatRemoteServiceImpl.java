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
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public class BoatRemoteServiceImpl extends AbstractRemoteService implements
		BoatRemoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public List<BoatDTO> getBoatOverview() throws NotLoggedInException{
		try{
		return getRowBuddyFacade().getBoatOverview();
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public void addBoat(Boat boat) throws RowBuddyException, NotLoggedInException {
		try{
		getRowBuddyFacade().addBoat(boat);
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public void updateBoat(Boat boat) throws RowBuddyException, NotLoggedInException {
		getRowBuddyFacade().updateBoat(boat);
	}

	@Override
	public Boat getBoat(Long id) throws RowBuddyException, NotLoggedInException {
		Boat boat = null;
		try{
		boat = getRowBuddyFacade().getBoat(id);
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}

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
	public void deleteBoat(Long id) throws RowBuddyException {
		try{
		getRowBuddyFacade().deleteBoat(id);
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public List<DamageDTO> getOpenDamages() throws NotLoggedInException {
		try{
		return getRowBuddyFacade().getOpenDamages();
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public List<DamageDTO> getAllDamages() throws NotLoggedInException {
		try{
		return getRowBuddyFacade().getAllDamages();
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public BoatDamage getDamage(Long id) throws RowBuddyException {
		BoatDamage damage = null;
		try{
		damage = getRowBuddyFacade().getDamage(id);
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
		damage.getLogger().setPublishedTrips(new LinkedList<Trip>());
		damage.getLogger().setRoles(new LinkedList<Role>());
		damage.getBoat().setBoatReservations(
					new LinkedList<BoatReservation>());
		damage.getBoat().setBoatDamages(new LinkedList<BoatDamage>());
		return damage;
		
	}

	@Override
	public void updateDamage(BoatDamage damage) throws NotLoggedInException {
		try{
		getRowBuddyFacade().updateDamage(damage);
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public void addDamage(BoatDamage damage, Long boatId)
			throws RowBuddyException {
		try{
		getRowBuddyFacade().addDamage(damage, boatId);
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}

	@Override
	public List<BoatDTO> search(String query) throws NotLoggedInException {
		try{
		return getRowBuddyFacade().search(query);
		} catch (NullPointerException e){
			throw new NotLoggedInException("rowBuddyFacade = null");
		}
	}
}
