package com.gosi.disclosure.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Disclosure.
 */
@Entity
@Table(name = "disclosure")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Disclosure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "purpose_of_disclosure")
    private String purposeOfDisclosure;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_number")
    private String jobNumber;

    @Column(name = "ext")
    private String ext;

    @Column(name = "public_administration")
    private String publicAdministration;

    @Column(name = "administration")
    private String administration;

    @Column(name = "confirm")
    private String confirm;

    @Column(name = "email")
    private String email;

    @Column(name = "email_direct_manager")
    private String emailDirectManager;

    @Column(name = "name_direct_manager")
    private String nameDirectManager;

    @Column(name = "job_number_direct_manager")
    private String jobNumberDirectManager;

    @Column(name = "job_title_direct_manager")
    private String jobTitleDirectManager;

    @Column(name = "ext_direct_manager")
    private String extDirectManager;

    @Column(name = "are_there_relatives")
    private Boolean areThereRelatives;

    @Column(name = "file")
    private String file;

    @Column(name = "filename")
    private String filename;

    @JsonIgnoreProperties(value = { "disclosure" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Gift gift;

    @JsonIgnoreProperties(value = { "disclosure" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private ConflictInterest conflictInterest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "disclosure")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "disclosure" }, allowSetters = true)
    private Set<Relatives> relatives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Disclosure id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurposeOfDisclosure() {
        return this.purposeOfDisclosure;
    }

    public Disclosure purposeOfDisclosure(String purposeOfDisclosure) {
        this.setPurposeOfDisclosure(purposeOfDisclosure);
        return this;
    }

    public void setPurposeOfDisclosure(String purposeOfDisclosure) {
        this.purposeOfDisclosure = purposeOfDisclosure;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public Disclosure creationDate(String creationDate) {
        this.setCreationDate(creationDate);
        return this;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return this.name;
    }

    public Disclosure name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public Disclosure jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobNumber() {
        return this.jobNumber;
    }

    public Disclosure jobNumber(String jobNumber) {
        this.setJobNumber(jobNumber);
        return this;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getExt() {
        return this.ext;
    }

    public Disclosure ext(String ext) {
        this.setExt(ext);
        return this;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPublicAdministration() {
        return this.publicAdministration;
    }

    public Disclosure publicAdministration(String publicAdministration) {
        this.setPublicAdministration(publicAdministration);
        return this;
    }

    public void setPublicAdministration(String publicAdministration) {
        this.publicAdministration = publicAdministration;
    }

    public String getAdministration() {
        return this.administration;
    }

    public Disclosure administration(String administration) {
        this.setAdministration(administration);
        return this;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getConfirm() {
        return this.confirm;
    }

    public Disclosure confirm(String confirm) {
        this.setConfirm(confirm);
        return this;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getEmail() {
        return this.email;
    }

    public Disclosure email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailDirectManager() {
        return this.emailDirectManager;
    }

    public Disclosure emailDirectManager(String emailDirectManager) {
        this.setEmailDirectManager(emailDirectManager);
        return this;
    }

    public void setEmailDirectManager(String emailDirectManager) {
        this.emailDirectManager = emailDirectManager;
    }

    public String getNameDirectManager() {
        return this.nameDirectManager;
    }

    public Disclosure nameDirectManager(String nameDirectManager) {
        this.setNameDirectManager(nameDirectManager);
        return this;
    }

    public void setNameDirectManager(String nameDirectManager) {
        this.nameDirectManager = nameDirectManager;
    }

    public String getJobNumberDirectManager() {
        return this.jobNumberDirectManager;
    }

    public Disclosure jobNumberDirectManager(String jobNumberDirectManager) {
        this.setJobNumberDirectManager(jobNumberDirectManager);
        return this;
    }

    public void setJobNumberDirectManager(String jobNumberDirectManager) {
        this.jobNumberDirectManager = jobNumberDirectManager;
    }

    public String getJobTitleDirectManager() {
        return this.jobTitleDirectManager;
    }

    public Disclosure jobTitleDirectManager(String jobTitleDirectManager) {
        this.setJobTitleDirectManager(jobTitleDirectManager);
        return this;
    }

    public void setJobTitleDirectManager(String jobTitleDirectManager) {
        this.jobTitleDirectManager = jobTitleDirectManager;
    }

    public String getExtDirectManager() {
        return this.extDirectManager;
    }

    public Disclosure extDirectManager(String extDirectManager) {
        this.setExtDirectManager(extDirectManager);
        return this;
    }

    public void setExtDirectManager(String extDirectManager) {
        this.extDirectManager = extDirectManager;
    }

    public Boolean getAreThereRelatives() {
        return this.areThereRelatives;
    }

    public Disclosure areThereRelatives(Boolean areThereRelatives) {
        this.setAreThereRelatives(areThereRelatives);
        return this;
    }

    public void setAreThereRelatives(Boolean areThereRelatives) {
        this.areThereRelatives = areThereRelatives;
    }

    public String getFile() {
        return this.file;
    }

    public Disclosure file(String file) {
        this.setFile(file);
        return this;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFilename() {
        return this.filename;
    }

    public Disclosure filename(String filename) {
        this.setFilename(filename);
        return this;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Gift getGift() {
        return this.gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Disclosure gift(Gift gift) {
        this.setGift(gift);
        return this;
    }

    public ConflictInterest getConflictInterest() {
        return this.conflictInterest;
    }

    public void setConflictInterest(ConflictInterest conflictInterest) {
        this.conflictInterest = conflictInterest;
    }

    public Disclosure conflictInterest(ConflictInterest conflictInterest) {
        this.setConflictInterest(conflictInterest);
        return this;
    }

    public Set<Relatives> getRelatives() {
        return this.relatives;
    }

    public void setRelatives(Set<Relatives> relatives) {
        if (this.relatives != null) {
            this.relatives.forEach(i -> i.setDisclosure(null));
        }
        if (relatives != null) {
            relatives.forEach(i -> i.setDisclosure(this));
        }
        this.relatives = relatives;
    }

    public Disclosure relatives(Set<Relatives> relatives) {
        this.setRelatives(relatives);
        return this;
    }

    public Disclosure addRelatives(Relatives relatives) {
        this.relatives.add(relatives);
        relatives.setDisclosure(this);
        return this;
    }

    public Disclosure removeRelatives(Relatives relatives) {
        this.relatives.remove(relatives);
        relatives.setDisclosure(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Disclosure)) {
            return false;
        }
        return getId() != null && getId().equals(((Disclosure) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Disclosure{" +
            "id=" + getId() +
            ", purposeOfDisclosure='" + getPurposeOfDisclosure() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", name='" + getName() + "'" +
            ", jobTitle='" + getJobTitle() + "'" +
            ", jobNumber='" + getJobNumber() + "'" +
            ", ext='" + getExt() + "'" +
            ", publicAdministration='" + getPublicAdministration() + "'" +
            ", administration='" + getAdministration() + "'" +
            ", confirm='" + getConfirm() + "'" +
            ", email='" + getEmail() + "'" +
            ", emailDirectManager='" + getEmailDirectManager() + "'" +
            ", nameDirectManager='" + getNameDirectManager() + "'" +
            ", jobNumberDirectManager='" + getJobNumberDirectManager() + "'" +
            ", jobTitleDirectManager='" + getJobTitleDirectManager() + "'" +
            ", extDirectManager='" + getExtDirectManager() + "'" +
            ", areThereRelatives='" + getAreThereRelatives() + "'" +
            ", file='" + getFile() + "'" +
            ", filename='" + getFilename() + "'" +
            "}";
    }
}
