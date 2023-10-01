package com.itca.cursify.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsContextConfig {
    @Bean
    public WebMvcConfigurer mvcConfigurer(){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/media/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }

}
