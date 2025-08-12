package com.gosi.disclosure.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ManagerInfo.
 */
@Entity
@Table(name = "manager_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ManagerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   
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

    @JsonIgnoreProperties(value = { "managerinfo" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "managerinfo")
    private UserInfo userInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ManagerInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailDirectManager() {
        return this.emailDirectManager;
    }

    public ManagerInfo emailDirectManager(String emailDirectManager) {
        this.setEmailDirectManager(emailDirectManager);
        return this;
    }

    public void setEmailDirectManager(String emailDirectManager) {
        this.emailDirectManager = emailDirectManager;
    }

    public String getNameDirectManager() {
        return this.nameDirectManager;
    }

    public ManagerInfo nameDirectManager(String nameDirectManager) {
        this.setNameDirectManager(nameDirectManager);
        return this;
    }

    public void setNameDirectManager(String nameDirectManager) {
        this.nameDirectManager = nameDirectManager;
    }

    public String getJobNumberDirectManager() {
        return this.jobNumberDirectManager;
    }

    public ManagerInfo jobNumberDirectManager(String jobNumberDirectManager) {
        this.setJobNumberDirectManager(jobNumberDirectManager);
        return this;
    }

    public void setJobNumberDirectManager(String jobNumberDirectManager) {
        this.jobNumberDirectManager = jobNumberDirectManager;
    }

    public String getJobTitleDirectManager() {
        return this.jobTitleDirectManager;
    }

    public ManagerInfo jobTitleDirectManager(String jobTitleDirectManager) {
        this.setJobTitleDirectManager(jobTitleDirectManager);
        return this;
    }

    public void setJobTitleDirectManager(String jobTitleDirectManager) {
        this.jobTitleDirectManager = jobTitleDirectManager;
    }

    public String getExtDirectManager() {
        return this.extDirectManager;
    }

    public ManagerInfo extDirectManager(String extDirectManager) {
        this.setExtDirectManager(extDirectManager);
        return this;
    }

    public void setExtDirectManager(String extDirectManager) {
        this.extDirectManager = extDirectManager;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        if (this.userInfo != null) {
            this.userInfo.setManagerinfo(null);
        }
        if (userInfo != null) {
            userInfo.setManagerinfo(this);
        }
        this.userInfo = userInfo;
    }

    public ManagerInfo userInfo(UserInfo userInfo) {
        this.setUserInfo(userInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManagerInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((ManagerInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManagerInfo{" +
            "id=" + getId() +
            ", emailDirectManager='" + getEmailDirectManager() + "'" +
            ", nameDirectManager='" + getNameDirectManager() + "'" +
            ", jobNumberDirectManager='" + getJobNumberDirectManager() + "'" +
            ", jobTitleDirectManager='" + getJobTitleDirectManager() + "'" +
            ", extDirectManager='" + getExtDirectManager() + "'" +
            "}";
    }
}
