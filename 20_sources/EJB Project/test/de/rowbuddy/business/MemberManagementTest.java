package de.rowbuddy.business;

import static org.hamcrest.matchers.IsCollectionOf.equalsList;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class MemberManagementTest extends EjbTestBase {

	private MemberManagement memberMgmt;
	private Member member1;
	private Member member2;
	private Member admin1;
	private Member admin2;

	public MemberManagementTest() {
	}

	@Before
	public void setup() throws RowBuddyException {
		memberMgmt = Ejb.lookUp(MemberManagement.class, MemberManagement.class);
		memberMgmt.setupRoles();

		member1 = new Member();
		member1.setGivenname("Member1");
		member1.setSurname("Schmitz");
		member1.setEmail("member1@schmitz.de");
		member1.setPasswordHash("member1");
		member1.setBirthdate(new Date(System.currentTimeMillis()));
		member1.setMemberId("XDSADSF-221");

		member2 = new Member();
		member2.setGivenname("Member2");
		member2.setSurname("Schmitz");
		member2.setEmail("member2@schmitz.de");
		member2.setPasswordHash("member2");
		member2.setBirthdate(new Date(System.currentTimeMillis()));
		member2.setMemberId("XDSADSF-222");
	}

	@After
	public void tearDown() {
		memberMgmt = null;

		member1 = null;
		member2 = null;
	}

	@Test
	public void canAddMultipleMembers() throws RowBuddyException {
		Member m1 = memberMgmt.addMember(member1,
				new RoleName[] { RoleName.MEMBER });
		Member m2 = memberMgmt.addMember(member2,
				new RoleName[] { RoleName.MEMBER });

		for (Role r : m2.getRoles()) {
			System.out.println(r);
		}
		for (Role r : m1.getRoles()) {
			System.out.println(r);
		}
		assertThat(m1.getRoles(), equalsList(m2.getRoles()));
	}

}
