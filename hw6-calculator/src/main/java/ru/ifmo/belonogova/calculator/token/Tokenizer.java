package ru.ifmo.belonogova.calculator.token;

import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.state.SOEState;
import ru.ifmo.belonogova.calculator.token.state.State;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {private int position;
    private String expression;
    int cur;
    List<Token> tokens;
    private State state;

    public void parse(String expression) {
        tokens = new ArrayList<>();
        state = new SOEState(this);
        position = 0;
        this.expression = expression;
        next();
    }

    private void skipWhitespaces() {
        while (position < expression.length() && Character.isWhitespace(expression.charAt(position))) {
            position++;
        }
    }

    public int current() {
        return cur;
    }

    public void next() {
        skipWhitespaces();
        if (position == expression.length()) {
            cur = -1;
        } else {
            cur = expression.charAt(position++);
        }

        if (isNumber()) {
            state.onNumber();
            return;
        }

        if (isBrace()) {
            state.onBrace();
            return;
        }

        if (isOperation()) {
            state.onOperation();
            return;
        }

        if (isEnd()) {
            state.onEnd();
            return;
        }

        state.onUnexpected();
    }

    public TokensResponse getResult() throws IllegalStateException {
        return state.getResult();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void saveToken(Token token) {
        tokens.add(token);
    }

    public int getPosition() {
        return position;
    }

    private boolean isNumber() {
        return cur >= '0' && cur <= '9';
    }

    private boolean isBrace() {
        return cur == '(' || cur == ')';
    }

    private boolean isOperation() {
        return cur == '+' ||
               cur == '-' ||
               cur == '*' ||
               cur == '/';
    }

    private boolean isEnd() {
        return cur == -1;
    }
}
