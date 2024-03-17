package parser.nodes;

import lexer.token.Token;
import parser.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryOperationNode extends ASTNode {
    private final ASTNode leftOperand;
    private final Token operator;
    private final ASTNode rightOperand;

    public BinaryOperationNode(ASTNode leftOperand, Token operator, ASTNode rightOperand) {
        super(operator);
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    public ASTNode getLeftOperand() {
        return leftOperand;
    }

    public Token getOperator() {
        return operator;
    }

    public ASTNode getRightOperand() {
        return rightOperand;
    }

    @Override
    public List<ASTNode> getChildren() {
        List<ASTNode> children = new ArrayList<>();
        children.add(leftOperand);
        children.add(rightOperand);
        return children;
    }

    @Override
    public String toString() {
        return "(" + leftOperand.toString() + " " + operator.getValue() + " " + rightOperand.toString() + ")";
    }
}