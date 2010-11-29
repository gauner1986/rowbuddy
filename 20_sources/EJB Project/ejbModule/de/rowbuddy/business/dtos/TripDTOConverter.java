package de.rowbuddy.business.dtos;

import java.util.LinkedList;
import java.util.List;

import de.rowbuddy.entities.Trip;

public class TripDTOConverter {

	public TripDTO getObject(Trip trip){
		TripDTO dto = new TripDTO();
		dto.trip = trip;
		return dto;
	}
	
	public List<TripDTO> getList(List<Trip>  trips){
		List<TripDTO> collection = new LinkedList<TripDTO>();
		for (Trip b : trips){
			collection.add(getObject(b));
		}
		return collection;
	}
	
	
}
