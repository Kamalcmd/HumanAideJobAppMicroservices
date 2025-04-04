package com.HumanAIdeJobAppMs.JobApp.job.mapper;
import com.HumanAIdeJobAppMs.JobApp.job.Job;

import com.HumanAIdeJobAppMs.JobApp.job.dto.JobDTO;
import com.HumanAIdeJobAppMs.JobApp.job.external.Company;
import com.HumanAIdeJobAppMs.JobApp.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(Job job,
                                                Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;

    }
}
