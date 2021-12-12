package ru.ifmo.belonogova.calculator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.Tokenizer;
import ru.ifmo.belonogova.calculator.visitor.TokenVisitor;

import java.util.List;

@Component
@Order(2)
public class VisitorsComponent implements CommandLineRunner {
    private final List<TokenVisitor> visitors;
    private final Tokenizer tokenizer;

    public VisitorsComponent(List<TokenVisitor> visitors, Tokenizer tokenizer) {
        this.visitors = visitors;
        this.tokenizer = tokenizer;
    }

    @Override
    public void run(String... args) {
        TokensResponse tokensResponse = tokenizer.getResult();

        for (TokenVisitor visitor : visitors) {
            tokensResponse = visitor.apply(tokensResponse);;
        }

        if (!tokensResponse.isSuccess()) {
            System.out.println(tokensResponse.getErrorMessage());
        }
    }
}
