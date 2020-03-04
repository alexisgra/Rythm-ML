package fr.unice.polytech.rythmML.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RythmMlShellApplication {
    public static void main(String[] args) {
        SpringApplication.run(RythmMlShellApplication.class, args);
    }

}
