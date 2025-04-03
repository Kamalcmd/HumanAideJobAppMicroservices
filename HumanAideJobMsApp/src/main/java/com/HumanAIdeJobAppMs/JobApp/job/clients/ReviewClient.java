package com.HumanAIdeJobAppMs.JobApp.job.clients;

import com.HumanAIdeJobAppMs.JobApp.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "HUMANAIDEREVIEWMSAPP")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
