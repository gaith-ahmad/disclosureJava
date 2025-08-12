package com.gosi.disclosure.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "display_name")
    private String displayName;

   
    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "local_phone")
    private String localPhone;

    @Column(name = "department")
    private String department;

    @Column(name = "physical_delivery_office_name")
    private String physicalDeliveryOfficeName;

    @Column(name = "distinguished_name")
    private String distinguishedName;

    @Column(name = "username")
    private String username;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Employee displayName(String displayName) {
        this.setDisplayName(displayName);
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return this.email;
    }

    public Employee email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return this.title;
    }

    public Employee title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public Employee telephoneNumber(String telephoneNumber) {
        this.setTelephoneNumber(telephoneNumber);
        return this;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getLocalPhone() {
        return this.localPhone;
    }

    public Employee localPhone(String localPhone) {
        this.setLocalPhone(localPhone);
        return this;
    }

    public void setLocalPhone(String localPhone) {
        this.localPhone = localPhone;
    }

    public String getDepartment() {
        return this.department;
    }

    public Employee department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhysicalDeliveryOfficeName() {
        return this.physicalDeliveryOfficeName;
    }

    public Employee physicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
        this.setPhysicalDeliveryOfficeName(physicalDeliveryOfficeName);
        return this;
    }

    public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
        this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
    }

    public String getDistinguishedName() {
        return this.distinguishedName;
    }

    public Employee distinguishedName(String distinguishedName) {
        this.setDistinguishedName(distinguishedName);
        return this;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public String getUsername() {
        return this.username;
    }

    public Employee username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return getId() != null && getId().equals(((Employee) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", displayName='" + getDisplayName() + "'" +
            ", email='" + getEmail() + "'" +
            ", title='" + getTitle() + "'" +
            ", telephoneNumber='" + getTelephoneNumber() + "'" +
            ", localPhone='" + getLocalPhone() + "'" +
            ", department='" + getDepartment() + "'" +
            ", physicalDeliveryOfficeName='" + getPhysicalDeliveryOfficeName() + "'" +
            ", distinguishedName='" + getDistinguishedName() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }
}
