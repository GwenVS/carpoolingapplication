package be.kdg.ip2.carpoolingapplication.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

    @Value("${file.max.size}")
    private int maxUploadSize;

    public WebConfig() {
        maxUploadSize *= 1024 * 1024; //MB
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        //https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/multipart/MultipartResolver.html
        //https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/multipart/commons/CommonsMultipartResolver.html
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(maxUploadSize);
        return multipartResolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
}
