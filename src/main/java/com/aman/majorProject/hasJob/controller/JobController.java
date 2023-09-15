package com.aman.majorProject.hasJob.controller;

import com.aman.majorProject.hasJob.entity.Job;
import com.aman.majorProject.hasJob.entity.Organizations;
import com.aman.majorProject.hasJob.entity.Users;
import com.aman.majorProject.hasJob.repository.JobRepository;
import com.aman.majorProject.hasJob.repository.OrganizationRepository;
import com.aman.majorProject.hasJob.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/createForm")
    public String createJobForm(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        List<String> jobCategories = jobRepository.findAllJobCategory();
        List<String> jobTypes = jobRepository.findAllJobType();
        List<String> locations = jobRepository.findAllLocations();
        List<String> payOptions = jobRepository.findAllPay();

        model.addAttribute("jobCategories",jobCategories);
        model.addAttribute("jobTypes",jobTypes);
        model.addAttribute("locations",locations);
        model.addAttribute("payOptions",payOptions);
        model.addAttribute("loggedInUser",loggedInUser);

        return "createJob";
    }

    @PostMapping("/createJob")
    public String createJob(@ModelAttribute("job") Job job,
                            @ModelAttribute("organization") Organizations organizations){

        Organizations existingOrganization = organizationRepository.findByOrganizationName(organizations.getOrganizationName());
        if(existingOrganization != null){
            job.setOrganization(existingOrganization);
        }else{
            organizationRepository.save(organizations);
        }
        jobRepository.save(job);

        return "redirect:/dashboard";
    }

    @GetMapping({"/dashboard","/"})
    public String dashboard(Model model,
                            @RequestParam(value = "location", required = false) List<String> checkedLocations,
                            @RequestParam(value = "filterJobType", required = false) List<String> checkedJobType,
                            @RequestParam(value = "filterJobCategory", required = false) List<String> checkedJobCategory,
                            @RequestParam(value = "filterPayingType", required = false) List<String> checkedPayingType,
                            @RequestParam(value = "searchInput", required = false) String search,
                            @RequestParam(value = "selectedValue",required = false) String selectedValue){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        List<Job> jobs;
        boolean emptyLocations = checkedLocations == null || checkedLocations.isEmpty();
        boolean emptyJobType = checkedJobType == null || checkedJobType.isEmpty();
        boolean emptyJobCategory = checkedJobCategory == null || checkedJobCategory.isEmpty();
        boolean emptyPayingType = checkedPayingType == null || checkedPayingType.isEmpty();

        if (search != null && !search.isEmpty()) {
            jobs = jobRepository.findJobsByFiltersAndSearch(
                    checkedLocations, checkedJobType, checkedJobCategory,
                    checkedPayingType, emptyLocations, emptyJobType, emptyJobCategory, emptyPayingType, search);
        } else {
            if (!emptyLocations || !emptyJobCategory || !emptyJobType || !emptyPayingType) {
                jobs = jobRepository.findJobsByFilters(
                        checkedLocations, checkedJobType, checkedJobCategory,
                        checkedPayingType, emptyLocations, emptyJobType, emptyJobCategory, emptyPayingType);
            } else {
                if(selectedValue != null){
                    if(selectedValue.equals("Anywhere")){
                        jobs = jobRepository.findAllJobs();
                    }else{
                        jobs = jobRepository.findJobsBySelectedValue(selectedValue);
                    }
                }else{
                    jobs = jobRepository.findAllJobs();
                }
            }
        }

        List<String> jobCategories = jobRepository.findAllJobCategory();
        List<String> jobTypes = jobRepository.findAllJobType();
        List<String> locations = jobRepository.findAllLocations();
        List<String> payOptions = jobRepository.findAllPay();

        model.addAttribute("jobs",jobs);
        model.addAttribute("jobCategories",jobCategories);
        model.addAttribute("jobTypes",jobTypes);
        model.addAttribute("locations",locations);
        model.addAttribute("payOptions",payOptions);
        model.addAttribute("search",search);
        model.addAttribute("checkedLocations",checkedLocations);
        model.addAttribute("checkedJobType",checkedJobType);
        model.addAttribute("checkedJobCategory",checkedJobCategory);
        model.addAttribute("checkedPayingType",checkedPayingType);
        model.addAttribute("loggedInUser",loggedInUser);
        return "dashBoard";
    }

    @GetMapping("/viewJob/{id}")
    public String viewFullJob(@PathVariable int id, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        Job job = jobRepository.findById(id).orElse(null);

        List<String> jobCategories = jobRepository.findAllJobCategory();
        List<String> jobTypes = jobRepository.findAllJobType();
        List<String> locations = jobRepository.findAllLocations();
        List<String> payOptions = jobRepository.findAllPay();

        model.addAttribute("jobCategories",jobCategories);
        model.addAttribute("jobTypes",jobTypes);
        model.addAttribute("locations",locations);
        model.addAttribute("payOptions",payOptions);
        model.addAttribute("job",job);
        model.addAttribute("loggedInUser",loggedInUser);
        return "viewJob";
    }

    @PostMapping("/reviewJob")
    public String reviewJob(@ModelAttribute Job job, @ModelAttribute Organizations organization, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        job.setPostedBy(loggedInUser);
        job.setPostedOn(LocalDate.now());

        model.addAttribute("job",job);
        model.addAttribute("organization",organization);
        return "reviewJob";
    }

    @PostMapping("/bookmark")
    public String viewBookmark(@RequestParam("jobId") int jobId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        Users user = usersRepository.findByUsername(loggedInUser);
        Job job = jobRepository.findById(jobId).orElse(null);

        if(job != null){
            if (!user.getJobs().contains(job)) {
                user.getJobs().add(job);
                usersRepository.save(user);
            }
        }
        return "redirect:/viewBookmarks";
    }

    @GetMapping("/viewBookmarks")
    public String viewBookmarks(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        Users user = usersRepository.findByUsername(loggedInUser);
        List<Job> bookmarkedJobs = user.getJobs();

        model.addAttribute("bookmarkedJobs",bookmarkedJobs);
        return "bookmarks";
    }

    @PostMapping("/removeBookmark/{jobId}")
    public String removeBookmark(@PathVariable int jobId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        Users user = usersRepository.findByUsername(loggedInUser);
        Job job = jobRepository.findById(jobId).orElse(null);

        user.getJobs().remove(job);
        usersRepository.save(user);
        return "redirect:/viewBookmarks";
    }
}
