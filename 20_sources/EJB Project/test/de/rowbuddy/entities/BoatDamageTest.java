package de.rowbuddy.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BoatDamageTest {

	private BoatDamage damage;

	@Before
	public void setUp(){
		damage = new BoatDamage();
	}
	
	@After
	public void tearDown(){
		damage = null;
	}
	
	@Test
	public void canCreateInstance(){
		assertNull(damage.getId());
		assertThat(damage.getAdditionalInformation(), is(""));
		assertThat(damage.isFixed(), is(false));
		assertNull(damage.getBoat());
		assertThat(damage.getDamageDescription(), is(""));
		assertNotNull(damage.getLogDate());
		assertNull(damage.getLogger());
	}
	
}
