package ru.ifmo.belonogova.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.ifmo.belonogova.calculator.token.Tokenizer;

@SpringBootApplication
public class Application {
    @Bean
    Tokenizer getTokenizer() {
        return new Tokenizer();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
