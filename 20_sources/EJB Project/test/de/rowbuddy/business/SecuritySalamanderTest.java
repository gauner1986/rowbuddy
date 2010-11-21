package de.rowbuddy.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.Member;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbExceptionHandler;
import de.rowbuddy.util.EjbTestBase;

public class SecuritySalamanderTest extends EjbTestBase {

	private Member attachedMember;
	private Member member;
	private RowBuddyFacade rowBuddyFacade;
	private EjbExceptionHandler ejbHandler;

	@Before
	public void setup() {
		ejbHandler = new EjbExceptionHandler();
		member = new Member();
		member.setEmail("bla@bla.de");
		member.setPassword("secret");
		attachedMember = (Member) em.persist(member);
		rowBuddyFacade = Ejb.lookUp(RowBuddyFacade.class, RowBuddyFacade.class);
	}

	@After
	public void tearDown() {
		removeEntity(Member.class, attachedMember.getId());
		member = null;
		attachedMember = null;
		ejbHandler = null;
	}
	
	private <T> void removeEntity(Class<T> type, Object key){
		T obj = em.find(type, key);
		if (obj != null){
			em.remove(type, key);
		}	
	}
	
	@Test
	public void canLogIn(){
		assertTrue(rowBuddyFacade.login(member));
		assertTrue(rowBuddyFacade.isLoggedIn());
	}
	
	@Test
	public void canNotLogIn(){
		Member falseMember = new Member();
		falseMember.setEmail("basfa@asfsa.de");
		falseMember.setPassword("wrongpass");
		assertFalse(rowBuddyFacade.login(falseMember));
		assertFalse(rowBuddyFacade.isLoggedIn());
		falseMember.setEmail("bla@bla.de");
		assertFalse(rowBuddyFacade.login(falseMember));
		assertFalse(rowBuddyFacade.isLoggedIn());
		
		assertFalse(rowBuddyFacade.login(new Member()));
	}

	@Test(expected = NotLoggedInException.class)
	public void cannotUseGetBoatManagement() throws Exception {
		ejbHandler.execute(new Runnable() {

			@Override
			public void run() {
				rowBuddyFacade.getBoatManagement();
			}

		});
	}
	
	@Test(expected = NotLoggedInException.class)
	public void canLogOut() throws Exception{
		assertTrue(rowBuddyFacade.login(member));
		rowBuddyFacade.logout();
		assertFalse(rowBuddyFacade.isLoggedIn());
		
		
		ejbHandler.execute(new Runnable() {

			@Override
			public void run() {
				rowBuddyFacade.getBoatManagement();
			}

		});
	}
	
	@Test
	public void canUseGetBoatManagement() {
		assertTrue(rowBuddyFacade.login(member));
		assertNotNull(rowBuddyFacade.getBoatManagement());
	}
}
