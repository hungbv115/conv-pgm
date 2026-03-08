package com.process.old.config;

import com.process.old.thymeleaf.LineColumnDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;

/**
 * Spring configuration that registers the custom LineColumnDialect.
 * Spring Boot will automatically pick this up and add it to the Thymeleaf template engine.
 */
@Configuration
public class ThymeleafDialectConfig {

    @Bean
    public IDialect lineColumnDialect() {
        return new LineColumnDialect();
    }
}


