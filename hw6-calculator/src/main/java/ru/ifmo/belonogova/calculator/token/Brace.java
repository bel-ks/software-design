package ru.ifmo.belonogova.calculator.token;

import ru.ifmo.belonogova.calculator.visitor.TokenVisitor;

public abstract class Brace implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return getTokenType().toString();
    }
}
