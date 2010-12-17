package de.rowbuddy.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.boundary.RowBuddyFacade;
import de.rowbuddy.entities.Boat;
import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbExceptionHandler;
import de.rowbuddy.util.EjbTestBase;
import de.rowbuddy.util.EncryptionUtility;

public class PermissionInterceptorTest extends EjbTestBase {

	private Member attachedAdminMember;
	private Member adminMember;
	private Member userMember;
	private Member attachedUserMember;
	private RowBuddyFacade rowBuddyFacade;
	private EjbExceptionHandler ejbHandler;
	private Boat existingBoat;

	@Before
	public void setup() {
		ejbHandler = new EjbExceptionHandler();
		adminMember = createTestMember("bla@bla.de2", "secret", new RoleName[] {
				RoleName.ADMIN, RoleName.MEMBER }, "ID1");
		userMember = createTestMember("user@bla.de", "secret",
				new RoleName[] { RoleName.MEMBER }, "ID2");

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

		attachedAdminMember = (Member) em.persist(adminMember);
		attachedUserMember = (Member) em.persist(userMember);
		rowBuddyFacade = Ejb.lookUp(RowBuddyFacade.class, RowBuddyFacade.class);
	}

	@After
	public void tearDown() {
		removeEntity(Member.class, attachedAdminMember.getId());
		removeEntity(Member.class, attachedUserMember.getId());
		removeEntity(Member.class, existingBoat.getId());
		existingBoat = null;
		adminMember = null;
		userMember = null;
		attachedAdminMember = null;
		attachedUserMember = null;
		ejbHandler = null;
	}

	private <T> void removeEntity(Class<T> type, Object key) {
		T obj = em.find(type, key);
		if (obj != null) {
			em.remove(type, key);
		}
	}

	private Member createTestMember(String username, String password,
			RoleName[] roleNames, String mId) {
		Member member = new Member();
		try {
			member.setEmail(username);
		} catch (RowBuddyException e1) {
			e1.printStackTrace();
			fail();
		}
		member.setPasswordHash(EncryptionUtility
				.encryptStringWithSHA2(password));
		ArrayList<Role> roles = new ArrayList<Role>();
		for (RoleName roleName : roleNames) {
			Role role = new Role();
			try {
				role.setName(roleName);
			} catch (RowBuddyException e) {
				e.printStackTrace();
				fail();
			}
			roles.add(role);
		}
		member.setRoles(roles);
		try {
			member.setMemberId(mId);
		} catch (RowBuddyException e) {
			e.printStackTrace();
			fail();
		}
		return member;
	}

	@Test
	public void canLogin() {
		try {
			rowBuddyFacade.login(adminMember.getEmail(), "secret");
		} catch (NotLoggedInException nlie) {
			fail("Member can't login");
		}
		assertTrue(rowBuddyFacade.isLoggedIn());
	}

	@Test
	public void cannotLoginWithWrongUserAndPass() {
		Member falseMember = createTestMember("basfa@asfsa.de", "wrongpass",
				new RoleName[] {}, "ID3");
		try {
			rowBuddyFacade.login(falseMember.getEmail(),
					falseMember.getPasswordHash());
			fail("Member can login anyway, where he shouldn't.");
		} catch (NotLoggedInException nlie) {
		}
		assertFalse(rowBuddyFacade.isLoggedIn());
	}

	@Test
	public void cannotLoginWithWrongPass() {
		Member falseMember = createTestMember("bla@bla2.de", "wrongpass",
				new RoleName[] {}, "ID4");
		try {
			rowBuddyFacade.login(falseMember.getEmail(),
					falseMember.getPasswordHash());
			fail("Member can login anyway, where he shouldn't.");
		} catch (NotLoggedInException e) {
		}
		assertFalse(rowBuddyFacade.isLoggedIn());
	}

	@Test
	public void cannotLoginWithNoCredentials() {
		try {
			rowBuddyFacade.login("", "");
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
		rowBuddyFacade.login(adminMember.getEmail(),
				adminMember.getPasswordHash());
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
	public void canUseGetBoatManagementAsUser() {
		testGetBoatManagementAs(userMember);
	}

	@Test
	public void canUseGetBoatManagementAsAdmin() {
		testGetBoatManagementAs(adminMember);
	}

	private void testGetBoatManagementAs(Member member) {
		try {
			rowBuddyFacade.login(member.getEmail(), "secret");
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

	@Test(expected = NotLoggedInException.class)
	public void cannotUpdateBoatAsUser() throws Exception {
		rowBuddyFacade.login(userMember.getEmail(),
				userMember.getPasswordHash());
		assertTrue(rowBuddyFacade.isLoggedIn());
		rowBuddyFacade.updateBoat(new Boat());
	}

	@Test
	public void canUpdateBoatAsAdmin() {
		try {
			rowBuddyFacade.login(adminMember.getEmail(), "secret");
			assertTrue(rowBuddyFacade.isLoggedIn());
		} catch (NotLoggedInException e) {
			e.printStackTrace();
			fail();
		}
		try {
			rowBuddyFacade.updateBoat(existingBoat);
		} catch (RowBuddyException e) {
			e.printStackTrace();
			fail();
		}
	}
}
