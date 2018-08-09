package be.kdg.ip2.carpoolingapplication.configuration.other;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Class used to create a DataSource object with the provided spring datasource from the config
 */
public class DataBaseConfig {
    @Configuration
    public class DatabaseConfig {
        @Bean
        @Primary //Indicates that a bean should be given preference when multiple candidates are qualified to autowire a single-valued dependency.
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }
    }
}
