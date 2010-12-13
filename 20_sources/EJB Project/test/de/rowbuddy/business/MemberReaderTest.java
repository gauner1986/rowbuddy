package de.rowbuddy.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rowbuddy.entities.Member;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.Ejb;
import de.rowbuddy.util.EjbTestBase;

public class MemberReaderTest extends EjbTestBase {

	MemberManagement memberMgmt;

	@Before
	public void setup() {
		memberMgmt = Ejb.lookUp(MemberManagement.class, MemberManagement.class);
	}

	@After
	public void tearDown() {
		memberMgmt = null;
	}

	@Test
	public void canImportMembers() throws IOException, RowBuddyException {

		// given
		FileInputStream fis = new FileInputStream("test/import.csv");
		MemberReader reader = new MemberReader(fis);

		// when
		List<Member> members = reader.getMembers();
		memberMgmt.importMembers(members);
	}
}
