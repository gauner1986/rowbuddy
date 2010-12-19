package de.rowbuddy.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.rowbuddy.boundary.converter.BoatDTOConverter;
import de.rowbuddy.boundary.converter.DamageDTOConverter;
import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.business.ListType;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;

@Stateless
@LocalBean
public class BoatBoundary {

	@EJB
	private BoatManagement boatManagement;
	private BoatDTOConverter dtoConverter = new BoatDTOConverter();
	private DamageDTOConverter damageConverter = new DamageDTOConverter();

	public List<BoatDTO> getBoatOverview() {
		List<Boat> boats = boatManagement.getBoatOverview();
		return dtoConverter.getList(boats);
	}

	public List<DamageDTO> getOpenDamages() {
		List<BoatDamage> damages = boatManagement.getDamages(ListType.OPEN);
		return damageConverter.getList(damages);
	}

	public List<DamageDTO> getAllDamages() {
		List<BoatDamage> damages = boatManagement.getDamages(ListType.ALL);
		return damageConverter.getList(damages);
	}

	public List<BoatDTO> searchBoat(String search) {
		List<Boat> boats = boatManagement.searchBoat(search);
		return dtoConverter.getList(boats);
	}

	public List<BoatDTO> searchBoatNotLocked(String search) {
		List<Boat> boats = boatManagement.searchBoatNotLocked(search);
		return dtoConverter.getList(boats);
	}
}
