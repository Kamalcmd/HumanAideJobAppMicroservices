package com.HumanAIdeJobAppMs.JobApp.job.impl;

import com.HumanAIdeJobAppMs.JobApp.job.Job;
import com.HumanAIdeJobAppMs.JobApp.job.JobRepository;
import com.HumanAIdeJobAppMs.JobApp.job.JobService;
import com.HumanAIdeJobAppMs.JobApp.job.clients.CompanyClient;
import com.HumanAIdeJobAppMs.JobApp.job.clients.ReviewClient;
import com.HumanAIdeJobAppMs.JobApp.job.dto.JobDTO;
import com.HumanAIdeJobAppMs.JobApp.job.external.Company;
import com.HumanAIdeJobAppMs.JobApp.job.external.Review;
import com.HumanAIdeJobAppMs.JobApp.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImp implements JobService {
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;
    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImp(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallBack")
//    @Retry(name= "companyBreaker", fallbackMethod = "companyBreakerFallBack")
    @RateLimiter(name = "companyBreaker")
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();


        return jobs.stream().map(this::converToDto)
                .collect(Collectors.toList());
    }
    public List<String> companyBreakerFallBack(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;

    }
    private JobDTO converToDto(Job job){
        if(job==null){
            return null;
        }
//           Company company = restTemplate.getForObject("http://HUMANAIDECOMPANYMSAPP:8081/companies/"+job.getCompanyId(),
//                    Company.class);
        //clients folder
        Company company;
        try {
            company = companyClient.getCompany(job.getCompanyId());
        }catch (Exception e){
            company = new Company();
            company.setName("Unknown Company");
        }

//            ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                    "http://HUMANAIDEREVIEWMSAPP:8083/reviews?companyId=" + job.getCompanyId(),
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<Review>>(){
//                    });
        List<Review> reviews;
        try {
            reviews = reviewClient.getReviews(job.getCompanyId());
        }catch (Exception e){
            reviews = new ArrayList<>();
        }
//            List<Review> reviews = reviewResponse.getBody();


            JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
           // jobDTO.setCompany(company);
            return jobDTO;

    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
        //jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return converToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if (job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}






