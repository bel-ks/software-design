package ru.ifmo.belonogova.calculator.token;

import ru.ifmo.belonogova.calculator.exception.CalculatorException;

public class DivOperation extends Operation {
    @Override
    public int eval(int left, int right) throws CalculatorException {
        if (right == 0)
            throw new CalculatorException("Division by zero is not allowed");

        return left / right;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.DIV_OP;
    }
}
