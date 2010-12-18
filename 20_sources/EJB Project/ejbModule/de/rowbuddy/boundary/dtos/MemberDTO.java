package de.rowbuddy.boundary.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import de.rowbuddy.entities.Role;
import de.rowbuddy.entities.Role.RoleName;

public class MemberDTO implements Serializable {

	private static final long serialVersionUID = 535672769853512601L;
	private Long id = null;
	private String memberId = "";
	private String givenname = "";
	private String surname = "";
	private Date birthdate;
	private String email = "";
	private boolean deleted = false;
	private String street = "";
	private String city = "";
	private String zipCode = "";
	private String phone = "";
	private String mobilePhone = "";
	private List<Role> roles = new LinkedList<Role>();

	public MemberDTO() {
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

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getGivenname() {
		return this.givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
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
		return givenname + " " + surname;
	}

	public String getAddress() {
		return street + ", " + zipCode + " " + city;
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
		MemberDTO other = (MemberDTO) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
