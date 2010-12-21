package de.rowbuddy.client.menu;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;

import de.rowbuddy.client.events.AbstractEvent;

public class MenuItem extends HasClickMenuHandlers {
	private int id;
	private String title;
	private List<MenuSubItem> subItems = new ArrayList<MenuSubItem>();
	private ImageResource image;
	
	public MenuItem(int id, String title, ImageResource image, AbstractEvent associatedEvent){
		super(associatedEvent);
		this.id = id;
		this.title = title;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageResource getImage() {
		return image;
	}

	public void setImage(ImageResource image) {
		this.image = image;
	}

	public List<MenuSubItem> getSubItems() {
		return subItems;
	}

}
