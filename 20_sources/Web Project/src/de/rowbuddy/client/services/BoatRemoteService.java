package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public interface BoatRemoteService extends RemoteService {
	public List<BoatDTO> getBoatOverview() throws NotLoggedInException;

	public void addBoat(Boat boat) throws Exception;

	public void updateBoat(Boat boat) throws Exception;

	public Boat getBoat(Long id) throws Exception;

	public void deleteBoat(Long id) throws Exception;

	public List<DamageDTO> getOpenDamages() throws NotLoggedInException;

	public List<DamageDTO> getAllDamages() throws NotLoggedInException;

	public BoatDamage getDamage(Long id) throws Exception;

	public void updateDamage(BoatDamage damage) throws RowBuddyException;

	public List<BoatDTO> search(String query) throws NotLoggedInException;

	void addDamage(BoatDamage damage, Long boatId) throws RowBuddyException;
}
