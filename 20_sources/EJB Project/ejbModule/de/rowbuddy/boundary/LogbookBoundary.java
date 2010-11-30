package de.rowbuddy.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.rowbuddy.business.Logbook;
import de.rowbuddy.business.dtos.TripDTO;
import de.rowbuddy.business.dtos.TripDTOConverter;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;

@Stateless
@LocalBean
public class LogbookBoundary {

	@EJB
	Logbook logbook;
	TripDTOConverter tripConverter = new TripDTOConverter();
	
	/**
	 * @return List of trips that are not finished yet.
	 */
	public List<TripDTO> getOpenTrips(Member currentUser) {
		List<Trip> trips = logbook.getOpenTrips(currentUser);
		List<TripDTO> dtoList = tripConverter.getList(trips);
		return addEditInformation(dtoList, trips, currentUser);
	}
	
	private List<TripDTO> addEditInformation(List<TripDTO> tripDtos, List<Trip> trips, Member currentUser){
		if (tripDtos.size() != trips.size()){
			throw new IllegalArgumentException("Collections must have same size");
		}
		for (int i = 0; i<tripDtos.size(); i++){
			Trip trip = trips.get(i);
			TripDTO dto = tripDtos.get(i);
			boolean canEdit = logbook.canEditTrip(trip, currentUser);
			
			dto.setCanEditTrip(canEdit);
		}
		return tripDtos;
	}	
}
