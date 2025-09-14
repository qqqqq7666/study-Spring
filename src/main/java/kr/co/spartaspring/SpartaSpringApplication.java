package kr.co.spartaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpartaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaSpringApplication.class, args);
    }

}
