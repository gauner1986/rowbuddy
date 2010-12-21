package de.rowbuddy.client.menu;

import de.rowbuddy.client.events.AbstractEvent;

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
