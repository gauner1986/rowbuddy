package de.rowbuddy.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.business.dtos.BoatDTOConverter;
import de.rowbuddy.entities.Boat;

@Stateless
@LocalBean
public class BoatBoundary {

	@EJB
	BoatManagement boatManagement;
	BoatDTOConverter dtoConverter;
	
	public List<BoatDTO> getBoatOverview() {
		List<Boat> boats = boatManagement.getBoatOverview();
		return dtoConverter.getList(boats);
	}
	
}
