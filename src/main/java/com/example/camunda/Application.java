package com.example.camunda;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = "classpath:/processes/*.bpmn")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
