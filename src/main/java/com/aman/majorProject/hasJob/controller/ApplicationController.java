package com.aman.majorProject.hasJob.controller;

import com.aman.majorProject.hasJob.entity.Application;
import com.aman.majorProject.hasJob.entity.Job;
import com.aman.majorProject.hasJob.entity.Users;
import com.aman.majorProject.hasJob.repository.ApplicationRepository;
import com.aman.majorProject.hasJob.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping("/save")
    public String saveApplication(@RequestParam("jobId") int jobId, @ModelAttribute Application application){

        Job job = jobRepository.findById(jobId).orElse(null);

        if(job != null){
            application.setJob(job);
            application.setAppliedOn(LocalDate.now());
            applicationRepository.save(application);
        }
        return "redirect:/job/dashboard";
    }

    @GetMapping("/viewAppliedJobs")
    public String viewAppliedJobs(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        List<Application> applications = applicationRepository.findByName(loggedInUser);
        List<Job> appliedJobs = new ArrayList<>();

        for(Application application : applications){
            appliedJobs.add(application.getJob());
        }

        model.addAttribute("appliedJobs",appliedJobs);
        return "applied";
    }
}
