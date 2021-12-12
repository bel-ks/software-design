package ru.ifmo.belonogova.calculator.token.state;

import ru.ifmo.belonogova.calculator.token.*;

public class SOEState extends State {

    public SOEState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void onNumber() {
        tokenizer.changeState(new NumberState(tokenizer));
        tokenizer.next();
    }

    @Override
    public void onBrace() {
        switch (tokenizer.current()) {
            case '(':
                tokenizer.saveToken(new OpenBrace());
                tokenizer.next();
                break;
            case ')':
                tokenizer.saveToken(new CloseBrace());
                tokenizer.next();
                break;
            default:
                tokenizer.changeState(new ErrorState(tokenizer, "'(' or ')' expected in brace state", tokenizer.getPosition()));
                tokenizer.next();
        }
    }

    @Override
    public void onOperation() {
        switch (tokenizer.current()) {
            case '-':
                tokenizer.saveToken(new SubOperation());
                tokenizer.next();
                break;
            case '+':
                tokenizer.saveToken(new AddOperation());
                tokenizer.next();
                break;
            case '*':
                tokenizer.saveToken(new MulOperation());
                tokenizer.next();
                break;
            case '/':
                tokenizer.saveToken(new DivOperation());
                tokenizer.next();
                break;
            default:
                tokenizer.changeState(new ErrorState(tokenizer, "'+', '-', '*' or '/' expected in operation state", tokenizer.getPosition()));
                tokenizer.next();
        }
    }

}
