package ru.ifmo.belonogova.calculator.token.state;

import ru.ifmo.belonogova.calculator.token.NumberToken;
import ru.ifmo.belonogova.calculator.token.Tokenizer;

public class NumberState extends State {
    private int number = 0;

    public NumberState(Tokenizer tokenizer) {
        super(tokenizer);
        number += tokenizer.current() - '0';
    }

    @Override
    public void onNumber() {
        number *= 10;
        number += tokenizer.current() - '0';
        tokenizer.next();
    }

    @Override
    public void onBrace() {
        State state = onNonNumber();
        state.onBrace();
    }

    @Override
    public void onOperation() {
        State state = onNonNumber();
        state.onOperation();
    }

    private State onNonNumber() {
        State state = new SOEState(tokenizer);
        tokenizer.changeState(state);
        tokenizer.saveToken(new NumberToken(number));

        return state;
    }

    @Override
    public void onEnd() {
        super.onEnd();
        tokenizer.saveToken(new NumberToken(number));
    }
}
