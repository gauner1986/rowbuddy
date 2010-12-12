package de.rowbuddy.boundary.dtos;

import java.util.LinkedList;
import java.util.List;

public abstract class DtoConverter<EntityType, DtoType> {

	public abstract DtoType getDto(EntityType entity);

	public List<DtoType> getList(List<EntityType> entities) {
		List<DtoType> collection = new LinkedList<DtoType>();
		for (EntityType entity : entities) {
			collection.add(getDto(entity));
		}
		return collection;
	}

}
