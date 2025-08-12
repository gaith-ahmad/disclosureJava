package com.gosi.disclosure.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagerInfoDTO {
	@JsonProperty("Email_direct_manager")
	private String Email_direct_manager;
	@JsonProperty("Name_direct_manager")
	private String Name_direct_manager;
	@JsonProperty("Job_number_direct_manager")
	private String Job_number_direct_manager;
	@JsonProperty("Job_title_direct_manager")
	private String Job_title_direct_manager;
	@JsonProperty("EXT_direct_manager")
	private String EXT_direct_manager;

	public String getEmail_direct_manager() {
		return Email_direct_manager;
	}

	public void setEmail_direct_manager(String email_direct_manager) {
		Email_direct_manager = email_direct_manager;
	}

	public String getName_direct_manager() {
		return Name_direct_manager;
	}

	public void setName_direct_manager(String name_direct_manager) {
		Name_direct_manager = name_direct_manager;
	}

	public String getJob_number_direct_manager() {
		return Job_number_direct_manager;
	}

	public void setJob_number_direct_manager(String job_number_direct_manager) {
		Job_number_direct_manager = job_number_direct_manager;
	}

	public String getJob_title_direct_manager() {
		return Job_title_direct_manager;
	}

	public void setJob_title_direct_manager(String job_title_direct_manager) {
		Job_title_direct_manager = job_title_direct_manager;
	}

	public String getEXT_direct_manager() {
		return EXT_direct_manager;
	}

	public void setEXT_direct_manager(String eXT_direct_manager) {
		EXT_direct_manager = eXT_direct_manager;
	}

}