package com.HumanAIdeJobAppMs.JobApp.job;

import com.HumanAIdeJobAppMs.JobApp.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class jobController {
    private JobService jobService;

    public jobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());

    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        try {
            jobService.createJob(job);
            return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add job: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO != null)
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted Successifully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob ){
        Boolean updated = jobService.updateJobById(id, updatedJob);
        if(updated)
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


/*
GET /jobs: Get all jobs
GET /jobs/{id}: Get a specific job by ID
POST /jobs: Create a new jobs (require body should content the job details)
DELETE /jobs/{id}: Delete a specific job with id
PUT /jobs/{id}: Update a specific job with id
GET /jobs/{id}/company: Get the company associated with id.
*/


/*
* Example API URLs:
* GET {base_url}/jobs
* GET {base_url}/jobs/1
* POST {base_url}/jobs
* DELETE {base_url}/jobs/1
* PUT {base_url}/jobs/1
* */