package de.rowbuddy.client.services;

import java.util.List;

import javax.ejb.CreateException;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.entities.Boat;

public interface BoatRemoteService extends RemoteService{
	public List<BoatDTO> getBoatOverview();
	public void addBoat(Boat boat) throws Exception;
	public void updateBoat(Boat boat);
	public Boat getBoat(Long id);
}
