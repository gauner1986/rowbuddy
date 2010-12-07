package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;

public interface BoatRemoteService extends RemoteService {
	public List<BoatDTO> getBoatOverview();

	public void addBoat(Boat boat) throws Exception;

	public void updateBoat(Boat boat) throws Exception;

	public Boat getBoat(Long id) throws Exception;

	public void deleteBoat(Long id) throws Exception;

	public List<DamageDTO> getOpenDamages();

	public List<DamageDTO> getAllDamages();

	public BoatDamage getDamage(Long id) throws Exception;

	public void updateDamage(BoatDamage damage);

	public List<BoatDTO> search(String query);
}
