package de.rowbuddy.server.web;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import nl.fontys.jee.rowbuddy.client.MessageService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.chiaradia.messaging.entities.MessageEntity;
import de.chiaradia.messaging.service.MessageAdmin;

/**
 * Servlet implementation class MessageServiceImpl
 */
@WebServlet("/MessageServiceImpl")
public class MessageServiceImpl extends RemoteServiceServlet implements MessageService{
	private static final long serialVersionUID = 1L;

	@EJB
	private MessageAdmin messageService; 
	
	@Override
	public List<MessageEntity> getMessages() {
		System.out.println("Request!");
		return messageService.getEntites();
	}
	
	
      
}
