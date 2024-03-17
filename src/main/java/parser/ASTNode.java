package parser;

import lexer.token.Token;

import java.util.List;

public abstract class ASTNode {
    protected final Token token;

    public ASTNode(Token token) {
        this.token = token;
    }

    public abstract List<ASTNode> getChildren();

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token.getValue();
    }
}