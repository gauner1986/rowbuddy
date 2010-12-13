package de.rowbuddy.business;

import static org.hamcrest.matchers.IsCollectionOf.equalsList;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class MemberManagementTest extends EjbTestBase {

	private MemberManagement memberMgmt;
	private Member member1;
	private Member member2;
	private Member admin1;
	private Member admin2;

	@Before
	public void setup() throws RowBuddyException {
		member1 = new Member();
		member1.setGivenname("Member1");
		member1.setSurname("Schmitz");
		member1.setEmail("member1@schmitz.de");
		member1.setPassword("member1");
		member1.setBirthdate(new Date(System.currentTimeMillis()));
		member1.setMemberId("XDSADSF-221");

		member2 = new Member();
		member2.setGivenname("Member2");
		member2.setSurname("Schmitz");
		member2.setEmail("member2@schmitz.de");
		member2.setPassword("member2");
		member2.setBirthdate(new Date(System.currentTimeMillis()));
		member2.setMemberId("XDSADSF-222");

		memberMgmt = Ejb.lookUp(MemberManagement.class, MemberManagement.class);
	}

	@After
	public void tearDown() {
		member1 = null;
		member2 = null;
	}

	@Test
	public void canAddMultipleMembers() throws RowBuddyException {
		Member m1 = memberMgmt.addMember(member1);
		Member m2 = memberMgmt.addMember(member2);
		
		for(Role r : m2.getRoles()){
			System.out.println(r.toString());
		}
		assertThat(m1.getRoles(), equalsList(m2.getRoles()));
	}

}
