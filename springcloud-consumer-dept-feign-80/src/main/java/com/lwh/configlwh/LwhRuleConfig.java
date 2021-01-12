package com.lwh.configlwh;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LwhRuleConfig {

    @Bean
    public IRule getRule()
    {
        return new LwhRoundRule();
    }
}
