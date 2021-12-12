package ru.ifmo.belonogova.calculator.token.state;

import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.Tokenizer;

public class EOEState extends State {
    public EOEState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void onNumber() {
        onAction();
    }

    @Override
    public void onBrace() {
        onAction();
    }

    @Override
    public void onOperation() {
        onAction();
    }

    @Override
    public void onEnd() {
        onAction();
    }

    @Override
    public void onUnexpected() {
        onAction();
    }

    @Override
    public TokensResponse getResult() throws IllegalStateException {
        return TokensResponse.success(tokenizer.getTokens());
    }

    private void onAction() {
        tokenizer.changeState(new ErrorState(tokenizer, "end exited", tokenizer.getPosition()));
    }
}
