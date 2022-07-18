package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class democonf {
    @Bean
    public SimpleDateFormat simpleFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
