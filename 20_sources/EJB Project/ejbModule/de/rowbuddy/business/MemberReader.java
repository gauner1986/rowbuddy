package de.rowbuddy.business;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import de.rowbuddy.entities.Member;

public class MemberReader {

	private final BufferedReader reader;
	private static final String expectedHeader = "NAME;VORNAME;EMAIL;STRASSE;PLZ;ORT;MEMBER-ID";
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

		StringTokenizer tok = new StringTokenizer(strLine, ";");
		if (tok.countTokens() != 7) {
			throw new IOException(String.format(
					"Invalid number of tokens on line %d", lineNumber));
		}

		try {
			Member m = new Member();
			m.setSurname(tok.nextToken());
			m.setGivenname(tok.nextToken());
			m.setEmail(tok.nextToken());
			m.setStreet(tok.nextToken());
			m.setZipCode(tok.nextToken());
			m.setCity(tok.nextToken());
			m.setMemberId(tok.nextToken());

			return m;
		} catch (Exception ex) {
			throw new IOException(String.format(
					"Invalid member format on line %d", lineNumber), ex);
		}
	}
}
