package com.basic.myspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 먼저 로딩되게함 ( ex mobile/index.html로 들어올경우)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**")
//반드시 mobile 다음에 / 을 주어야 한다.
                .addResourceLocations("classpath:/mobile/")
                .setCachePeriod(20);//20초
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 리엑트나 vue js등의 프론트엔드 서버가 다를경우 연결시켜주는것
//                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("*")
                .allowedMethods("*");;
    }

}
