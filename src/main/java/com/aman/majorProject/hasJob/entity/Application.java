package com.aman.majorProject.hasJob.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "job_application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "application")
    private String jobApplication;

    @Column(name = "new_feature")
    private boolean contactYouForNewFeatures;

    @Column(name = "applied_on")
    private LocalDate appliedOn;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    public LocalDate getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDate appliedOn) {
        this.appliedOn = appliedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(String jobApplication) {
        this.jobApplication = jobApplication;
    }

    public boolean isContactYouForNewFeatures() {
        return contactYouForNewFeatures;
    }

    public void setContactYouForNewFeatures(boolean contactYouForNewFeatures) {
        this.contactYouForNewFeatures = contactYouForNewFeatures;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name +
                ", email='" + email +
                ", phone=" + phone +
                ", jobApplication='" + jobApplication +
                ", contactYouForNewFeatures=" + contactYouForNewFeatures +
                ", job=" + job +
                '}';
    }
}
