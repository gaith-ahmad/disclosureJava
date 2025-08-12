package com.gosi.disclosure.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConflictInterestDTO {
	@JsonProperty("Conflict_interest_Classification")
	private String Conflict_interest_Classification;
	@JsonProperty("Conflict_interest_entity_individual")
	private String Conflict_interest_entity_individual;
	@JsonProperty("Conflict_interest_date_arose")
	private String Conflict_interest_date_arose;
	@JsonProperty("Conflict_interest_impact")
	private String Conflict_interest_impact;
	@JsonProperty("Relationship_entity_individual")
	private String Relationship_entity_individual;
	@JsonProperty("AffectPermission")
	private String AffectPermission;
	@JsonProperty("Case_details")
	private String Case_details;
	@JsonProperty("Disclose_inspector")
	private String Disclose_inspector;
	@JsonProperty("Facility_registered_insurance_name")
	private String Facility_registered_insurance_name;
	@JsonProperty("Office")
	private String Office;

	public String getConflict_interest_Classification() {
		return Conflict_interest_Classification;
	}

	public void setConflict_interest_Classification(String conflict_interest_Classification) {
		Conflict_interest_Classification = conflict_interest_Classification;
	}

	public String getConflict_interest_entity_individual() {
		return Conflict_interest_entity_individual;
	}

	public void setConflict_interest_entity_individual(String conflict_interest_entity_individual) {
		Conflict_interest_entity_individual = conflict_interest_entity_individual;
	}

	public String getConflict_interest_date_arose() {
		return Conflict_interest_date_arose;
	}

	public void setConflict_interest_date_arose(String conflict_interest_date_arose) {
		Conflict_interest_date_arose = conflict_interest_date_arose;
	}

	public String getConflict_interest_impact() {
		return Conflict_interest_impact;
	}

	public void setConflict_interest_impact(String conflict_interest_impact) {
		Conflict_interest_impact = conflict_interest_impact;
	}

	public String getRelationship_entity_individual() {
		return Relationship_entity_individual;
	}

	public void setRelationship_entity_individual(String relationship_entity_individual) {
		Relationship_entity_individual = relationship_entity_individual;
	}

	public String getAffectPermission() {
		return AffectPermission;
	}

	public void setAffectPermission(String affectPermission) {
		AffectPermission = affectPermission;
	}

	public String getCase_details() {
		return Case_details;
	}

	public void setCase_details(String case_details) {
		Case_details = case_details;
	}

	public String getDisclose_inspector() {
		return Disclose_inspector;
	}

	public void setDisclose_inspector(String disclose_inspector) {
		Disclose_inspector = disclose_inspector;
	}

	public String getFacility_registered_insurance_name() {
		return Facility_registered_insurance_name;
	}

	public void setFacility_registered_insurance_name(String facility_registered_insurance_name) {
		Facility_registered_insurance_name = facility_registered_insurance_name;
	}

	public String getOffice() {
		return Office;
	}

	public void setOffice(String office) {
		Office = office;
	}

}