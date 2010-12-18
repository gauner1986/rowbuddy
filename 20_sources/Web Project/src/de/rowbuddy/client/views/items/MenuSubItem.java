package de.rowbuddy.client.views.items;

import de.rowbuddy.client.events.AbstractEvent;
import de.rowbuddy.client.views.items.handlers.HasClickMenuHandlers;

public class MenuSubItem extends HasClickMenuHandlers{
	private String title;
	private int id;
	
	public MenuSubItem(int id, String title, AbstractEvent associatedEvent){
		super(associatedEvent);
		this.id = id;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
