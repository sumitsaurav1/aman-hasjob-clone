package com.aman.majorProject.hasJob.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "paying_type")
    private String payingType;

    @Column(name = "url")
    private String url;

    @Column(name = "posted_by")
    private String postedBy;

    @Column(name = "posted_on")
    private LocalDate postedOn;

    @Column(name = "perks")
    private String perks;

    @Column(name = "equity")
    private String equity;

    @Column(name = "relocation")
    private String relocation;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organizations organization;

    @ManyToMany(mappedBy = "jobs", cascade = CascadeType.ALL)
    private List<Users> users = new ArrayList<>();

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

    public String getPerks() {
        return perks;
    }

    public void setPerks(String perks) {
        this.perks = perks;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getRelocation() {
        return relocation;
    }

    public void setRelocation(String relocation) {
        this.relocation = relocation;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Organizations getOrganization() {
        return organization;
    }

    public void setOrganization(Organizations organization) {
        this.organization = organization;
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
                ", perks='" + perks +
                ", equity='" + equity +
                ", relocation='" + relocation +
                '}';
    }
}
