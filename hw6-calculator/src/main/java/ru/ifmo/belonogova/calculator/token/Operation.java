package ru.ifmo.belonogova.calculator.token;

import ru.ifmo.belonogova.calculator.exception.CalculatorException;
import ru.ifmo.belonogova.calculator.visitor.TokenVisitor;

public abstract class Operation implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public abstract int eval(int left, int right) throws CalculatorException;

    public abstract int getPriority();

    @Override
    public String toString() {
        return getTokenType().toString();
    }
}
