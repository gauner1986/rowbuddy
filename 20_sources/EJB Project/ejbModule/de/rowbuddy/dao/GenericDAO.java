package de.rowbuddy.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import com.sun.javadoc.ParameterizedType;

public class GenericDAO<T, ID extends Serializable> {
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<T> entityType;
	
	public GenericDAO() {
	}
	
	@SuppressWarnings("unchecked")
	public List<T>  listAll() {
		return (List<T>) getEntityManager().createQuery("select e from "+getEntityName() + " as e").getResultList();
	}
	
	public T getById(ID id) throws EntityNotFoundException {
		T entity = null;
		
		entity = getEntityManager().getReference(entityType, id);
		return entity;
	}
	
	public void persist(T entity) throws EntityExistsException {
		getEntityManager().persist(entity);
	}
	
	public void merge(T entity) {
		entity = getEntityManager().merge(entity);
	}
	
	public EntityManager getEntityManager() {
		if(this.em == null) {
			throw new IllegalStateException("Entity Manager has not been initialized.");
		}
		return this.em;
	}
	
	public String getEntityName() {
		String entityName = entityType.getSimpleName();
        if(entityType.isAnnotationPresent(javax.persistence.Table.class)) {
            entityName = entityType.getAnnotation(javax.persistence.Table.class).name();
        }
        return entityName; 
	}

}
