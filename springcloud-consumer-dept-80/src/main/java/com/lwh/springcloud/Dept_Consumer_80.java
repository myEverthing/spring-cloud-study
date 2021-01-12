package com.lwh.springcloud;

import com.lwh.configlwh.LwhRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = LwhRuleConfig.class)
public class Dept_Consumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(Dept_Consumer_80.class, args);
    }
}
