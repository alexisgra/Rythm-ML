package fr.unice.polytech.rythmML.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.logging.LogManager;

@SpringBootApplication
@EnableAsync
public class RythmMlShellApplication {
    public static void main(String[] args) {
        SpringApplication.run(RythmMlShellApplication.class, args);
    }

}
