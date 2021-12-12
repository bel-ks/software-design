package ru.ifmo.belonogova.calculator.token;

public class OpenBrace extends Brace {
    @Override
    public TokenType getTokenType() {
        return TokenType.LEFT_BR;
    }
}
