package de.rowbuddy.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.exceptions.RowBuddyException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RoleTest {

	Role role1;
	
	@Before
	public void setup(){
		role1 = new Role();
	}
	
	@Test
	public void canCreateRole(){
		assertNull(role1.getId());
		assertThat(role1.getName(), is(""));
	}
	
	@Test(expected=NullPointerException.class)
	public void cannotSetNullName() throws RowBuddyException{
		role1.setName(null);
	}


}
