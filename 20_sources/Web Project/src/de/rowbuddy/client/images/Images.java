package de.rowbuddy.client.images;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Tree;

public interface Images extends Tree.Resources{
	
	@Source("boat.png")
	ImageResource boat();
	
	@Source("fahrtenbuch.png")
	ImageResource logBook();
	
	@Source("open_blue.png")
	ImageResource openTrips();
	
	@Source("people.png")
	ImageResource profil();
	
	@Source("statistics.png")
	ImageResource statistics();
	
	@Source("member.png")
	ImageResource member();
	
	@Source("map.png")
	ImageResource map();
}
