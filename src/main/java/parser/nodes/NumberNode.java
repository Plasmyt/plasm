package parser.nodes;

import lexer.token.Token;
import parser.ASTNode;

import java.util.Collections;
import java.util.List;

public class NumberNode extends ASTNode {
    private final int value;

    public NumberNode(Token token) {
        super(token);
        this.value = Integer.parseInt(token.getValue());
    }

    public int getValue() {
        return value;
    }

    @Override
    public List<ASTNode> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}