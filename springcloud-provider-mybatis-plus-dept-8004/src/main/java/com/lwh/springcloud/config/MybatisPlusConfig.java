package com.lwh.springcloud.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor()
  {
      return new OptimisticLockerInterceptor();
  }

  @Bean
  public PaginationInterceptor paginationInterceptor()
  {
      return new PaginationInterceptor();
  }

  @Bean
  public ISqlInjector sqlInjector()
  {
      return new LogicSqlInjector();
  }

  @Bean
  public PerformanceInterceptor performanceInterceptor(){
      PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();

      performanceInterceptor.setMaxTime(100);
      performanceInterceptor.setFormat(true);
      return performanceInterceptor;
  }
}
