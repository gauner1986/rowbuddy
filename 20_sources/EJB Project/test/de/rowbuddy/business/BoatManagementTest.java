package de.rowbuddy.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbExceptionHandler;
import de.rowbuddy.util.EjbTestBase;

public class BoatManagementTest extends EjbTestBase {

	private Boat existingBoat;
	private Boat newBoat;
	private Boat deletedBoat;
	private BoatManagement boatManagement;
	private EjbExceptionHandler ejbHandler;

	@Before
	public void setup() {
		ejbHandler = new EjbExceptionHandler();
		existingBoat = new Boat();
		existingBoat.setName("TestBoat 1");
		existingBoat.setLocked(false);
		existingBoat.setNumberOfSeats(1);
		existingBoat.setDeleted(false);
		existingBoat.setCoxed(true);
		existingBoat = (Boat) em.persist(existingBoat);

		newBoat = new Boat();
		newBoat.setName("New boat");
		newBoat.setNumberOfSeats(10);
		newBoat.setCoxed(false);
		newBoat.setDeleted(false);
		newBoat.setLocked(false);

		deletedBoat = new Boat();
		deletedBoat.setName("Deleted Boat");
		deletedBoat.setLocked(false);
		deletedBoat.setNumberOfSeats(12);
		deletedBoat.setDeleted(true);
		deletedBoat.setCoxed(true);
		deletedBoat = (Boat) em.persist(deletedBoat);
		
		boatManagement = Ejb.lookUp(BoatManagement.class, BoatManagement.class);
	}

	@After
	public void tearDown() {
		newBoat = null;
		ejbHandler = null;
		
		removeEntity(Boat.class, existingBoat.getId());
		removeEntity(Boat.class, deletedBoat.getId());
	
		deletedBoat = null;
		existingBoat = null;
	}
	
	private <T> void removeEntity(Class<T> type, Object key){
		T obj = em.find(type, key);
		if (obj != null){
			em.remove(type, key);
		}	
	}

	@Test
	public void canAddBoat() {
		boatManagement.addBoat(newBoat);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotAddBoatWithoutName() throws Exception {
		newBoat.setName("");

		ejbHandler.execute(new Runnable() {

			@Override
			public void run() {
				boatManagement.addBoat(newBoat);
			}

		});
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotAddBoatWithoutNumberOfSeats() throws Exception {
		newBoat.setNumberOfSeats(0);

		ejbHandler.execute(new Runnable() {

			@Override
			public void run() {
				boatManagement.addBoat(newBoat);
			}

		});
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotAddDeletedBoat() throws Exception {
		newBoat.setDeleted(true);

		ejbHandler.execute(new Runnable() {

			@Override
			public void run() {
				boatManagement.addBoat(newBoat);
			}

		});
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotAddAlreadyExistingBoat() throws Exception {

		ejbHandler.execute(new Runnable() {
			@Override
			public void run() {
				boatManagement.addBoat(existingBoat);
			}
		});
	}

	@Test
	public void canUpdateBoat() {
		existingBoat.setName("new name");
		Boat updatedBoat = boatManagement.updateBoat(existingBoat);
		assertThat(updatedBoat.getName(), is(existingBoat.getName()));

		updatedBoat = boatManagement.getBoat(existingBoat.getId());
		assertThat(updatedBoat.getName(), is(existingBoat.getName()));
	}

	@Test(expected = EntityNotFoundException.class)
	public void cannotUpdateNonExistingBoat() throws Exception {
		ejbHandler.execute(new Runnable() {
			@Override
			public void run() {
				boatManagement.updateBoat(newBoat);
			}
		});
	}

	@Test(expected = EntityNotFoundException.class)
	public void cannotUpdateDeletedBoat() throws Exception {
		deletedBoat.setDeleted(false);
		
		ejbHandler.execute(new Runnable() {
			@Override
			public void run() {
				boatManagement.updateBoat(deletedBoat);
			}
		});
	}

	public void canDeleteBoat() {
		boatManagement.deleteBoat(existingBoat.getId());
		Boat dbBoat = boatManagement.getBoat(existingBoat.getId());
		assertThat(dbBoat.isDeleted(), is(true));
	}

	@Test(expected = EntityNotFoundException.class)
	public void cannotDeleteAlreadyDeletedBoat() throws Exception {
		ejbHandler.execute(new Runnable() {
			@Override
			public void run() {
				boatManagement.deleteBoat(deletedBoat.getId());
			}
		});
	}
	
	@Test
	public void canGetBoat(){
		Boat boat = boatManagement.getBoat(existingBoat.getId());
		assertNotNull(boat);
	}
	
	@Test
	public void canGetDeletedBoat(){
		Boat boat = boatManagement.getBoat(deletedBoat.getId());
		assertNotNull(boat);
	}
	
	@Test
	public void canGetOverview(){
		Collection<BoatDTO> boats = boatManagement.getBoatOverview();
		boolean containsExistingBoat = false;
		for (BoatDTO boat : boats){
			if (boat.getId() == existingBoat.getId()){
				containsExistingBoat = true;
			}
			if (boat.getId() == deletedBoat.getId()){
				fail("deleted boat found");
			}
		}
		if (!containsExistingBoat){
			fail("existing boat not found");
		}
	}
}
