package com.lwh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Dept_Consumer_Histrix_Dashboard_9001 {
    public static void main(String[] args) {
        SpringApplication.run(Dept_Consumer_Histrix_Dashboard_9001.class, args);
    }
}
