package de.rowbuddy.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.rowbuddy.entities.Route;

public interface RouteRemoteServiceAsync {

	void addRoute(Route newRoute, AsyncCallback<Route> callback);

	void canEditRoute(Route route, AsyncCallback<Boolean> callback);

	void deleteRoute(Long id, AsyncCallback<Void> callback);

	void editRoute(Route route, AsyncCallback<Route> callback);

	void getRoute(Long id, AsyncCallback<Route> callback);

	void getRouteForEdit(Long id, AsyncCallback<Route> callback);

	void getRouteList(AsyncCallback<List<Route>> callback);

}
