package de.rowbuddy.client.services;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.entities.Boat;

public interface BoatRemoteService extends RemoteService{
	public Collection<BoatDTO> getBoatOverview();
	public void addBoat(Boat boat);
}
