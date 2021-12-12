package ru.ifmo.belonogova.calculator.visitor;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Component
@Order(1)
public class ParserVisitor implements TokenVisitor {

    private Stack<Token> calculatorEvalStack;
    private List<Token> tokens;

    @Override
    public void visit(NumberToken token) {
        tokens.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.getTokenType() == TokenType.LEFT_BR) {
            calculatorEvalStack.push(token);
        } else {
            Token cur = calculatorEvalStack.pop();
            while (cur.getTokenType() != TokenType.LEFT_BR) {
                tokens.add(cur);
                cur = calculatorEvalStack.pop();
            }
        }
    }

    @Override
    public void visit(Operation token) {
        if (!calculatorEvalStack.isEmpty()) {
            Token cur = calculatorEvalStack.peek();
            while (!calculatorEvalStack.isEmpty() && token.getPriority() <= cur.getPriority()) {
                tokens.add(calculatorEvalStack.pop());
                if (!calculatorEvalStack.isEmpty()) {
                    cur = calculatorEvalStack.peek();
                }
            }
        }

        calculatorEvalStack.push(token);
    }

    @Override
    public TokensResponse applyOnSuccess(TokensResponse tokensResponse) {
        calculatorEvalStack = new Stack<>();
        tokens = new ArrayList<>();
        tokensResponse.getTokens().forEach(token -> token.accept(this));

        while (!calculatorEvalStack.isEmpty()) {
            Token token = calculatorEvalStack.pop();
            tokens.add(token);
        }

        return TokensResponse.success(tokens);
    }

}
