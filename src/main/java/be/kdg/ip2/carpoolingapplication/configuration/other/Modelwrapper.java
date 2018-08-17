package be.kdg.ip2.carpoolingapplication.configuration.other;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * configuration wrapperclass for modelwrapper
 * used for easy conversion from entity to dto and back
 */
@Configuration
public class Modelwrapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
