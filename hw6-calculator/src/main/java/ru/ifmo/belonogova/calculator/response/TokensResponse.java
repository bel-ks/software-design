package ru.ifmo.belonogova.calculator.response;

import ru.ifmo.belonogova.calculator.token.Token;

import java.util.List;

public class TokensResponse {
    private final String errorMessage;
    private final List<Token> tokens;

    private TokensResponse(List<Token> tokens, String errorMessage) {
        this.errorMessage = errorMessage;
        this.tokens = tokens;
    }

    public static TokensResponse success(List<Token> tokens) {
        return new TokensResponse(tokens, null);
    }

    public static TokensResponse error(String errorMessage) {
        return new TokensResponse(null, errorMessage);
    }

    public boolean isSuccess() {
        return errorMessage == null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "TokensResponse{" +
                (isSuccess() ? "Success: " + getTokens() : "Error : " + getErrorMessage()) +
                "}";
    }
}
