package org.web;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * CR1: General notes: 0. Add readme with description of the project, commands
 * to take evrything up, database processing, migration, etc. 1. Make checkstyle
 * plugings working with spring boot (Checkstyle, SpotBugs, PMD) 2. Add javadocs
 * to interfaces and classes. 3. Refactor the project to have multimodule
 * project with repos, services, api-services, controllers 4. Introduce database
 * migrations in detached module if any (I saw liquibase)
 */

@SpringBootApplication
@EnableCaching
@PropertySource({ "classpath:persistence-mysql.properties", "classpath:security.properties",
        "classpath:liquibase-migration.properties" })
@Import(org.security.config.SecurityConfig.class)
@ComponentScan({ "org.web.*", "org.security.*", "org.services.*", "org.db.*", "org.api.*" })
@EnableJpaRepositories("org.db.repository.springdata")
@EntityScan("org.db.*")
public class JtmainApplication {

    private static final int SMTP_PORT = 587;

    public static void main(final String[] args) {
        SpringApplication.run(JtmainApplication.class, args);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(SMTP_PORT);
        mailSender.setUsername("username");
        mailSender.setPassword("password");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

}
