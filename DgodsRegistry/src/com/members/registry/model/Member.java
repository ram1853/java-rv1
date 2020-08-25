package com.members.registry.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import com.members.registry.formValidations.ValidateContact;
import com.members.registry.formValidations.ValidatePincode;


/**
 * 
 * Spring does not treat Entities as beans and it is not a 
 * good idea to mark Entity classes as Spring components
 *
 */

@Entity
//@Table(name = "member") -> This table is in local db
@Table(name = "dgods_member") // -> This is in cloud db
public class Member implements Comparable<Member>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "blood_type")
	private String bloodType;

	@Email
	@Column(name = "email")
	private String email;
	
	@ValidateContact
	@Column(name = "contact")
	private String contact;
	
	@ValidateContact
	@Column(name = "emergency_contact")
	private String emergencyContact;

	@Column(name = "dob")
	private String dob;
	
	@Min(value=2012, message="must be equal or greater than 2012")  
	@Column(name = "year_joining_dgods")
	private int yearOfJoiningDgods;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "company")
	private String company;

	@Column(name = "dance_spec")
	private String danceSpecialization;

	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name="profile_picture")
	private byte[] content;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;
	
	@ValidatePincode
	@Column(name = "pin_code")
	private String pinCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getYearOfJoiningDgods() {
		return yearOfJoiningDgods;
	}

	public void setYearOfJoiningDgods(int yearOfJoiningDgods) {
		this.yearOfJoiningDgods = yearOfJoiningDgods;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDanceSpecialization() {
		return danceSpecialization;
	}

	public void setDanceSpecialization(String danceSpecialization) {
		this.danceSpecialization = danceSpecialization;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public int compareTo(Member member) {
		if(this.email.equals(member.getEmail())) {
			return 0;
		}
		return -1;
	}




}
