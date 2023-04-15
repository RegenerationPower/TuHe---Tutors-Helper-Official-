package Project.TuHe.security;

import Project.TuHe.mappers.CustomUserDetailsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomUserDetailsMapper customUserDetailsMapper() {
        return new CustomUserDetailsMapper();
    }
}
