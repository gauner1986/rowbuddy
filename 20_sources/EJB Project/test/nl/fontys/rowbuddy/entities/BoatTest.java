package nl.fontys.rowbuddy.entities;

import org.junit.Test;

import de.rowbuddy.entities.Boat;


public class BoatTest extends EjbTestBase {
	
	@Test
	public void canAddBoat(){
		Boat boat = new Boat();
		boat.setName("New boat");
		boat.setCoxed(false);
		boat.setDeleted(false);
		boat.setLocked(false);
		
		em.persist(boat);
	}
}
