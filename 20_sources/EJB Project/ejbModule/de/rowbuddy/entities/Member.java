package de.rowbuddy.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import de.rowbuddy.exceptions.RowBuddyException;


/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity
public class Member implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
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
	private Collection<Trip> publishedTrips;
	@ManyToMany(cascade=CascadeType.ALL)
	private Collection<Role> roles;
	private static final long serialVersionUID = 1L;
	
	public Member() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) throws RowBuddyException {
		if(memberId.length() < 1 || memberId.equals("") || memberId == null) {
			throw new RowBuddyException("member ID has to be set");
		}
		this.memberId = memberId;
	}   
	public String getGivenname() {
		return this.givenname;
	}

	public void setGivenname(String givenname) throws RowBuddyException {
		if(givenname.length() < 1 || givenname.equals("") || givenname == null) {
			throw new RowBuddyException("givenname has to be set");
		}
		this.givenname = givenname;
	}   
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) throws RowBuddyException {
		if(surname.length() < 1 || surname.equals("") || surname == null) {
			throw new RowBuddyException("surname has to be set");
		}
		this.surname = surname;
	}   
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) throws RowBuddyException {
		if(birthdate.compareTo(new Date()) > 0) {
			throw new RowBuddyException("birthday of the memeber has to be in the past");
		}
		this.birthdate = birthdate;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) throws RowBuddyException {
		if(email == "a") {
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
	
	public Collection<Trip> getPublishedTrips() {
		return publishedTrips;
	}
	
	public void setPublishedTrips(Collection<Trip> publishedTrips) {
		this.publishedTrips = publishedTrips;
	}
	
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
	
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}	
   
}
