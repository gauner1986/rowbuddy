package de.rowbuddy.business;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sun.istack.logging.Logger;

import de.rowbuddy.entities.Member;
import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.NotLoggedInException;
import de.rowbuddy.exceptions.RowBuddyException;
import de.rowbuddy.util.EncryptionUtility;

/**
 * Session Bean implementation class MemberManagement
 */
@Stateless
@LocalBean
public class MemberManagement {

	private static Logger logger = Logger.getLogger(MemberManagement.class);

	@PersistenceContext
	EntityManager em;

	public MemberManagement() {
	}

	public void setupRoles() {
		try {
			logger.info("Setting up Roles");
			Role memberRole = getRoleForName(RoleName.MEMBER);
			if (memberRole == null) {
				memberRole = new Role();
				memberRole.setName(RoleName.MEMBER);
				em.persist(memberRole);
				logger.info("Role MEMBER angelegt");
			}
			Role adminRole = getRoleForName(RoleName.ADMIN);
			if (adminRole == null) {
				adminRole = new Role();
				adminRole.setName(RoleName.ADMIN);
				em.persist(adminRole);
				logger.info("Role ADMIN angelegt");
			}
		} catch (RowBuddyException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Adds a member to the system
	 * 
	 * @param addMember
	 *            the member which should be added
	 * @param roles
	 *            Roles to select
	 * @return the new member
	 * @throws RowBuddyException
	 */
	public Member addMember(Member addMember, RoleName... roles)
			throws RowBuddyException {

		if (addMember.getId() != null) {
			throw new RowBuddyException(
					"Member is not allowed to have a predefined id");
		}

		if (addMember.getRoles().size() != 0) {
			throw new RowBuddyException("Rollen können so nicht gesetzt werden");
		}

		if (roles.length == 0) {
			throw new RowBuddyException(
					"Es muss zumindestens eine Rolle angegeben werden");
		}

		List<Role> roleList = new LinkedList<Role>();
		for (RoleName role : roles) {
			Role r = getRoleForName(role);
			if (r == null) {
				throw new RowBuddyException("Die Rolle existiert nicht");
			}
			roleList.add(r);
		}

		addMember.setRoles(roleList);

		checkEmailExists(addMember.getEmail(), new Long(-1));

		em.persist(addMember);
		return addMember;
	}

	private Role getRoleForName(RoleName role) {
		try {
			String query = "SELECT r FROM Role r WHERE (r.roleName = :role)";
			TypedQuery<Role> q = em.createQuery(query, Role.class);
			q.setParameter("role", role);
			return q.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Member getMember(Long id) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("id must not be null");
		}
		Member member = em.find(Member.class, id);
		if (member == null) {
			throw new RowBuddyException("member was not found");
		}
		return member;
	}

	public Member getMember(String email) {
		String query = "SELECT m FROM Member m WHERE (m.email=:member) ";

		TypedQuery<Member> q = em.createQuery(query, Member.class);
		q.setParameter("member", email);
		Member m = q.getSingleResult();
		return m;
	}

	private void checkEmailExists(String email, Long exceptId)
			throws RowBuddyException {
		String query = "SELECT m FROM Member m WHERE (m.email=:member) ";
		if (exceptId != -1) {
			query += " AND (NOT(m.id = :id))";
		}

		TypedQuery<Member> q = em.createQuery(query, Member.class);
		q.setParameter("member", email);
		if (exceptId != -1) {
			q.setParameter("id", exceptId);
		}
		List<Member> sameEmailAsExistingMember = q.getResultList();
		if (sameEmailAsExistingMember.size() > 0) {
			throw new RowBuddyException(
					"A user with this emailadress already exists");
		}
	}

	public Member updateMember(Member member) throws RowBuddyException {
		if (member == null) {
			throw new RowBuddyException("member must not be null");
		}

		// ensure that member exists
		Member dbMember = getMember(member.getId());

		// check that email is not used for other user
		checkEmailExists(member.getEmail(), member.getId());

		// set member properties
		dbMember.setGivenname(member.getGivenname());
		dbMember.setSurname(member.getSurname());
		dbMember.setEmail(member.getEmail());
		dbMember.setStreet(member.getStreet());
		dbMember.setZipCode(member.getZipCode());
		dbMember.setCity(member.getCity());
		dbMember.setMemberId(member.getMemberId());

		return member;
	}

	public void importMembers(List<Member> members, Member currentMember)
			throws RowBuddyException {
		int i = 0;
		int count = members.size();
		logger.info("Import Members: Start");

		for (Member member : members) {
			i++;
			logger.info(String.format("Import Member: %d/%d", i, count));
			importMember(member, currentMember);
		}
		logger.info("Import Members: Finished");
	}

	public List<Member> getMembers() {
		TypedQuery<Member> q = em.createQuery("SELECT m FROM Member m",
				Member.class);
		return q.getResultList();
	}

	private Member importMember(Member member, Member currentMember)
			throws RowBuddyException {
		Member dbMember = getMemberByMemberId(member.getMemberId());

		Member importedMember = null;
		if (dbMember == null) {
			logger.info("Import Members: Creating new Member");
			importedMember = addMember(member, RoleName.MEMBER);
		} else {
			member.setId(dbMember.getId());
			logger.info(String.format(
					"Import Members: Updating existing Member (%d)",
					dbMember.getId()));
			importedMember = updateMember(member);
		}
		setPassword(importedMember.getId(), currentMember, "test");
		return importedMember;
	}

	private Member getMemberByMemberId(String memberId) {
		TypedQuery<Member> q = em.createQuery(
				"SELECT m FROM Member m WHERE m.memberId = :memberId",
				Member.class);
		q.setParameter("memberId", memberId);
		try {
			return q.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	private Member getMemberByEmail(String email) {
		TypedQuery<Member> q = em.createQuery(
				"SELECT m FROM Member m WHERE m.email = :email", Member.class);
		q.setParameter("email", email);
		try {
			return q.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public void setPassword(Long id, Member currentUser, String password)
			throws RowBuddyException {
		Member dbMember = getMember(id);

		Member currentMember = currentUser;
		if (em.contains(currentUser)) {
			currentMember = em.getReference(Member.class, currentUser.getId());
		}
		if (!currentMember.equals(dbMember)) {
			if (!currentMember.isInRole(RoleName.ADMIN)) {
				throw new RowBuddyException(
						"Dies darf nur der Member selbst oder ein Administrator");
			}
		}

		String passwordHash = EncryptionUtility.encryptStringWithSHA2(password);
		dbMember.setPasswordHash(passwordHash);
	}

	public Member checkLogin(String email, String password)
			throws NotLoggedInException {

		Member registeredMember = null;
		try {
			registeredMember = getMemberByEmail(email);
			if (registeredMember == null) {
				throw new Exception("Member wurde nicht gefunden");
			}

			String passwordHash = getPasswordHash(password);

			if (!registeredMember.getPasswordHash().equals(passwordHash)) {
				throw new Exception("Passwort stimmt nicht überein");
			}
		} catch (Exception ex) {
			throw new NotLoggedInException(
					"Ihr Passwort und/oder der Benutzername sind inkorrekt.");
		}
		return registeredMember;
	}

	private String getPasswordHash(String password) {
		return EncryptionUtility.encryptStringWithSHA2(password);
	}
}
