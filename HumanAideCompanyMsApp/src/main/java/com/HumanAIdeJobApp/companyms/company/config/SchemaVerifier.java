package com.HumanAIdeJobApp.companyms.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class SchemaVerifier implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to database: " + connection.getCatalog());
            System.out.println("Default schema: " + connection.getSchema());
        }
    }
}
