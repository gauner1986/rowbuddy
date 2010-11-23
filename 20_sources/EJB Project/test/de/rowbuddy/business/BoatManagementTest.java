package de.rowbuddy.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import de.rowbuddy.business.dtos.BoatDTO;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.Member;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbExceptionHandler;
import de.rowbuddy.util.EjbTestBase;

public class BoatManagementTest extends EjbTestBase {

	private Boat existingBoat;
	private Boat newBoat;
	private Boat deletedBoat;
	private BoatManagement boatManagement;
	private Member loginMember;

	@Before
	public void setup() {
		
		existingBoat = new Boat();
		try {
			existingBoat.setName("TestBoat 1");
			existingBoat.setLocked(false);
			existingBoat.setNumberOfSeats(1);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		existingBoat.setDeleted(false);
		existingBoat.setCoxed(true);
		existingBoat = (Boat) em.persist(existingBoat);

		newBoat = new Boat();
		try {
			newBoat.setName("New boat");
			newBoat.setNumberOfSeats(10);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newBoat.setCoxed(false);
		newBoat.setDeleted(false);
		newBoat.setLocked(false);

		deletedBoat = new Boat();
		try {
			deletedBoat.setName("Deleted Boat");
			deletedBoat.setLocked(false);
			deletedBoat.setNumberOfSeats(12);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deletedBoat.setDeleted(true);
		deletedBoat.setCoxed(true);
		deletedBoat = (Boat) em.persist(deletedBoat);
		
		Member member = new Member();
		try {
			member.setEmail("test@test.de");
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		member.setPassword("pwd");
		loginMember = (Member)em.persist(member);
		
		boatManagement = Ejb.lookUp(BoatManagement.class, BoatManagement.class);
	}

	@After
	public void tearDown() {
		newBoat = null;
		
		removeEntity(Boat.class, existingBoat.getId());
		removeEntity(Boat.class, deletedBoat.getId());
		removeEntity(Member.class, loginMember.getId());
	
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
	public void canAddBoat() throws CreateException {
		boatManagement.addBoat(newBoat);
	}

	@Test(expected = CreateException.class)
	public void cannotAddBoatWithoutName() throws CreateException {
		try {
			newBoat.setName("");
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boatManagement.addBoat(newBoat);
	}

	@Test(expected = CreateException.class)
	public void cannotAddBoatWithoutNumberOfSeats() throws CreateException {
		try {
			newBoat.setNumberOfSeats(0);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boatManagement.addBoat(newBoat);
	}

	@Test(expected = CreateException.class)
	public void cannotAddDeletedBoat() throws CreateException {
		newBoat.setDeleted(true);

		boatManagement.addBoat(newBoat);
	}

	@Test(expected = CreateException.class)
	public void cannotAddAlreadyExistingBoat()throws CreateException {
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

	@Test(expected = FinderException.class)
	public void cannotUpdateNonExistingBoat() throws FinderException, RowBuddyException  {
		boatManagement.updateBoat(newBoat);
	}

	@Test(expected = FinderException.class)
	public void cannotUpdateDeletedBoat() throws FinderException, RowBuddyException {
		deletedBoat.setDeleted(false);
		boatManagement.updateBoat(deletedBoat);
	}

	@Test
	public void canDeleteBoat() throws RemoveException, FinderException {
		boatManagement.deleteBoat(existingBoat.getId());
		Boat dbBoat = boatManagement.getBoat(existingBoat.getId());
		assertThat(dbBoat.isDeleted(), is(true));
	}

	@Test(expected = RemoveException.class)
	public void cannotDeleteAlreadyDeletedBoat() throws RemoveException {
		boatManagement.deleteBoat(deletedBoat.getId());
	}
	
	@Test
	public void canGetBoat() throws FinderException{
		Boat boat = boatManagement.getBoat(existingBoat.getId());
		assertNotNull(boat);
	}
	
	@Test
	public void canGetDeletedBoat() throws FinderException{
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
