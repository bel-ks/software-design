package ru.ifmo.belonogova.calculator.token;

public class SubOperation extends Operation {
    @Override
    public int eval(int left, int right) {
        return left - right;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.SUB_OP;
    }
}
