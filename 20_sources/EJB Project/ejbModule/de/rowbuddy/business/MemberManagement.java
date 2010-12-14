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
			throw new RowBuddyException(
					"Member is not allowed to have a predefined id");
		}
		
		if(addMember.getRoles().size() == 0){
			try {
				String query = "SELECT r FROM Role r WHERE (r.roleName = :role)";
				TypedQuery<Role> q = em.createQuery(query, Role.class);
				q.setParameter("role", RoleName.MEMBER);
				Role r = q.getSingleResult();
				LinkedList<Role> roles = new LinkedList<Role>();
				roles.add(r);
				addMember.setRoles(roles);
			} catch (Exception e){
				logger.info("A Member default role could not be set up, omitting (probably no Roles in the database)",e);
			}
		}
		
		checkEmailExists(addMember.getEmail(), new Long(-1));

		em.persist(addMember);
		return addMember;
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
	
	public Member getMember(String email){
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

	public Member updateMember(Member member) throws RowBuddyException {
		if (member == null) {
			throw new RowBuddyException("member must not be null");
		}

		// ensure that member exists
		getMember(member.getId());

		em.merge(member);

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
	
	public void addRole(Role role){
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
