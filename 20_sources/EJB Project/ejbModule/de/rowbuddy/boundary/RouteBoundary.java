package de.rowbuddy.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.rowbuddy.boundary.converter.RouteDTOConverter;
import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.business.RouteManagement;
import de.rowbuddy.entities.Route;

@Stateless
@LocalBean
public class RouteBoundary {

	@EJB
	private RouteManagement routeManagement;
	private RouteDTOConverter dtoConverter = new RouteDTOConverter();

	public List<RouteDTO> searchRoute(String search) {
		List<Route> routes = routeManagement.searchRoute(search);
		return dtoConverter.getList(routes);
	}
}
