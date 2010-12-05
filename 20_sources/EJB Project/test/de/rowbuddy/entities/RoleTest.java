package de.rowbuddy.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.RowBuddyException;

public class RoleTest {

	Role role1;
	
	@Before
	public void setup(){
		role1 = new Role();
	}
	
	@Test
	public void canCreateRole(){
		assertNull(role1.getId());
		assertEquals(RoleName.MEMBER, role1.getName());
	}
	
	@Test(expected=NullPointerException.class)
	public void cannotSetNullName() throws RowBuddyException{
		role1.setName(null);
	}


}
