package ru.ifmo.belonogova.calculator.visitor;

import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.Brace;
import ru.ifmo.belonogova.calculator.token.NumberToken;
import ru.ifmo.belonogova.calculator.token.Operation;

public interface TokenVisitor {
    void visit(NumberToken token);

    void visit(Brace token);

    void visit(Operation token);

    TokensResponse applyOnSuccess(TokensResponse tokensResponse);

    default TokensResponse apply(TokensResponse tokensResponse) {
        if (!tokensResponse.isSuccess())
            return tokensResponse;

        return applyOnSuccess(tokensResponse);
    }
}
