package com.aman.majorProject.hasJob.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String jobRole;

    @Column(name = "type")
    private String jobType;

    @Column(name = "category")
    private String jobCategory;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String jobDescription;

    @Column(name = "paying_type")
    private String payingType;

    @Column(name = "url")
    private String url;

    @Column(name = "posted_by")
    private String postedBy;

    @Column(name = "posted_on")
    private LocalDate postedOn;

    @Column(name = "organization_id", nullable = false)
    private int organizationId;

    @ManyToOne
    @JoinColumn(name = "organization_id", insertable = false, updatable = false)
    private Organization organization;

//    @OneToMany
//    private Users user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getPayingType() {
        return payingType;
    }

    public void setPayingType(String payingType) {
        this.payingType = payingType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public LocalDate getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDate postedOn) {
        this.postedOn = postedOn;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobRole='" + jobRole +
                ", jobType='" + jobType +
                ", jobCategory='" + jobCategory +
                ", location='" + location +
                ", jobDescription='" + jobDescription +
                ", payingType='" + payingType +
                ", url='" + url +
                ", postedBy='" + postedBy +
                ", postedOn=" + postedOn +
                ", organizationId=" + organizationId +
                '}';
    }
}
