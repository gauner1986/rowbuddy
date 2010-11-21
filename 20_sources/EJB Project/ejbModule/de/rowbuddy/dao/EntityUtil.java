package de.rowbuddy.dao;

import javax.persistence.EntityManager;

public class EntityUtil {
	
	private EntityManager em;
	
	public EntityUtil(EntityManager em) {
		this.em = em;
	}
	
	public <T> T getById(Class<T> type, Object id) {
		return (T)em.getReference(type, id);
	}

}
