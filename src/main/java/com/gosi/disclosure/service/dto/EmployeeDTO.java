package com.gosi.disclosure.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {
	@JsonProperty("DisplayName")
	private String DisplayName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("title")
	private String title;
	@JsonProperty("telephoneNumber")
	private String telephoneNumber;
	@JsonProperty("localPhone")
	private String localPhone;
	@JsonProperty("department")
	private String department;
	@JsonProperty("physicalDeliveryOfficeName")
	private String physicalDeliveryOfficeName;
	@JsonProperty("distinguishedName")
	private String distinguishedName;
	@JsonProperty("username")
	private String username;

	public String getDisplayName() {
		return DisplayName;
	}

	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getLocalPhone() {
		return localPhone;
	}

	public void setLocalPhone(String localPhone) {
		this.localPhone = localPhone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhysicalDeliveryOfficeName() {
		return physicalDeliveryOfficeName;
	}

	public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
		this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
	}

	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}