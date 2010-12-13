package de.rowbuddy.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.rowbuddy.boundary.converter.PersonalTripDTOConverter;
import de.rowbuddy.boundary.converter.TripDTOConverter;
import de.rowbuddy.boundary.dtos.PersonalTripDTO;
import de.rowbuddy.boundary.dtos.TripDTO;
import de.rowbuddy.business.Logbook;
import de.rowbuddy.business.Logbook.ListType;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Trip;

@Stateless
@LocalBean
public class LogbookBoundary {

	@EJB
	Logbook logbook;
	TripDTOConverter tripDtoConv = new TripDTOConverter();

	/**
	 * @return List of trips that are not finished yet.
	 */
	public List<TripDTO> getOpenTrips(Member currentUser) {
		List<Trip> trips = logbook.getOpenTrips(currentUser);
		List<TripDTO> dtoList = tripDtoConv.getList(trips);
		return addEditInformation(dtoList, trips, currentUser);
	}

	private List<TripDTO> addEditInformation(List<TripDTO> tripDtos,
			List<Trip> trips, Member currentUser) {
		if (tripDtos.size() != trips.size()) {
			throw new IllegalArgumentException(
					"Collections must have same size");
		}
		for (int i = 0; i < tripDtos.size(); i++) {
			Trip trip = trips.get(i);
			TripDTO dto = tripDtos.get(i);
			boolean canEdit = logbook.canEditTrip(trip, currentUser);

			dto.setCanEditTrip(canEdit);
		}
		return tripDtos;
	}

	/**
	 * Determines all trips that member has participated in
	 * 
	 * @param member
	 *            the part taking member
	 * @param listType
	 *            All or only open
	 * @return a list of PersonalTripDTO
	 */
	public List<PersonalTripDTO> getPersonalTrips(Member member,
			ListType listType) {

		List<Trip> trips = null;
		trips = logbook.getPersonalTrips(member, listType);

		PersonalTripDTOConverter conv = new PersonalTripDTOConverter(member);
		return conv.getList(trips);

	}
}
