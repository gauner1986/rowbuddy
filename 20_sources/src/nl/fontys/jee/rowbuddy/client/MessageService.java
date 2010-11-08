package nl.fontys.jee.rowbuddy.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.chiaradia.messaging.entities.MessageEntity;

public interface MessageService extends RemoteService{
	public List<MessageEntity> getMessages();
}
