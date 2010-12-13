package de.rowbuddy.boundary.converter;

import de.rowbuddy.boundary.dtos.DtoConverter;
import de.rowbuddy.boundary.dtos.RouteDTO;
import de.rowbuddy.entities.Route;

public class RouteDTOConverter extends DtoConverter<Route, RouteDTO> {

	private static final transient MemberDTOConverter mConv = new MemberDTOConverter();

	@Override
	public RouteDTO getDto(Route entity) {
		RouteDTO route = new RouteDTO();
		route.setDeleted(entity.isDeleted());
		route.setId(entity.getId());
		route.setLastEditor(mConv.getDto(entity.getLastEditor()));
		route.setLengthKM(entity.getLengthKM());
		route.setMutable(entity.isMutable());
		route.setName(entity.getName());
		route.setParentId(entity.getParentId());
		route.setShortDescription(entity.getShortDescription());
		return route;
	}

}
