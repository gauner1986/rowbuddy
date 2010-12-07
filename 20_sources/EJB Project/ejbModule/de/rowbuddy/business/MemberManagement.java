package de.rowbuddy.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

		em.persist(addMember);
		return addMember;
	}

	public Member getMember(Long id) {
		return em.getReference(Member.class, id);
	}

}
