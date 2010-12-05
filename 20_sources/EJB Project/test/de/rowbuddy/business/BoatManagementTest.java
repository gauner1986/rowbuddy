package de.rowbuddy.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.FinderException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.BoatDamage;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class BoatManagementTest extends EjbTestBase {

	private Boat existingBoat;
	private Boat newBoat;
	private Boat deletedBoat;
	private BoatManagement boatManagement;

	@Before
	public void setup() throws RowBuddyException {

		db.setupMembers();
		
		existingBoat = new Boat();
		existingBoat.setName("TestBoat 1");
		existingBoat.setLocked(false);
		existingBoat.setNumberOfSeats(1);
		existingBoat.setDeleted(false);
		existingBoat.setCoxed(true);
		existingBoat.validate();
		existingBoat = (Boat) em.persist(existingBoat);

		newBoat = new Boat();
		newBoat.setName("New boat");
		newBoat.setNumberOfSeats(10);
		newBoat.setCoxed(false);
		newBoat.setDeleted(false);
		newBoat.setLocked(false);
		newBoat.validate();

		deletedBoat = new Boat();
		deletedBoat.setName("Deleted Boat");
		deletedBoat.setLocked(false);
		deletedBoat.setNumberOfSeats(12);
		deletedBoat.setDeleted(true);
		deletedBoat.setCoxed(true);
		deletedBoat.validate();
		deletedBoat = (Boat) em.persist(deletedBoat);

		boatManagement = Ejb.lookUp(BoatManagement.class, BoatManagement.class);
	}

	@After
	public void tearDown() {
		newBoat = null;
		deletedBoat = null;
		existingBoat = null;
	}

	@Test
	public void canAddBoat() throws RowBuddyException {
		boatManagement.addBoat(newBoat);
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddDeletedBoat() throws RowBuddyException {
		newBoat.setDeleted(true);

		boatManagement.addBoat(newBoat);
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddInvalidBoat() throws RowBuddyException {
		Boat b1 = new Boat();
		boatManagement.addBoat(b1);
	}

	@Test(expected = RowBuddyException.class)
	public void cannotAddAlreadyExistingBoat() throws RowBuddyException {
		boatManagement.addBoat(existingBoat);
	}

	@Test
	public void canUpdateBoat() throws FinderException, RowBuddyException {
		existingBoat.setName("new name");
		Boat updatedBoat = boatManagement.updateBoat(existingBoat);
		assertThat(updatedBoat.getName(), is(existingBoat.getName()));

		updatedBoat = boatManagement.getBoat(existingBoat.getId());
		assertThat(updatedBoat.getName(), is(existingBoat.getName()));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotUpdateNonExistingBoat() throws RowBuddyException,
			RowBuddyException {
		boatManagement.updateBoat(newBoat);
	}

	@Test(expected = RowBuddyException.class)
	public void cannotUpdateDeletedBoat() throws RowBuddyException,
			RowBuddyException {
		deletedBoat.setDeleted(false);
		boatManagement.updateBoat(deletedBoat);
	}

	@Test
	public void canDeleteBoat() throws RowBuddyException {
		boatManagement.deleteBoat(existingBoat.getId());
		Boat dbBoat = boatManagement.getBoat(existingBoat.getId());
		assertThat(dbBoat.isDeleted(), is(true));
	}

	@Test(expected = RowBuddyException.class)
	public void cannotDeleteAlreadyDeletedBoat() throws RowBuddyException {
		boatManagement.deleteBoat(deletedBoat.getId());
	}

	@Test
	public void canGetBoat() throws RowBuddyException {
		Boat boat = boatManagement.getBoat(existingBoat.getId());
		assertNotNull(boat);
	}

	@Test
	public void canGetDeletedBoat() throws RowBuddyException {
		Boat boat = boatManagement.getBoat(deletedBoat.getId());
		assertNotNull(boat);
	}

	@Test
	public void canGetOverview() {
		Collection<Boat> boats = boatManagement.getBoatOverview();
		boolean containsExistingBoat = false;
		for (Boat boat : boats) {
			if (boat.getId() == existingBoat.getId()) {
				containsExistingBoat = true;
			}
			if (boat.getId() == deletedBoat.getId()) {
				fail("deleted boat found");
			}
		}
		if (!containsExistingBoat) {
			fail("existing boat not found");
		}
	}

	@Test
	public void canAddDamage() throws RowBuddyException {
		// given
		BoatDamage damage = new BoatDamage();
		damage.setBoat(existingBoat);
		damage.setDamageDescription("Left side broken");
		damage.setLogDate(new Date(System.currentTimeMillis()));
		int sizeBefore = boatManagement.getBoat(existingBoat.getId()).getBoatDamages().size(); 
		
		// when 
		boatManagement.addDamage(damage, db.getMembers().get(0));
		
		// then
		int sizeAfter = boatManagement.getBoat(existingBoat.getId()).getBoatDamages().size();
		assertEquals(1, sizeAfter - sizeBefore);
	}
	
	@Test
	public void canGetAllDamages() throws RowBuddyException{
		// given
		BoatDamage damage = new BoatDamage();
		damage.setBoat(existingBoat);
		damage.setDamageDescription("Right side broken");
		damage.setLogDate(new Date(System.currentTimeMillis()));
		damage.setFixed(true);
		boatManagement.addDamage(damage, db.getMembers().get(0));
		
		// when
		List<BoatDamage> damages = boatManagement.getDamages(ListType.ALL);
		
		// then
		boolean found = false;
		for(BoatDamage dmg : damages) {
			if(!dmg.isFixed()) {
				found = true;
			}
		}
		assertEquals(false, found);
	}
	
	@Test
	public void canGetOpenDamages() throws RowBuddyException{
		// given
		BoatDamage damage = new BoatDamage();
		damage.setBoat(existingBoat);
		damage.setDamageDescription("Right side broken");
		damage.setLogDate(new Date(System.currentTimeMillis()));
		damage.setFixed(true);
		boatManagement.addDamage(damage, db.getMembers().get(0));
		
		BoatDamage damage2 = new BoatDamage();
		damage2.setId(null);
		damage2.setBoat(existingBoat);
		damage2.setDamageDescription("Right side broken");
		damage2.setLogDate(new Date(System.currentTimeMillis()));
		damage2.setFixed(false);
		boatManagement.addDamage(damage2, db.getMembers().get(0));
		
		// when
		List<BoatDamage> damages = boatManagement.getDamages(ListType.OPEN);
		
		// then
		for(BoatDamage dmg : damages) {
			assertEquals(false, dmg.isFixed());
		}
	}
}
