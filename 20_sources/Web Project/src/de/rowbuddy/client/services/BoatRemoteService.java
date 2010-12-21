package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.exceptions.RowBuddyException;

public interface BoatRemoteService extends RemoteService {
	public List<BoatDTO> getBoatOverview() throws RowBuddyException;

	public void addBoat(Boat boat) throws RowBuddyException;

	public void updateBoat(Boat boat) throws RowBuddyException;

	public Boat getBoat(Long id) throws RowBuddyException;

	public void deleteBoat(Long id) throws RowBuddyException;

	public List<DamageDTO> getOpenDamages() throws RowBuddyException;

	public List<DamageDTO> getAllDamages() throws RowBuddyException;

	public BoatDamage getDamage(Long id) throws RowBuddyException;

	public void updateDamage(BoatDamage damage) throws RowBuddyException;

	public List<BoatDTO> search(String query) throws RowBuddyException;

	void addDamage(BoatDamage damage, Long boatId) throws RowBuddyException;
}
