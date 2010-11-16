package nl.fontys.rowbuddy.entities;

import javax.persistence.EntityExistsException;

import nl.fontys.rowbuddy.EntityManagerBeanLocal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.business.BoatManagement;
import de.rowbuddy.entities.Boat;


public class BoatManagementTest extends EjbTestBase {
	
	private Boat existingBoat;
	private BoatManagement boatManagement;
	
	@Before
	public void setup(){
		existingBoat = new Boat();
		existingBoat.setName("TestBoat 1");
		existingBoat.setLocked(false);
		existingBoat.setDeleted(false);
		existingBoat.setCoxed(true);
		existingBoat = (Boat) em.persist(existingBoat);
		
		boatManagement = Ejb.lookUp(BoatManagement.class, BoatManagement.class);
	}
	
	@After
	public void tearDown(){
		em.remove(Boat.class, existingBoat.getId());
		existingBoat = null;
	}
	
	@Test
	public void canAddBoat(){
		Boat boat = new Boat();
		boat.setName("New boat");
		boat.setCoxed(false);
		boat.setDeleted(false);
		boat.setLocked(false);
		
		boatManagement.addBoat(boat);
	}
	
	@Test(expected=EntityExistsException.class)
	public void cannotAddAlreadyExistingBoat(){
		boatManagement.addBoat(existingBoat);
	}
}
