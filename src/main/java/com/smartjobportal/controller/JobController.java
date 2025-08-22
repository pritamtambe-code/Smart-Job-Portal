package com.smartjobportal.controller;



import com.smartjobportal.model.Job;
import com.smartjobportal.model.User;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobRepository jobRepository;
    private UserRepository userRepository;


    @PostMapping("/{employerId}")
    public Job postJob(@PathVariable Long employerId, @RequestBody Job job) {
        User employer = userRepository.findById(employerId).orElse(null);
        if (employer == null) return null;

        job.setEmployer(employer);
        return jobRepository.save(job);
    }


    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }


    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobRepository.findById(id).orElse(null);
    }


    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
    }
}
