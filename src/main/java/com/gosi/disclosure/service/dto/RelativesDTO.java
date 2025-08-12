package com.gosi.disclosure.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelativesDTO {
	@JsonProperty("Name_discloser")
	private String Name_discloser;
	@JsonProperty("Name_relative")
	private String Name_relative;
	@JsonProperty("Job_title_relative")
	private String Job_title_relative;
	@JsonProperty("Relative_job_number")
	private String Relative_job_number;
	@JsonProperty("Email_relative")
	private String Email_relative;
	@JsonProperty("Relative_extension_number")
	private String Relative_extension_number;
	@JsonProperty("Relative_relationship")
	private String Relative_relationship; // Optional
	@JsonProperty("General_Administration_relative")
	private String General_Administration_relative;
	@JsonProperty("Administration_relative")
	private String Administration_relative;

	public String getName_discloser() {
		return Name_discloser;
	}

	public void setName_discloser(String name_discloser) {
		Name_discloser = name_discloser;
	}

	public String getName_relative() {
		return Name_relative;
	}

	public void setName_relative(String name_relative) {
		Name_relative = name_relative;
	}

	public String getJob_title_relative() {
		return Job_title_relative;
	}

	public void setJob_title_relative(String job_title_relative) {
		Job_title_relative = job_title_relative;
	}

	public String getRelative_job_number() {
		return Relative_job_number;
	}

	public void setRelative_job_number(String relative_job_number) {
		Relative_job_number = relative_job_number;
	}

	public String getEmail_relative() {
		return Email_relative;
	}

	public void setEmail_relative(String email_relative) {
		Email_relative = email_relative;
	}

	public String getRelative_extension_number() {
		return Relative_extension_number;
	}

	public void setRelative_extension_number(String relative_extension_number) {
		Relative_extension_number = relative_extension_number;
	}

	public String getRelative_relationship() {
		return Relative_relationship;
	}

	public void setRelative_relationship(String relative_relationship) {
		Relative_relationship = relative_relationship;
	}

	public String getGeneral_Administration_relative() {
		return General_Administration_relative;
	}

	public void setGeneral_Administration_relative(String general_Administration_relative) {
		General_Administration_relative = general_Administration_relative;
	}

	public String getAdministration_relative() {
		return Administration_relative;
	}

	public void setAdministration_relative(String administration_relative) {
		Administration_relative = administration_relative;
	}

}