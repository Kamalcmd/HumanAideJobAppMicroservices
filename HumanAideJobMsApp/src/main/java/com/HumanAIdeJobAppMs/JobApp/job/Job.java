package com.HumanAIdeJobAppMs.JobApp.job;
import jakarta.persistence.*;

@Entity
//@Table(name = "job_table")     //Used to specify table name if we want to change the table name
public class Job {
    @Id                        //primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String Location;
    private Long companyId;



    public Job() {
    }

    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.Location = location;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }
}

