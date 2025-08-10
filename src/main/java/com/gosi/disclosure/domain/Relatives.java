package com.gosi.disclosure.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Relatives.
 */
@Entity
@Table(name = "relatives")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Relatives implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name_discloser", nullable = false)
    private String nameDiscloser;

    @NotNull
    @Column(name = "name_relative", nullable = false)
    private String nameRelative;

    @NotNull
    @Column(name = "job_title_relative", nullable = false)
    private String jobTitleRelative;

    @NotNull
    @Column(name = "relative_job_number", nullable = false)
    private String relativeJobNumber;

    @NotNull
    @Column(name = "email_relative", nullable = false)
    private String emailRelative;

    @NotNull
    @Column(name = "relative_extension_number", nullable = false)
    private String relativeExtensionNumber;

    @Column(name = "relative_relationship")
    private String relativeRelationship;

    @NotNull
    @Column(name = "general_administration_relative", nullable = false)
    private String generalAdministrationRelative;

    @NotNull
    @Column(name = "administration_relative", nullable = false)
    private String administrationRelative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "gift", "conflictInterest", "relatives" }, allowSetters = true)
    private Disclosure disclosure;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Relatives id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDiscloser() {
        return this.nameDiscloser;
    }

    public Relatives nameDiscloser(String nameDiscloser) {
        this.setNameDiscloser(nameDiscloser);
        return this;
    }

    public void setNameDiscloser(String nameDiscloser) {
        this.nameDiscloser = nameDiscloser;
    }

    public String getNameRelative() {
        return this.nameRelative;
    }

    public Relatives nameRelative(String nameRelative) {
        this.setNameRelative(nameRelative);
        return this;
    }

    public void setNameRelative(String nameRelative) {
        this.nameRelative = nameRelative;
    }

    public String getJobTitleRelative() {
        return this.jobTitleRelative;
    }

    public Relatives jobTitleRelative(String jobTitleRelative) {
        this.setJobTitleRelative(jobTitleRelative);
        return this;
    }

    public void setJobTitleRelative(String jobTitleRelative) {
        this.jobTitleRelative = jobTitleRelative;
    }

    public String getRelativeJobNumber() {
        return this.relativeJobNumber;
    }

    public Relatives relativeJobNumber(String relativeJobNumber) {
        this.setRelativeJobNumber(relativeJobNumber);
        return this;
    }

    public void setRelativeJobNumber(String relativeJobNumber) {
        this.relativeJobNumber = relativeJobNumber;
    }

    public String getEmailRelative() {
        return this.emailRelative;
    }

    public Relatives emailRelative(String emailRelative) {
        this.setEmailRelative(emailRelative);
        return this;
    }

    public void setEmailRelative(String emailRelative) {
        this.emailRelative = emailRelative;
    }

    public String getRelativeExtensionNumber() {
        return this.relativeExtensionNumber;
    }

    public Relatives relativeExtensionNumber(String relativeExtensionNumber) {
        this.setRelativeExtensionNumber(relativeExtensionNumber);
        return this;
    }

    public void setRelativeExtensionNumber(String relativeExtensionNumber) {
        this.relativeExtensionNumber = relativeExtensionNumber;
    }

    public String getRelativeRelationship() {
        return this.relativeRelationship;
    }

    public Relatives relativeRelationship(String relativeRelationship) {
        this.setRelativeRelationship(relativeRelationship);
        return this;
    }

    public void setRelativeRelationship(String relativeRelationship) {
        this.relativeRelationship = relativeRelationship;
    }

    public String getGeneralAdministrationRelative() {
        return this.generalAdministrationRelative;
    }

    public Relatives generalAdministrationRelative(String generalAdministrationRelative) {
        this.setGeneralAdministrationRelative(generalAdministrationRelative);
        return this;
    }

    public void setGeneralAdministrationRelative(String generalAdministrationRelative) {
        this.generalAdministrationRelative = generalAdministrationRelative;
    }

    public String getAdministrationRelative() {
        return this.administrationRelative;
    }

    public Relatives administrationRelative(String administrationRelative) {
        this.setAdministrationRelative(administrationRelative);
        return this;
    }

    public void setAdministrationRelative(String administrationRelative) {
        this.administrationRelative = administrationRelative;
    }

    public Disclosure getDisclosure() {
        return this.disclosure;
    }

    public void setDisclosure(Disclosure disclosure) {
        this.disclosure = disclosure;
    }

    public Relatives disclosure(Disclosure disclosure) {
        this.setDisclosure(disclosure);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Relatives)) {
            return false;
        }
        return getId() != null && getId().equals(((Relatives) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Relatives{" +
            "id=" + getId() +
            ", nameDiscloser='" + getNameDiscloser() + "'" +
            ", nameRelative='" + getNameRelative() + "'" +
            ", jobTitleRelative='" + getJobTitleRelative() + "'" +
            ", relativeJobNumber='" + getRelativeJobNumber() + "'" +
            ", emailRelative='" + getEmailRelative() + "'" +
            ", relativeExtensionNumber='" + getRelativeExtensionNumber() + "'" +
            ", relativeRelationship='" + getRelativeRelationship() + "'" +
            ", generalAdministrationRelative='" + getGeneralAdministrationRelative() + "'" +
            ", administrationRelative='" + getAdministrationRelative() + "'" +
            "}";
    }
}
