package de.rowbuddy.boundary.converter;

import de.rowbuddy.boundary.dtos.DamageDTO;
import de.rowbuddy.entities.BoatDamage;

public class DamageDTOConverter extends DtoConverter<BoatDamage, DamageDTO> {

	@Override
	public DamageDTO getDto(BoatDamage entity) {
		DamageDTO damage = new DamageDTO();
		damage.setBootName(entity.getBoat().getName());
		damage.setDate(entity.getLogDate());
		damage.setId(entity.getId());
		damage.setFixed(entity.isFixed());
		damage.setMember(entity.getLogger().getFullName());
		return damage;
	}

}
