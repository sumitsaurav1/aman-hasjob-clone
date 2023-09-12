package com.aman.majorProject.hasJob.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String organizationName;

    @Column(name = "url")
    private String organizationUrl;

    @Column(name = "email")
    private String organizationEmail;

    @Column(name = "collaborator")
    private String collaborators;

    @Column(name = "contact_you")
    private boolean contactYou;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Job> job = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationUrl() {
        return organizationUrl;
    }

    public void setOrganizationUrl(String organizationUrl) {
        this.organizationUrl = organizationUrl;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(String collaborators) {
        this.collaborators = collaborators;
    }

    public boolean isContactYou() {
        return contactYou;
    }

    public void setContactYou(boolean contactYou) {
        this.contactYou = contactYou;
    }

    public List<Job> getJob() {
        return job;
    }

    public void setJob(List<Job> job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", organizationName='" + organizationName +
                ", organizationUrl='" + organizationUrl +
                ", organizationEmail='" + organizationEmail +
                ", collaborators='" + collaborators +
                ", contactYou=" + contactYou +
                ", job=" + job +
                '}';
    }
}
