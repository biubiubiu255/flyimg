package com.flyimg.components.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * 所有filter统一调度
 * @author Asher
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new ExceptionFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("notice","/error");
        registration.addInitParameter("notice","/error2");
        registration.setName("exceptionFilter");
        registration.setOrder(0);
        return registration;
    }

   @Bean
    public FilterRegistrationBean xssFilterRegistration1() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("notice","/error");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }



}
