package de.chiaradia.messaging.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import de.chiaradia.messaging.entities.MessageEntity;

/**
 * Session Bean implementation class MessageAdmin
 */
@Stateless
@LocalBean
public class MessageAdmin {
	@PersistenceContext
	private EntityManager manager;
	
    /**
     * Default constructor. 
     */
    public MessageAdmin() {
        // TODO Auto-generated constructor stub
    }
    
    private void init(){
    	MessageEntity e1 = new MessageEntity();
    	e1.setMessage("Test Message");
    	manager.persist(e1);
    }
    
    public List<MessageEntity> getEntites(){
    	init();
    	Query query = manager.createQuery("SELECT msg FROM MessageEntity msg");
    	return (List<MessageEntity>) query.getResultList();
    }
    

}
