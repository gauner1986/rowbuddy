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
import de.rowbuddy.exceptions.RowBuddyException;

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

	public Member addMember(Member addMember) throws RowBuddyException {

		if (addMember.getId() != null) {
			throw new RowBuddyException("Mitglied darf keine Id haben");
		}

		if (addMember.getRoles().size() == 0) {
			try {
				String query = "SELECT r FROM Role r WHERE (r.roleName = :role)";
				TypedQuery<Role> q = em.createQuery(query, Role.class);
				q.setParameter("role", RoleName.MEMBER);
				Role r = q.getSingleResult();
				LinkedList<Role> roles = new LinkedList<Role>();
				roles.add(r);
				addMember.setRoles(roles);
			} catch (Exception e) {
				logger.info(
						"A Member default role could not be set up, omitting (probably no Roles in the database)",
						e);
			}
		}

		checkEmailExists(addMember.getEmail(), new Long(-1));

		em.persist(addMember);
		return addMember;
	}

	public Member getMember(Long id) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("Id darf nicht null sein");
		}
		Member member = em.find(Member.class, id);
		if (member == null) {
			throw new RowBuddyException("Das Mitglied existiert nicht");
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
					"Die Email-Adresse wird bereits verwendet");
		}
	}

	public Member updateMember(Member member) throws RowBuddyException {
		if (member == null) {
			throw new RowBuddyException("Mitglied darf nicht null sein");
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

	public void importMembers(List<Member> members) throws RowBuddyException {
		int i = 0;
		int count = members.size();
		logger.info("Import Members: Start");

		for (Member member : members) {
			i++;
			logger.info(String.format("Import Member: %d/%d", i, count));
			importMember(member);
		}
		logger.info("Import Members: Finished");
	}

	public void addRole(Role role) {
		em.persist(role);
	}

	private Member importMember(Member member) throws RowBuddyException {
		Member dbMember = getMemberByMemberId(member.getMemberId());
		if (dbMember == null) {
			logger.info("Import Members: Creating new Member");
			return addMember(member);
		}
		member.setId(dbMember.getId());
		logger.info(String.format(
				"Import Members: Updating existing Member (%d)",
				dbMember.getId()));
		return updateMember(member);
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
}
