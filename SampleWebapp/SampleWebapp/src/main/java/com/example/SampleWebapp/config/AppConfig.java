package com.example.SampleWebapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.*"})
public class AppConfig extends AbstractAppConfig {

}