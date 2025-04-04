package com.HumanAIdeJobAppMs.HumanAideReviewMsApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review){
       boolean isreviewSaved = reviewService.addReview(companyId, review);
       if(isreviewSaved){
            return new ResponseEntity<>("Review Added Succesfully", HttpStatus.OK);
    } else{
      return new ResponseEntity<>("Review Not added", HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);

    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReeview(@PathVariable Long reviewId,
                                                @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId, review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
    }  else {
            return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        System.out.println("Deleting review with ID: " + reviewId);
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        }  else {
            return new ResponseEntity<>("Review Not Deleted", HttpStatus.NOT_FOUND);
        }

    }

}
