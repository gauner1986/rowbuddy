package de.rowbuddy.business;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import de.rowbuddy.entities.Member;

public class MemberReader {

	private final InputStream is;
	private final List<Member> members = new LinkedList<Member>();

	public MemberReader(InputStream is) throws IOException {
		this.is = is;

		readMembers();
	}

	private void readMembers() throws IOException {
		DataInputStream in = new DataInputStream(is);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String strLine;

		// skip first line
		br.readLine();

		int i = 0;
		while ((strLine = br.readLine()) != null) {
			i++;

			StringTokenizer tok = new StringTokenizer(strLine, ";");
			if (tok.countTokens() != 7) {
				throw new IllegalArgumentException(String.format(
						"error on line %d", i));
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

				members.add(m);
			} catch (Exception ex) {
				throw new IllegalArgumentException(String.format(
						"error on line %d", i), ex);
			}
		}
		in.close();
	}

	public List<Member> getMembers() {
		return members;
	}
}
