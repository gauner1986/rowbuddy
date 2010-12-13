package de.rowbuddy.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import de.rowbuddy.entities.Role.RoleName;
import de.rowbuddy.exceptions.RowBuddyException;

/**
 * Entity implementation class for Entity: Member
 * 
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "memberId") })
public class Member implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	private String memberId = "";
	private String givenname = "";
	private String surname = "";
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	private String email = "";
	private String password = "";
	private boolean deleted = false;
	private String street = "";
	private String city = "";
	private String zipCode = "";
	@OneToMany
	private List<Trip> publishedTrips = new LinkedList<Trip>();
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Role> roles = new LinkedList<Role>();
	private static final long serialVersionUID = 1L;

	public Member() {
		if(roles.size()==0){
			Role r = new Role();
		try {
			r.setName(Role.RoleName.MEMBER);
		} catch (RowBuddyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			roles.add(r);
		}
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		if (id == null) {
			throw new NullPointerException("Id must not be null");
		}
		this.id = id;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) throws RowBuddyException {
		if (memberId == null) {
			throw new NullPointerException("Member ID must not be null");
		}
		if (memberId.isEmpty()) {
			throw new RowBuddyException("Member ID has to be set");
		}
		this.memberId = memberId;
	}

	public String getGivenname() {
		return this.givenname;
	}

	public void setGivenname(String givenname) throws RowBuddyException {
		if (givenname == null) {
			throw new NullPointerException("Givenname must not be null");
		}
		if (givenname.isEmpty()) {
			throw new RowBuddyException("Givenname has to be set");
		}
		this.givenname = givenname;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) throws RowBuddyException {
		if (surname == null) {
			throw new NullPointerException("Surname must not be null");
		}
		if (surname.isEmpty()) {
			throw new RowBuddyException("Surname has to be set");
		}
		this.surname = surname;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) throws RowBuddyException {
		if (birthdate.compareTo(new Date()) > 0) {
			throw new RowBuddyException(
					"birthday of the memeber has to be in the past");
		}
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) throws RowBuddyException {
		if (email == "a") {
			throw new RowBuddyException("email is not valid");
		}
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		// TODO encryption of password
		this.password = password;
	}

	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Trip> getPublishedTrips() {
		return publishedTrips;
	}

	public void setPublishedTrips(List<Trip> publishedTrips) {
		if (publishedTrips == null) {
			throw new NullPointerException("Published trips must not be null");
		}
		this.publishedTrips = publishedTrips;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		if (roles == null) {
			throw new NullPointerException("Roles must not be null");
		}
		this.roles = roles;
	}

	public boolean isInRole(RoleName roleName) {
		for (Role r : roles) {
			if (r.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public String getFullName() {
		return givenname + ", " + surname;
	}
	
//	public boolean isAdmin(){
//		for(Role r : roles){
//			if(r.getName().equals(Role.RoleName.ADMIN)){
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Member other = (Member) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
}
