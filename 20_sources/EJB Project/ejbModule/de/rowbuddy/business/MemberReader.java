package de.rowbuddy.business;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import de.rowbuddy.entities.Member;

public class MemberReader {

	private static final String expectedHeader = "ID,Nachname,Name,Adresse,PLZ,Stadt,Telefon,Handy,Email";
	private static final int NUMBER_OF_HEADER_FIELDS = 9;
	private final BufferedReader reader;
	private int lineNumber = 0;

	public MemberReader(InputStream is) throws IOException {
		DataInputStream in = new DataInputStream(is);
		reader = new BufferedReader(new InputStreamReader(in));

		verifyHeader();
	}

	public void verifyHeader() throws IOException {
		String header = reader.readLine();
		if (!header.equals(expectedHeader)) {
			throw new IOException(String.format(
					"Invalid header format. Expected: %s", expectedHeader));
		}
	}

	public Member readMember() throws IOException {

		String strLine = reader.readLine();
		if (strLine == null) {
			return null;
		}
		lineNumber++;

		StringTokenizer tok = new StringTokenizer(strLine, ",");
		if (tok.countTokens() != NUMBER_OF_HEADER_FIELDS) {
			throw new IOException(String.format(
					"Invalid number of tokens on line %d", lineNumber));
		}

		try {
			Member m = new Member();
			m.setMemberId(getString(tok.nextToken()));
			m.setSurname(getString(tok.nextToken()));
			m.setGivenname(getString(tok.nextToken()));
			m.setStreet(getString(tok.nextToken()));
			m.setZipCode(getString(tok.nextToken()));
			m.setCity(getString(tok.nextToken()));
			m.setPhone(getString(tok.nextToken()));
			m.setMobilePhone(getString(tok.nextToken()));
			m.setEmail(getString(tok.nextToken()));

			return m;
		} catch (Exception ex) {
			throw new IOException(String.format(
					"Invalid member format on line %d", lineNumber), ex);
		}
	}

	private String getString(String s) {
		return s.replace("\"", "");
	}
}
