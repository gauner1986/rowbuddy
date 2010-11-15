package nl.fontys.rowbuddy.entities;

import org.junit.Test;


public class BoatTest extends EjbTestBase {
	
	@Test
	public void canAddBoat(){
		BoatEntity boat = new BoatEntity();
		boat.setName("New boat");
		boat.setCoxed(false);
		boat.setDeleted(false);
		boat.setLocked(false);
		
		em.persist(boat);
	}
}
