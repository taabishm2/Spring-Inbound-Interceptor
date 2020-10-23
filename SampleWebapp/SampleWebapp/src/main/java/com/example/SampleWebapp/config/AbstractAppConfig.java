package com.example.SampleWebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
public abstract class AbstractAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public StatusReportingFilter getFilter() {
        return new StatusReportingFilter("Filter Instantiated");
    }

}
