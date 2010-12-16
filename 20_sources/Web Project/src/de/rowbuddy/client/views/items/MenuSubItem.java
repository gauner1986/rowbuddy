package de.rowbuddy.client.views.items;

public class MenuSubItem {
	private String title;
	private int id;
	
	public MenuSubItem(int id, String title){
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
