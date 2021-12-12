package ru.ifmo.belonogova.calculator.token.state;

import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.Tokenizer;

public abstract class State {
    protected final Tokenizer tokenizer;

    public State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void onEnd() {
        tokenizer.changeState(new EOEState(tokenizer));
    }

    public void onUnexpected() {
        tokenizer.changeState(new ErrorState(tokenizer, "unexpected symbol", tokenizer.getPosition()));
        tokenizer.next();
    }

    public TokensResponse getResult() {
        return TokensResponse.error("Unexpected call");
    }

    public abstract void onNumber();

    public abstract void onBrace();

    public abstract void onOperation();
}
