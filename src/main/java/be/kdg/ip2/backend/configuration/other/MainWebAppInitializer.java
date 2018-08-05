package be.kdg.ip2.backend.configuration.other;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MainWebAppInitializer implements WebApplicationInitializer {
    @Value("${temp.folder}")
    private String tempFolder;

    @Value("${file.max.size}")
    private int maxUploadSize;

    public MainWebAppInitializer() {
        this.maxUploadSize *= 1024 * 1024; //Converts the number from config to MB
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Register a new dispatcher servlet
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("mvc", new DispatcherServlet(
                new GenericWebApplicationContext()));

        appServlet.setLoadOnStartup(1);


        // https://docs.oracle.com/javaee/7/api/javax/servlet/MultipartConfigElement.html
        // location - the directory location where files will be stored
        // maxFileSize - the maximum size allowed for uploaded files
        // maxRequestSize - the maximum size allowed for multipart/form-data requests
        // fileSizeThreshold - the size threshold after which files will be written to disk
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(tempFolder,
                maxUploadSize, maxUploadSize * 2, maxUploadSize);

        appServlet.setMultipartConfig(multipartConfigElement);
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
