package nl.fontys.rowbuddy;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class EntityManagerBean
 */
@Stateless
public class EntityManagerBean implements EntityManagerBeanLocal {

	@PersistenceContext
	private EntityManager em;
    
    public void persist(Object entity){
    	em.persist(entity);
    }
    
    

}
