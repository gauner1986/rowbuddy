package de.rowbuddy.client.services;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.business.BoatOverview;

public interface BoatRemoteServiceAsync {
	void getBoatOverview(AsyncCallback<Collection<BoatOverview>> callback);
}
