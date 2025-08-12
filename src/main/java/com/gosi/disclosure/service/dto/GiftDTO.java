package com.gosi.disclosure.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftDTO {
	@JsonProperty("Gift_Name_organization_given")
	private String Gift_Name_organization_given;
	@JsonProperty("Gift_Reason")
	private String Gift_Reason;
	@JsonProperty("Gift_official_occasion")
	private String Gift_official_occasion;
	@JsonProperty("Gift_spoils_quickly")
	private String Gift_spoils_quickly;
	@JsonProperty("Gift_for_personal_use")
	private String Gift_for_personal_use;
	@JsonProperty("Gift_Type")
	private String Gift_Type;
	@JsonProperty("Gift_estimated_value")
	private String Gift_estimated_value;
	@JsonProperty("Gift_date_receiving")
	private String Gift_date_receiving;
	@JsonProperty("Gift_own_desire")
	private String Gift_own_desire;
	@JsonProperty("Gift_impact")
	private String Gift_impact;
	@JsonProperty("Gift_reason_acceptance_rejection")
	private String Gift_reason_acceptance_rejection;
	@JsonProperty("Amount_cash_offered")
	private String Amount_cash_offered;
	@JsonProperty("Other_Notes")
	private String Other_Notes;
	@JsonProperty("Formal_occasion_visit")
	private String Formal_occasion_visit;

	public String getGift_Name_organization_given() {
		return Gift_Name_organization_given;
	}

	public void setGift_Name_organization_given(String gift_Name_organization_given) {
		Gift_Name_organization_given = gift_Name_organization_given;
	}

	public String getGift_Reason() {
		return Gift_Reason;
	}

	public void setGift_Reason(String gift_Reason) {
		Gift_Reason = gift_Reason;
	}

	public String getGift_official_occasion() {
		return Gift_official_occasion;
	}

	public void setGift_official_occasion(String gift_official_occasion) {
		Gift_official_occasion = gift_official_occasion;
	}

	public String getGift_spoils_quickly() {
		return Gift_spoils_quickly;
	}

	public void setGift_spoils_quickly(String gift_spoils_quickly) {
		Gift_spoils_quickly = gift_spoils_quickly;
	}

	public String getGift_for_personal_use() {
		return Gift_for_personal_use;
	}

	public void setGift_for_personal_use(String gift_for_personal_use) {
		Gift_for_personal_use = gift_for_personal_use;
	}

	public String getGift_Type() {
		return Gift_Type;
	}

	public void setGift_Type(String gift_Type) {
		Gift_Type = gift_Type;
	}

	public String getGift_estimated_value() {
		return Gift_estimated_value;
	}

	public void setGift_estimated_value(String gift_estimated_value) {
		Gift_estimated_value = gift_estimated_value;
	}

	public String getGift_date_receiving() {
		return Gift_date_receiving;
	}

	public void setGift_date_receiving(String gift_date_receiving) {
		Gift_date_receiving = gift_date_receiving;
	}

	public String getGift_own_desire() {
		return Gift_own_desire;
	}

	public void setGift_own_desire(String gift_own_desire) {
		Gift_own_desire = gift_own_desire;
	}

	public String getGift_impact() {
		return Gift_impact;
	}

	public void setGift_impact(String gift_impact) {
		Gift_impact = gift_impact;
	}

	public String getGift_reason_acceptance_rejection() {
		return Gift_reason_acceptance_rejection;
	}

	public void setGift_reason_acceptance_rejection(String gift_reason_acceptance_rejection) {
		Gift_reason_acceptance_rejection = gift_reason_acceptance_rejection;
	}

	public String getAmount_cash_offered() {
		return Amount_cash_offered;
	}

	public void setAmount_cash_offered(String amount_cash_offered) {
		Amount_cash_offered = amount_cash_offered;
	}

	public String getOther_Notes() {
		return Other_Notes;
	}

	public void setOther_Notes(String other_Notes) {
		Other_Notes = other_Notes;
	}

	public String getFormal_occasion_visit() {
		return Formal_occasion_visit;
	}

	public void setFormal_occasion_visit(String formal_occasion_visit) {
		Formal_occasion_visit = formal_occasion_visit;
	}

}