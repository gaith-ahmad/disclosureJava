package com.gosi.disclosure.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Gift.
 */
@Entity
@Table(name = "gift")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Gift implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "gift_name_organization_given")
    private String giftNameOrganizationGiven;

    @Column(name = "gift_reason")
    private String giftReason;

   
    @Column(name = "gift_official_occasion")
    private String giftOfficialOccasion;

   
    @Column(name = "gift_spoils_quickly")
    private String giftSpoilsQuickly;

   
    @Column(name = "gift_for_personal_use")
    private String giftForPersonalUse;

   
    @Column(name = "gift_type")
    private String giftType;

    @Column(name = "gift_estimated_value")
    private String giftEstimatedValue;

   
    @Column(name = "gift_date_receiving")
    private String giftDateReceiving;

   
    @Column(name = "gift_own_desire")
    private String giftOwnDesire;

   
    @Column(name = "gift_impact")
    private String giftImpact;

    @Column(name = "gift_reason_acceptance_rejection")
    private String giftReasonAcceptanceRejection;

    @Column(name = "amount_cash_offered")
    private String amountCashOffered;

    @Column(name = "other_notes")
    private String otherNotes;

    @Column(name = "formal_occasion_visit")
    private String formalOccasionVisit;

    @JsonIgnoreProperties(value = { "gift", "conflictInterest", "relatives" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "gift")
    private Disclosure disclosure;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Gift id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiftNameOrganizationGiven() {
        return this.giftNameOrganizationGiven;
    }

    public Gift giftNameOrganizationGiven(String giftNameOrganizationGiven) {
        this.setGiftNameOrganizationGiven(giftNameOrganizationGiven);
        return this;
    }

    public void setGiftNameOrganizationGiven(String giftNameOrganizationGiven) {
        this.giftNameOrganizationGiven = giftNameOrganizationGiven;
    }

    public String getGiftReason() {
        return this.giftReason;
    }

    public Gift giftReason(String giftReason) {
        this.setGiftReason(giftReason);
        return this;
    }

    public void setGiftReason(String giftReason) {
        this.giftReason = giftReason;
    }

    public String getGiftOfficialOccasion() {
        return this.giftOfficialOccasion;
    }

    public Gift giftOfficialOccasion(String giftOfficialOccasion) {
        this.setGiftOfficialOccasion(giftOfficialOccasion);
        return this;
    }

    public void setGiftOfficialOccasion(String giftOfficialOccasion) {
        this.giftOfficialOccasion = giftOfficialOccasion;
    }

    public String getGiftSpoilsQuickly() {
        return this.giftSpoilsQuickly;
    }

    public Gift giftSpoilsQuickly(String giftSpoilsQuickly) {
        this.setGiftSpoilsQuickly(giftSpoilsQuickly);
        return this;
    }

    public void setGiftSpoilsQuickly(String giftSpoilsQuickly) {
        this.giftSpoilsQuickly = giftSpoilsQuickly;
    }

    public String getGiftForPersonalUse() {
        return this.giftForPersonalUse;
    }

    public Gift giftForPersonalUse(String giftForPersonalUse) {
        this.setGiftForPersonalUse(giftForPersonalUse);
        return this;
    }

    public void setGiftForPersonalUse(String giftForPersonalUse) {
        this.giftForPersonalUse = giftForPersonalUse;
    }

    public String getGiftType() {
        return this.giftType;
    }

    public Gift giftType(String giftType) {
        this.setGiftType(giftType);
        return this;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getGiftEstimatedValue() {
        return this.giftEstimatedValue;
    }

    public Gift giftEstimatedValue(String giftEstimatedValue) {
        this.setGiftEstimatedValue(giftEstimatedValue);
        return this;
    }

    public void setGiftEstimatedValue(String giftEstimatedValue) {
        this.giftEstimatedValue = giftEstimatedValue;
    }

    public String getGiftDateReceiving() {
        return this.giftDateReceiving;
    }

    public Gift giftDateReceiving(String giftDateReceiving) {
        this.setGiftDateReceiving(giftDateReceiving);
        return this;
    }

    public void setGiftDateReceiving(String giftDateReceiving) {
        this.giftDateReceiving = giftDateReceiving;
    }

    public String getGiftOwnDesire() {
        return this.giftOwnDesire;
    }

    public Gift giftOwnDesire(String giftOwnDesire) {
        this.setGiftOwnDesire(giftOwnDesire);
        return this;
    }

    public void setGiftOwnDesire(String giftOwnDesire) {
        this.giftOwnDesire = giftOwnDesire;
    }

    public String getGiftImpact() {
        return this.giftImpact;
    }

    public Gift giftImpact(String giftImpact) {
        this.setGiftImpact(giftImpact);
        return this;
    }

    public void setGiftImpact(String giftImpact) {
        this.giftImpact = giftImpact;
    }

    public String getGiftReasonAcceptanceRejection() {
        return this.giftReasonAcceptanceRejection;
    }

    public Gift giftReasonAcceptanceRejection(String giftReasonAcceptanceRejection) {
        this.setGiftReasonAcceptanceRejection(giftReasonAcceptanceRejection);
        return this;
    }

    public void setGiftReasonAcceptanceRejection(String giftReasonAcceptanceRejection) {
        this.giftReasonAcceptanceRejection = giftReasonAcceptanceRejection;
    }

    public String getAmountCashOffered() {
        return this.amountCashOffered;
    }

    public Gift amountCashOffered(String amountCashOffered) {
        this.setAmountCashOffered(amountCashOffered);
        return this;
    }

    public void setAmountCashOffered(String amountCashOffered) {
        this.amountCashOffered = amountCashOffered;
    }

    public String getOtherNotes() {
        return this.otherNotes;
    }

    public Gift otherNotes(String otherNotes) {
        this.setOtherNotes(otherNotes);
        return this;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }

    public String getFormalOccasionVisit() {
        return this.formalOccasionVisit;
    }

    public Gift formalOccasionVisit(String formalOccasionVisit) {
        this.setFormalOccasionVisit(formalOccasionVisit);
        return this;
    }

    public void setFormalOccasionVisit(String formalOccasionVisit) {
        this.formalOccasionVisit = formalOccasionVisit;
    }

    public Disclosure getDisclosure() {
        return this.disclosure;
    }

    public void setDisclosure(Disclosure disclosure) {
        if (this.disclosure != null) {
            this.disclosure.setGift(null);
        }
        if (disclosure != null) {
            disclosure.setGift(this);
        }
        this.disclosure = disclosure;
    }

    public Gift disclosure(Disclosure disclosure) {
        this.setDisclosure(disclosure);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gift)) {
            return false;
        }
        return getId() != null && getId().equals(((Gift) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Gift{" +
            "id=" + getId() +
            ", giftNameOrganizationGiven='" + getGiftNameOrganizationGiven() + "'" +
            ", giftReason='" + getGiftReason() + "'" +
            ", giftOfficialOccasion='" + getGiftOfficialOccasion() + "'" +
            ", giftSpoilsQuickly='" + getGiftSpoilsQuickly() + "'" +
            ", giftForPersonalUse='" + getGiftForPersonalUse() + "'" +
            ", giftType='" + getGiftType() + "'" +
            ", giftEstimatedValue='" + getGiftEstimatedValue() + "'" +
            ", giftDateReceiving='" + getGiftDateReceiving() + "'" +
            ", giftOwnDesire='" + getGiftOwnDesire() + "'" +
            ", giftImpact='" + getGiftImpact() + "'" +
            ", giftReasonAcceptanceRejection='" + getGiftReasonAcceptanceRejection() + "'" +
            ", amountCashOffered='" + getAmountCashOffered() + "'" +
            ", otherNotes='" + getOtherNotes() + "'" +
            ", formalOccasionVisit='" + getFormalOccasionVisit() + "'" +
            "}";
    }
}
