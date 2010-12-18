package de.rowbuddy.server.web;

import java.util.LinkedList;
import java.util.List;

import de.rowbuddy.client.services.RouteRemoteService;
import de.rowbuddy.entities.GpsPoint;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Route;
import de.rowbuddy.entities.Trip;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;

public class RouteRemoteServiceImpl extends AbstractRemoteService implements
		RouteRemoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Route addRoute(Route newRoute) throws RowBuddyException {
		try{
		return makeSerlizable(getRowBuddyFacade().addRoute(newRoute));
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
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
		try{
		for (Route route : getRowBuddyFacade().getRouteList()) {
			routes.add(makeSerlizable(route));
		}
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
		return routes;
	}

	@Override
	public Route editRoute(Route route) throws RowBuddyException {
		try{
		return makeSerlizable(getRowBuddyFacade().editRoute(route));
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

	@Override
	public Route getRoute(Long id) throws RowBuddyException {
		try{
		return makeSerlizable(getRowBuddyFacade().getRoute(id));
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

	@Override
	public void deleteRoute(Long id) throws RowBuddyException {
		try{
		getRowBuddyFacade().deleteRoute(id);
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

	@Override
	public boolean canEditRoute(Route route) throws NotLoggedInException {
		try{
		return getRowBuddyFacade().canEditRoute(route);
		} catch(NullPointerException e){
			throw new NotLoggedInException();
		}
	}

}
