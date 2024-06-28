package com.ecom.ecommerse.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    HttpComponentsClientHttpRequestFactory factory= new HttpComponentsClientHttpRequestFactory();

    @Bean
    public RestTemplate restTemplate(){
        factory.setConnectTimeout(5000);
        factory.setConnectTimeout(10000);
        return new RestTemplate(factory);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
