package com.reach.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Interceptor configuration
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(true);
        configurer.setUseTrailingSlashMatch(true);
    }

    @Bean(name="multipartResolver")
    public MultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }
}
