package com.newer.jay.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        PathPatternParser pathPatternParser = new PathPatternParser();
        pathPatternParser.setMatchOptionalTrailingSeparator(true);
        configurer.setPatternParser(pathPatternParser);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/article/*").addResourceLocations("file:" + uploadPath + "articleCover\\");
        registry.addResourceHandler("/upload/user/*").addResourceLocations("file:" + uploadPath + "userAvatar\\");
        registry.addResourceHandler("/upload/contact/*").addResourceLocations("file:" + uploadPath + "contactAvatar\\");
    }
}
