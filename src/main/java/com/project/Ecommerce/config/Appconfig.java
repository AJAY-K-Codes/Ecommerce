package com.project.Ecommerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

    @Bean
    public ModelMapper modeemapper(){
        return new ModelMapper();
    }
}
