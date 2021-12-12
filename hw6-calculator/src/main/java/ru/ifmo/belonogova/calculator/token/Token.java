package ru.ifmo.belonogova.calculator.token;

import ru.ifmo.belonogova.calculator.visitor.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);

    TokenType getTokenType();

    default int getPriority() {
        return 0;
    }
}
