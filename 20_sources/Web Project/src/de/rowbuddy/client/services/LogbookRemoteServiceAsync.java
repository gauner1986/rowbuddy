package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public interface LogbookRemoteServiceAsync {

	public void logRowedTrip(Trip rowedTrip, AsyncCallback<Void> callback)
			throws RowBuddyException;

	public void startTrip(Trip startedTrip, AsyncCallback<Void> callback)
			throws RowBuddyException;

	public List<Trip> getOpenTrips(AsyncCallback<List<Trip>> callback);

	public void finishTrip(Trip openTrip, AsyncCallback<Void> callback)
			throws RowBuddyException;

}
