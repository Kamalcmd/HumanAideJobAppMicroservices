package com.HumanAIdeJobApp.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    //@PreAuthorize("hasRole(ROLE_USER)") // Only users with ROLE_USER can access this endpoint
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                        @RequestBody Company updatedCompany){
        Boolean updated = companyService.updateCompany(id, updatedCompany);
        if(updated)
            return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')") // Only users with ROLE_ADMIN can access this endpoint
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        try{
            companyService.createCompany(company);
            return new ResponseEntity<>("Company added Successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add Company" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
            Boolean isDeleted = companyService.deleteCompanyById(id);
            if (isDeleted)
                return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
            return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
