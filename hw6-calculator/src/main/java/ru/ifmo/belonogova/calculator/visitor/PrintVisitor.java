package ru.ifmo.belonogova.calculator.visitor;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.Brace;
import ru.ifmo.belonogova.calculator.token.NumberToken;
import ru.ifmo.belonogova.calculator.token.Operation;

@Component
@Order(2)
public class PrintVisitor implements TokenVisitor {
    private StringBuilder builder;

    @Override
    public void visit(NumberToken token) {
        builder.append(token).append(' ');
    }

    @Override
    public void visit(Brace token) {
        builder.append(token).append(' ');
    }

    @Override
    public void visit(Operation token) {
        builder.append(token).append(' ');
    }

    @Override
    public TokensResponse applyOnSuccess(TokensResponse tokensResponse) {
        builder = new StringBuilder();
        tokensResponse.getTokens().forEach(token -> token.accept(this));
        System.out.println(builder.toString());

        return tokensResponse;
    }
}
