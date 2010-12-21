package de.rowbuddy.server.web;

import java.util.LinkedList;
import java.util.List;

import de.rowbuddy.client.services.RouteRemoteService;
import de.rowbuddy.entities.GpsPoint;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.RowBuddyException;

public class RouteRemoteServiceImpl extends AbstractRemoteService implements
		RouteRemoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Route addRoute(Route newRoute) throws RowBuddyException {
		return makeSerlizable(getRowBuddyFacade().addRoute(newRoute));
	}

	private Route makeSerlizable(Route route) throws RowBuddyException {
		List<GpsPoint> points = new LinkedList<GpsPoint>(route.getWayPoints());
		route.setWayPoints(points);
		route.getLastEditor().setRoles(new LinkedList<Role>());
		route.getLastEditor().setPublishedTrips(new LinkedList<Trip>());
		return route;
	}

	@Override
	public List<Route> getRouteList() throws RowBuddyException {
		List<Route> routes = new LinkedList<Route>();
		for (Route route : getRowBuddyFacade().getRouteList()) {
			routes.add(makeSerlizable(route));
		}
		return routes;
	}

	@Override
	public Route editRoute(Route route) throws RowBuddyException {
		return makeSerlizable(getRowBuddyFacade().editRoute(route));
	}

	@Override
	public Route getRoute(Long id) throws RowBuddyException {
		return makeSerlizable(getRowBuddyFacade().getRoute(id));
	}

	@Override
	public void deleteRoute(Long id) throws RowBuddyException {
		getRowBuddyFacade().deleteRoute(id);
	}

	@Override
	public boolean canEditRoute(Route route) throws RowBuddyException {
		return getRowBuddyFacade().canEditRoute(route);
	}

	@Override
	public Route getRouteForEdit(Long id) throws RowBuddyException {
		Route route = getRoute(id);
		if (!canEditRoute(route)) {
			throw new RowBuddyException(
					"Keine Berechtigung um Route zu bearbeiten");
		}
		return route;
	}

}
