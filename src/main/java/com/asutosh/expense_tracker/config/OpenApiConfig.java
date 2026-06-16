package com.asutosh.expense_tracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI expenseTrackerAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Expense Tracker API")
                                .description(
                                        "REST API for managing expenses"
                                )
                                .version("1.0")
                );
    }
}