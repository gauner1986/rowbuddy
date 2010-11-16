package de.rowbuddy.business;

import java.util.Collection;

import javax.ejb.Stateless;

import de.rowbuddy.entities.Boat;

@Stateless
public class BoatManagement {

	public BoatManagement() {
		// TODO Auto-generated constructor stub
	}
	
	public Collection<BoatOverview> getBoatOverview(){
		throw new InYourFaceException();
	}
	
	public Boat getBoat(long id){
		throw new InYourFaceException();
	}
	
	public Boat addBoat(Boat boat){
		throw new InYourFaceException();
	}
	
	public void removeBoat(long id){
		throw new InYourFaceException();
	}
}
