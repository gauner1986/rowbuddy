package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.rowbuddy.entities.Route;
import de.rowbuddy.exceptions.RowBuddyException;

public interface RouteRemoteService extends RemoteService {

	public Route addRoute(Route newRoute) throws RowBuddyException;

	public List<Route> getRouteList() throws RowBuddyException;

	public Route editRoute(Route route) throws RowBuddyException;

	public Route getRoute(Long id) throws RowBuddyException;

	public void deleteRoute(Long id) throws RowBuddyException;

	public boolean canEditRoute(Route route);

}
