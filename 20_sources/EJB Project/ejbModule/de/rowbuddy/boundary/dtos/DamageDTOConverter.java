package de.rowbuddy.boundary.dtos;

import de.rowbuddy.entities.BoatDamage;

public class DamageDTOConverter extends DtoConverter<BoatDamage, DamageDTO> {

	@Override
	public DamageDTO getDto(BoatDamage entity) {
		DamageDTO damage = new DamageDTO();
		damage.setBootName(entity.getBoat().getName());
		damage.setDate(entity.getLogDate());
		damage.setId(entity.getId());
		damage.setOpen(entity.isFixed());
		return damage;
	}

}
