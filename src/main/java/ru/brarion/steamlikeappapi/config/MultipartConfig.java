package ru.brarion.steamlikeappapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.servlet.multipart")
public class MultipartConfig {

    private String location;
}
