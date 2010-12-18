package de.rowbuddy.client.views.items.handlers;

import de.rowbuddy.client.views.items.MenuItem;
import de.rowbuddy.client.views.items.MenuSubItem;


public interface ClickMenuHandler {
	
	public void menuItemClicked(MenuItem clickedItem);
	
	public void menuSubItemClicked(MenuSubItem clickedSubItem);

}
