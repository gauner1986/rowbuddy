package de.rowbuddy.client.services;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.business.dtos.BoatOverview;

public interface BoatRemoteService extends RemoteService{
	public Collection<BoatOverview> getBoatOverview();
}
