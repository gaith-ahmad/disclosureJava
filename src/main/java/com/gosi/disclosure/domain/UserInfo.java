package com.gosi.disclosure.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserInfo.
 */
@Entity
@Table(name = "user_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   
    @Column(name = "login_name")
    private String loginName;

   
    @Column(name = "full_name")
    private String fullName;

   
    @Column(name = "email")
    private String email;

    @Column(name = "jhi_key")
    private Long key;

   
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

    @Column(name = "relative_relationship")
    private String relativeRelationship;

    @Column(name = "office")
    private String office;

    @JsonIgnoreProperties(value = { "userInfo" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private ManagerInfo managerinfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public UserInfo loginName(String loginName) {
        this.setLoginName(loginName);
        return this;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public UserInfo fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public UserInfo email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getKey() {
        return this.key;
    }

    public UserInfo key(Long key) {
        this.setKey(key);
        return this;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public UserInfo name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public UserInfo jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobNumber() {
        return this.jobNumber;
    }

    public UserInfo jobNumber(String jobNumber) {
        this.setJobNumber(jobNumber);
        return this;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getExt() {
        return this.ext;
    }

    public UserInfo ext(String ext) {
        this.setExt(ext);
        return this;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPublicAdministration() {
        return this.publicAdministration;
    }

    public UserInfo publicAdministration(String publicAdministration) {
        this.setPublicAdministration(publicAdministration);
        return this;
    }

    public void setPublicAdministration(String publicAdministration) {
        this.publicAdministration = publicAdministration;
    }

    public String getAdministration() {
        return this.administration;
    }

    public UserInfo administration(String administration) {
        this.setAdministration(administration);
        return this;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getRelativeRelationship() {
        return this.relativeRelationship;
    }

    public UserInfo relativeRelationship(String relativeRelationship) {
        this.setRelativeRelationship(relativeRelationship);
        return this;
    }

    public void setRelativeRelationship(String relativeRelationship) {
        this.relativeRelationship = relativeRelationship;
    }

    public String getOffice() {
        return this.office;
    }

    public UserInfo office(String office) {
        this.setOffice(office);
        return this;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public ManagerInfo getManagerinfo() {
        return this.managerinfo;
    }

    public void setManagerinfo(ManagerInfo managerInfo) {
        this.managerinfo = managerInfo;
    }

    public UserInfo managerinfo(ManagerInfo managerInfo) {
        this.setManagerinfo(managerInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((UserInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + getId() +
            ", loginName='" + getLoginName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", email='" + getEmail() + "'" +
            ", key=" + getKey() +
            ", name='" + getName() + "'" +
            ", jobTitle='" + getJobTitle() + "'" +
            ", jobNumber='" + getJobNumber() + "'" +
            ", ext='" + getExt() + "'" +
            ", publicAdministration='" + getPublicAdministration() + "'" +
            ", administration='" + getAdministration() + "'" +
            ", relativeRelationship='" + getRelativeRelationship() + "'" +
            ", office='" + getOffice() + "'" +
            "}";
    }
}
