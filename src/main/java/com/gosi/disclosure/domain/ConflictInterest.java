package com.gosi.disclosure.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ConflictInterest.
 */
@Entity
@Table(name = "conflict_interest")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ConflictInterest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "conflict_interest_classification")
    private String conflictInterestClassification;

    @Column(name = "conflict_interest_entity_individual")
    private String conflictInterestEntityIndividual;

    @Column(name = "conflict_interest_date_arose")
    private String conflictInterestDateArose;

    @Column(name = "conflict_interest_impact")
    private String conflictInterestImpact;

    @Column(name = "relationship_entity_individual")
    private String relationshipEntityIndividual;

    @Column(name = "affect_permission")
    private String affectPermission;

    @Column(name = "case_details")
    private String caseDetails;

    @Column(name = "disclose_inspector")
    private String discloseInspector;

    @Column(name = "facility_registered_insurance_name")
    private String facilityRegisteredInsuranceName;

    @Column(name = "office")
    private String office;

    @JsonIgnoreProperties(value = { "gift", "conflictInterest", "relatives" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "conflictInterest")
    private Disclosure disclosure;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ConflictInterest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConflictInterestClassification() {
        return this.conflictInterestClassification;
    }

    public ConflictInterest conflictInterestClassification(String conflictInterestClassification) {
        this.setConflictInterestClassification(conflictInterestClassification);
        return this;
    }

    public void setConflictInterestClassification(String conflictInterestClassification) {
        this.conflictInterestClassification = conflictInterestClassification;
    }

    public String getConflictInterestEntityIndividual() {
        return this.conflictInterestEntityIndividual;
    }

    public ConflictInterest conflictInterestEntityIndividual(String conflictInterestEntityIndividual) {
        this.setConflictInterestEntityIndividual(conflictInterestEntityIndividual);
        return this;
    }

    public void setConflictInterestEntityIndividual(String conflictInterestEntityIndividual) {
        this.conflictInterestEntityIndividual = conflictInterestEntityIndividual;
    }

    public String getConflictInterestDateArose() {
        return this.conflictInterestDateArose;
    }

    public ConflictInterest conflictInterestDateArose(String conflictInterestDateArose) {
        this.setConflictInterestDateArose(conflictInterestDateArose);
        return this;
    }

    public void setConflictInterestDateArose(String conflictInterestDateArose) {
        this.conflictInterestDateArose = conflictInterestDateArose;
    }

    public String getConflictInterestImpact() {
        return this.conflictInterestImpact;
    }

    public ConflictInterest conflictInterestImpact(String conflictInterestImpact) {
        this.setConflictInterestImpact(conflictInterestImpact);
        return this;
    }

    public void setConflictInterestImpact(String conflictInterestImpact) {
        this.conflictInterestImpact = conflictInterestImpact;
    }

    public String getRelationshipEntityIndividual() {
        return this.relationshipEntityIndividual;
    }

    public ConflictInterest relationshipEntityIndividual(String relationshipEntityIndividual) {
        this.setRelationshipEntityIndividual(relationshipEntityIndividual);
        return this;
    }

    public void setRelationshipEntityIndividual(String relationshipEntityIndividual) {
        this.relationshipEntityIndividual = relationshipEntityIndividual;
    }

    public String getAffectPermission() {
        return this.affectPermission;
    }

    public ConflictInterest affectPermission(String affectPermission) {
        this.setAffectPermission(affectPermission);
        return this;
    }

    public void setAffectPermission(String affectPermission) {
        this.affectPermission = affectPermission;
    }

    public String getCaseDetails() {
        return this.caseDetails;
    }

    public ConflictInterest caseDetails(String caseDetails) {
        this.setCaseDetails(caseDetails);
        return this;
    }

    public void setCaseDetails(String caseDetails) {
        this.caseDetails = caseDetails;
    }

    public String getDiscloseInspector() {
        return this.discloseInspector;
    }

    public ConflictInterest discloseInspector(String discloseInspector) {
        this.setDiscloseInspector(discloseInspector);
        return this;
    }

    public void setDiscloseInspector(String discloseInspector) {
        this.discloseInspector = discloseInspector;
    }

    public String getFacilityRegisteredInsuranceName() {
        return this.facilityRegisteredInsuranceName;
    }

    public ConflictInterest facilityRegisteredInsuranceName(String facilityRegisteredInsuranceName) {
        this.setFacilityRegisteredInsuranceName(facilityRegisteredInsuranceName);
        return this;
    }

    public void setFacilityRegisteredInsuranceName(String facilityRegisteredInsuranceName) {
        this.facilityRegisteredInsuranceName = facilityRegisteredInsuranceName;
    }

    public String getOffice() {
        return this.office;
    }

    public ConflictInterest office(String office) {
        this.setOffice(office);
        return this;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Disclosure getDisclosure() {
        return this.disclosure;
    }

    public void setDisclosure(Disclosure disclosure) {
        if (this.disclosure != null) {
            this.disclosure.setConflictInterest(null);
        }
        if (disclosure != null) {
            disclosure.setConflictInterest(this);
        }
        this.disclosure = disclosure;
    }

    public ConflictInterest disclosure(Disclosure disclosure) {
        this.setDisclosure(disclosure);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConflictInterest)) {
            return false;
        }
        return getId() != null && getId().equals(((ConflictInterest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConflictInterest{" +
            "id=" + getId() +
            ", conflictInterestClassification='" + getConflictInterestClassification() + "'" +
            ", conflictInterestEntityIndividual='" + getConflictInterestEntityIndividual() + "'" +
            ", conflictInterestDateArose='" + getConflictInterestDateArose() + "'" +
            ", conflictInterestImpact='" + getConflictInterestImpact() + "'" +
            ", relationshipEntityIndividual='" + getRelationshipEntityIndividual() + "'" +
            ", affectPermission='" + getAffectPermission() + "'" +
            ", caseDetails='" + getCaseDetails() + "'" +
            ", discloseInspector='" + getDiscloseInspector() + "'" +
            ", facilityRegisteredInsuranceName='" + getFacilityRegisteredInsuranceName() + "'" +
            ", office='" + getOffice() + "'" +
            "}";
    }
}
