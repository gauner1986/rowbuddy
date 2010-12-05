package de.rowbuddy.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.rowbuddy.boundary.dtos.BoatDTO;
import de.rowbuddy.boundary.dtos.BoatDTOConverter;
import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.boundary.dtos.DamageDTOConverter;
import de.rowbuddy.business.BoatManagement;
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
	
	public List<DamageDTO> getDamages(){
		List<BoatDamage> damages = boatManagement.getDamages();
		return damageConverter.getList(damages);
	}
}
