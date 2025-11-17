package com.giovannicaggianella.toon.config;

import com.giovannicaggianella.toon.processor.ToonAnnotationProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring Boot auto-configuration for Toon.
 * Automatically registers beans required for TOON serialization using JToon.
 */
@Configuration
public class ToonAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ToonAutoConfiguration.class);

    /**
     * Creates a bean for TOON annotation processing.
     *
     * @return an instance of ToonAnnotationProcessor
     */
    @Bean
    @ConditionalOnMissingBean
    public ToonAnnotationProcessor toonAnnotationProcessor() {
        logger.info("Initializing ToonAnnotationProcessor");
        return new ToonAnnotationProcessor();
    }

}
