package ru.ifmo.belonogova.calculator.token;

public class CloseBrace extends Brace {
    @Override
    public TokenType getTokenType() {
        return TokenType.RIGHT_BR;
    }
}
