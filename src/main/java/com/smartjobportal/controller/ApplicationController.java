package com.smartjobportal.controller;



import com.smartjobportal.model.Application;
import com.smartjobportal.model.Job;
import com.smartjobportal.model.User;
import com.smartjobportal.repository.ApplicationRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@AllArgsConstructor
public class ApplicationController {

    private ApplicationRepository applicationRepository;
    private UserRepository userRepository;
    private JobRepository jobRepository;


    @PostMapping("/{jobId}/{userId}")
    public Application applyToJob(@PathVariable Long jobId,
                                  @PathVariable Long userId,
                                  @RequestBody Application application) {
        Job job = jobRepository.findById(jobId).orElse(null);
        User applicant = userRepository.findById(userId).orElse(null);

        if (job == null || applicant == null) return null;

        application.setJob(job);
        application.setApplicant(applicant);

        return applicationRepository.save(application);
    }


    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
}
