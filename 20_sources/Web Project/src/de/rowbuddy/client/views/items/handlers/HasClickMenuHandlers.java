package de.rowbuddy.client.views.items.handlers;

import java.util.ArrayList;
import java.util.List;

import de.rowbuddy.client.views.items.MenuItem;
import de.rowbuddy.client.views.items.MenuSubItem;

public class HasClickMenuHandlers {

	private List<ClickMenuHandler> observers = new ArrayList<ClickMenuHandler>();
	
	public void addClickMenuHandler(ClickMenuHandler handler){
		observers.add(handler);
	}
	
	public void menuItemClicked(MenuItem menuItem){
		
	}
	
	public void menuSubItemClicked(MenuSubItem menuItem){
		
	}
	
}
