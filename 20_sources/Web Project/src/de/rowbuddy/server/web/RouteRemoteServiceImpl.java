package de.rowbuddy.server.web;

import java.util.List;

import de.rowbuddy.client.services.RouteRemoteService;
import de.rowbuddy.entities.Route;
import de.rowbuddy.exceptions.RowBuddyException;

public class RouteRemoteServiceImpl extends AbstractRemoteService implements
		RouteRemoteService {

	@Override
	public Route addRoute(Route newRoute) throws RowBuddyException {
		return getRowBuddyFacade().addRoute(newRoute);
	}

	@Override
	public List<Route> getRouteList() {
		return getRowBuddyFacade().getRouteList();
	}

	@Override
	public Route editRoute(Route route) throws RowBuddyException {
		return getRowBuddyFacade().editRoute(route);
	}

	@Override
	public Route getRoute(Long id) throws RowBuddyException {
		return getRowBuddyFacade().getRoute(id);
	}

	@Override
	public void deleteRoute(Long id) throws RowBuddyException {
		getRowBuddyFacade().deleteRoute(id);
	}

	@Override
	public boolean canEditRoute(Route route) {
		return getRowBuddyFacade().canEditRoute(route);
	}

}
