package de.rowbuddy.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

	private <T> void removeEntity(Class<T> type, Object key) {
		T obj = em.find(type, key);
		if (obj != null) {
			em.remove(type, key);
		}
	}

	@Test
	public void canLogin() {
		try {
			rowBuddyFacade.login(member);
		} catch (NotLoggedInException nlie) {
			fail("Member can't login");
		}
		assertTrue(rowBuddyFacade.isLoggedIn());
	}

	@Test
	public void cannotLoginWithWrongUserAndPass() {
		Member falseMember = new Member();
		falseMember.setEmail("basfa@asfsa.de");
		falseMember.setPassword("wrongpass");

		try {
			rowBuddyFacade.login(falseMember);
			fail("Member can login anyway, where he shouldn't.");
		} catch (NotLoggedInException nlie) {
		}
		assertFalse(rowBuddyFacade.isLoggedIn());
	}

	@Test
	public void cannotLoginWith() {
		Member falseMember = new Member();
		falseMember.setEmail("basfa@asfsa.de");
		falseMember.setEmail("bla@bla.de");

		try {
			rowBuddyFacade.login(falseMember);
			fail("Member can login anyway, where he shouldn't.");
		} catch (NotLoggedInException e) {
		}
		assertFalse(rowBuddyFacade.isLoggedIn());
	}

	@Test
	public void cannotLoginWithNoCredentials() {
		try {
			rowBuddyFacade.login(new Member());
			fail("Member can login anyway, where he shouldn't.");
		} catch (NotLoggedInException e) {
		}
		assertFalse(rowBuddyFacade.isLoggedIn());
	}

	@Test(expected = NotLoggedInException.class)
	public void cannotUseGetBoatOverview() throws Exception {
		ejbHandler.execute(new Runnable() {

			@Override
			public void run() {
				rowBuddyFacade.getBoatOverview();
			}

		});
	}

	@Test(expected = NotLoggedInException.class)
	public void canLogout() throws Exception {
		rowBuddyFacade.login(member);
		rowBuddyFacade.logout();
		assertFalse(rowBuddyFacade.isLoggedIn());
		ejbHandler.execute(new Runnable() {

			@Override
			public void run() {
				rowBuddyFacade.getBoatOverview();
			}

		});
		fail("Member can use methods anyway, where he shouldn't.");
		assertFalse(rowBuddyFacade.isLoggedIn());
	}

	@Test
	public void canUseGetBoatManagement() {
		try {
			rowBuddyFacade.login(member);
		} catch (NotLoggedInException e1) {
			fail("Member couldn't log in");
		}
		assertTrue(rowBuddyFacade.isLoggedIn());
		try {
			rowBuddyFacade.getBoatOverview();
		} catch (Exception e) {
			if (!(e instanceof NotLoggedInException)) {
				e.printStackTrace();
				fail("Should have thrown NotLoggedInException");
			} else {
				fail("Member can not use the method we want.");
			}
		}
	}
}
