package de.rowbuddy.client.services;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.business.dtos.BoatOverview;
import de.rowbuddy.entities.Boat;

public interface BoatRemoteServiceAsync {
	void getBoatOverview(AsyncCallback<Collection<BoatOverview>> callback);
	void addBoat(Boat boat, AsyncCallback<Void> callback);
}
