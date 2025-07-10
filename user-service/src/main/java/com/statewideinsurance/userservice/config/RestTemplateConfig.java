package com.statewideinsurance.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /* RestTemplate used for calling external API's
       Bean-->  spring creates an objects of all special classes(@controller,@service)
       and stored into Application Context --> once we want object spring inject in that class to
       application Content -->inside core spring Application Context part like memory

       --> RestTemplate --> is as class spring inside --> we have not created-->
       for that class object --> we created @Bean --> for telling spring to create object
       of RestTemplate and store it in your Application Context -->
       once i inject rest template object
       --> u also inject
     */


// no need here bec user  itself register -- no need external


  @Bean
    public RestTemplate GetRestTemplate(){
        return new RestTemplate();


}}
