package ru.ifmo.belonogova.calculator.token.state;

import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.Tokenizer;

public class ErrorState extends State {

    private final String cause;
    private final int position;

    public ErrorState(Tokenizer tokenizer, String cause, int position) {
        super(tokenizer);
        this.cause = cause;
        this.position = position;
    }

    @Override
    public void onNumber() {
        tokenizer.next();
    }

    @Override
    public void onBrace() {
        tokenizer.next();
    }

    @Override
    public void onOperation() {
        tokenizer.next();
    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onUnexpected() {

    }

    @Override
    public TokensResponse getResult() {
        return TokensResponse.error(cause + " on " + position + " position");
    }
}
