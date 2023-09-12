package com.aman.majorProject.hasJob.controller;

import com.aman.majorProject.hasJob.entity.Job;
//import com.aman.majorProject.hasJob.entity.Organization;
import com.aman.majorProject.hasJob.repository.JobRepository;
import com.aman.majorProject.hasJob.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("/createForm")
    public String createJobForm(){
        return "createJob";
    }

    @PostMapping("/create")
    public String createJob(@ModelAttribute Job job, Model model){
        job.setPostedOn(LocalDate.now());
        jobRepository.save(job);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            @RequestParam(value = "location", required = false) List<String> checkedLocations,
                            @RequestParam(value = "filterJobType", required = false) List<String> checkedJobType,
                            @RequestParam(value = "filterJobCategory", required = false) List<String> checkedJobCategory,
                            @RequestParam(value = "filterPayingType", required = false) List<String> checkedPayingType,
                            @RequestParam(value = "searchInput", required = false) String search,
                            @RequestParam(value = "selectedValue",required = false) String selectedValue){

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
                jobs = jobRepository.findAllJobs();
            }
        }

//        if((checkedLocations != null && !checkedLocations.isEmpty()) ||
//                (checkedJobCategory != null && !checkedJobCategory.isEmpty()) ||
//                (checkedJobType != null && !checkedJobType.isEmpty()) ||
//                (checkedPayingType != null && !checkedPayingType.isEmpty())) {
//
//            jobs = jobRepository.findJobsByFilters(checkedLocations, checkedJobType,
//                    checkedJobCategory, checkedPayingType);
//        }

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
        return "dashBoard";
    }

    @GetMapping("/viewJob/{id}")
    public String viewFullJob(@PathVariable int id, Model model){
        Job job = jobRepository.findById(id).orElse(null);
//        Organization organization = organizationRepository.findById(job.getOrganizationId()).orElse(null);

        model.addAttribute("job",job);
//        model.addAttribute("organization",organization);
        return "viewJob";
    }

    @GetMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable int id){
        jobRepository.deleteById(id);
        return "redirect:publish";
    }

    @PostMapping("/updateJob/{id}")
    public String updateJob(@PathVariable int id, Model model){

        return "redirect:publish";
    }
}
