package nl.fontys.jee.rowbuddy.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.chiaradia.messaging.entities.MessageEntity;

public interface MessageServiceAsync {
	public void getMessages(AsyncCallback<List<MessageEntity>> asyncCallback);
}
