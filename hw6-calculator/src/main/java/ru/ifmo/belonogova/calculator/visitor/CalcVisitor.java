package ru.ifmo.belonogova.calculator.visitor;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ifmo.belonogova.calculator.exception.CalculatorException;
import ru.ifmo.belonogova.calculator.response.TokensResponse;
import ru.ifmo.belonogova.calculator.token.Brace;
import ru.ifmo.belonogova.calculator.token.NumberToken;
import ru.ifmo.belonogova.calculator.token.Operation;

import java.util.Stack;

@Component
@Order(3)
public class CalcVisitor implements TokenVisitor {

    private Stack<Integer> calculatorEvalStack;
    private TokensResponse tokensResponse;

    @Override
    public void visit(NumberToken token) {
        calculatorEvalStack.push(token.getNum());
    }

    @Override
    public void visit(Brace token) {
        tokensResponse = TokensResponse.error("Unexpected brace in calc visitor");
    }

    @Override
    public void visit(Operation token) {
        if (calculatorEvalStack.size() < 2) {
            tokensResponse = TokensResponse.error("Wrong expression");
        } else {
            int right = calculatorEvalStack.pop();
            int left = calculatorEvalStack.pop();
            try {
                calculatorEvalStack.push(token.eval(left, right));
            } catch (CalculatorException e) {
                tokensResponse = TokensResponse.error(e.getMessage());
            }
        }
    }

    @Override
    public TokensResponse applyOnSuccess(TokensResponse tokensResponse) {
        calculatorEvalStack = new Stack<>();
        this.tokensResponse = null;
        tokensResponse.getTokens().forEach(token -> token.accept(this));
        if (this.tokensResponse == null) {
            if (calculatorEvalStack.isEmpty()) {
                return TokensResponse.error("Wrong expression");
            }

            int ans = calculatorEvalStack.pop();
            if (!calculatorEvalStack.isEmpty()) {
                return TokensResponse.error("Wrong expression");
            }

            System.out.println(ans);

            return tokensResponse;
        }

        return this.tokensResponse;
    }
}
