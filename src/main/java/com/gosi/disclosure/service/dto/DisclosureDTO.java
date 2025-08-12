package com.gosi.disclosure.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;

public class DisclosureDTO {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("Purpose_of_disclosure")
	private String Purpose_of_disclosure;
	@JsonProperty("CreationDate")
	private String CreationDate;
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
	@JsonProperty("Confirm")
	private String Confirm;
	@JsonProperty("Email")
	private String Email;
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
	@JsonProperty("relatives")
	private List<RelativesDTO> relatives;
	@JsonProperty("gift")
	private GiftDTO gift;
	@JsonProperty("conflictInterest")
	private ConflictInterestDTO conflictInterest;
	@JsonProperty("areThereRelatives")
	private boolean areThereRelatives;
	@JsonProperty("purposeOfDisclosure")
	private String purposeOfDisclosure;
	@JsonProperty("file")
	private String file;
	@JsonProperty("filename")
	private String filename;

	public String getPurpose_of_disclosure() {
		return Purpose_of_disclosure;
	}

	public void setPurpose_of_disclosure(String purpose_of_disclosure) {
		Purpose_of_disclosure = purpose_of_disclosure;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
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

	public String getAdministration() {
		return Administration;
	}

	public void setAdministration(String administration) {
		Administration = administration;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

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

	public List<RelativesDTO> getRelatives() {
		return relatives;
	}

	public void setRelatives(List<RelativesDTO> relatives) {
		this.relatives = relatives;
	}

	public GiftDTO getGift() {
		return gift;
	}

	public void setGift(GiftDTO gift) {
		this.gift = gift;
	}

	public ConflictInterestDTO getConflictInterest() {
		return conflictInterest;
	}

	public void setConflictInterest(ConflictInterestDTO conflictInterest) {
		this.conflictInterest = conflictInterest;
	}

	public boolean isAreThereRelatives() {
		return areThereRelatives;
	}

	public void setAreThereRelatives(boolean areThereRelatives) {
		this.areThereRelatives = areThereRelatives;
	}

	public Boolean getAreThereRelatives() {
		return this.areThereRelatives;
	}

	public String getPurposeOfDisclosure() {
		return purposeOfDisclosure;
	}

	public void setPurposeOfDisclosure(String purposeOfDisclosure) {
		this.purposeOfDisclosure = purposeOfDisclosure;
	}

	public String getConfirm() {
		return Confirm;
	}

	public void setConfirm(String confirm) {
		Confirm = confirm;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}