package com.gosi.disclosure.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoDTO {

	@JsonProperty("LoginName")
	private String LoginName;
	@JsonProperty("FullName")
	private String FullName;
	@JsonProperty("Email")
	private String Email;
	@JsonProperty("key")
	private Integer key;
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Job_title")
	private String Job_title;
	@JsonProperty("Job_number")
	private String Job_number;
	@JsonProperty("EXT")
	private String EXT;
	@JsonProperty("Public_Administration")
	private String Public_Administration;
	@JsonProperty("Administration")
	private String Administration;
	@JsonProperty("managerinfo")
	private ManagerInfoDTO managerinfo;
	@JsonProperty("Relative_relationship")
	private String Relative_relationship;
	@JsonProperty("Office")
	private String Office;

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}
	@JsonProperty("Email")
	public String getEmail() {
		return Email;
	}
	@JsonProperty("Email")
	public void setEmail(String email) {
		Email = email;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getJob_title() {
		return Job_title;
	}

	public void setJob_title(String job_title) {
		Job_title = job_title;
	}

	public String getJob_number() {
		return Job_number;
	}

	public void setJob_number(String job_number) {
		Job_number = job_number;
	}

	public String getEXT() {
		return EXT;
	}

	public void setEXT(String eXT) {
		EXT = eXT;
	}
	
	public String getPublic_Administration() {
		return Public_Administration;
	}

	public void setPublic_Administration(String public_Administration) {
		Public_Administration = public_Administration;
	}
	@JsonProperty("Administration")
	public String getAdministration() {
		return Administration;
	}
	@JsonProperty("Administration")
	public void setAdministration(String administration) {
		Administration = administration;
	}

	public ManagerInfoDTO getManagerinfo() {
		return managerinfo;
	}

	public void setManagerinfo(ManagerInfoDTO managerinfo) {
		this.managerinfo = managerinfo;
	}

	public String getRelative_relationship() {
		return Relative_relationship;
	}

	public void setRelative_relationship(String relative_relationship) {
		Relative_relationship = relative_relationship;
	}

	public String getOffice() {
		return Office;
	}

	public void setOffice(String office) {
		Office = office;
	}

}
