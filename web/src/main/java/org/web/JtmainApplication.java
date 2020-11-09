package org.web;

import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
//import com.iverbs.jtmain.repository.CourseRepositoryImpl;

/**
 * CR1: General notes:
 * 0. Add readme with description of the project, commands to take evrything up, database processing, migration, etc.
 * 1. Make checkstyle plugings working with spring boot (Checkstyle, SpotBugs, PMD)
 * 2. Add javadocs to interfaces and classes.
 * 3. Refactor the project to have multimodule project with repos, services, api-services, controllers
 * 4. Introduce database migrations in detached module if any (I saw liquibase)
 * 5.  
 */



@SpringBootApplication
@EnableCaching
@PropertySource({"classpath:persistence-mysql.properties", "classpath:security.properties", "classpath:liquibase-migration.properties"})
@Import(org.security.config.SecurityConfig.class)
@ComponentScan({"org.web.*", "org.security.*", "org.services.*", "org.db.*", "org.api.*"})
@EnableJpaRepositories("org.db.repository.springdata")
@EntityScan("org.db.*")  
public class JtmainApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtmainApplication.class, args);
    }

    @Autowired
    //CourseRepositoryImpl courseRepositoryImpl;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
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
