package de.rowbuddy.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.rowbuddy.entities.Member;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Session Bean implementation class MemberManagement
 */
@Stateless
@LocalBean
public class MemberManagement {

	@PersistenceContext
	EntityManager em;

	public MemberManagement() {
	}

	public Member addMember(Member addMember) throws RowBuddyException {

		if (addMember.getId() != null) {
			throw new RowBuddyException(
					"Member is not allowed to have a predefined id");
		}

		checkEmailExists(addMember.getEmail(), new Long(-1));

		em.persist(addMember);
		return addMember;
	}

	public Member getMember(Long id) throws RowBuddyException {
		if (id == null) {
			throw new RowBuddyException("id must not be null");
		}

		return em.getReference(Member.class, id);
	}

	private void checkEmailExists(String email, Long exceptId)
			throws RowBuddyException {
		String query = "SELECT m FROM Member m WHERE (m.email=:member) ";
		if (exceptId != -1) {
			query += "m.id!=:id";
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
}
