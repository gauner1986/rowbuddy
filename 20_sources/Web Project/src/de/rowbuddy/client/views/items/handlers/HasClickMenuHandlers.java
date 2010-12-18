package de.rowbuddy.client.views.items.handlers;

import java.util.ArrayList;
import java.util.List;

import de.rowbuddy.client.events.AbstractEvent;
import de.rowbuddy.client.events.PresenterChanger;
import de.rowbuddy.client.views.items.MenuItem;
import de.rowbuddy.client.views.items.MenuSubItem;

public abstract class HasClickMenuHandlers {

	private List<ClickMenuHandler> observers = new ArrayList<ClickMenuHandler>();
	private AbstractEvent associatedEvent;
	
	public HasClickMenuHandlers(AbstractEvent associatedEvent){
		this.associatedEvent = associatedEvent;
	}
	
	public void addClickMenuHandler(ClickMenuHandler handler){
		observers.add(handler);
	}
	
	public void menuItemClicked(MenuItem menuItem){
		for(ClickMenuHandler c : observers){
			c.menuItemClicked(menuItem);
		}
	}
	
	public void menuSubItemClicked(MenuSubItem menuSubItem){
		for(ClickMenuHandler c : observers){
			c.menuSubItemClicked(menuSubItem);
		}
	}
	
	public AbstractEvent getAssociatedEvent() {
		return associatedEvent;
	}

	public void setAssociatedEvent(AbstractEvent associatedEvent) {
		this.associatedEvent = associatedEvent;
	}
	
}
