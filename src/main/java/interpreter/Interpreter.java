package interpreter;

import lexer.token.TokenType;
import parser.ASTNode;
import parser.nodes.BinaryOperationNode;
import parser.nodes.NumberNode;
import parser.nodes.StringNode;

public class Interpreter {
    private final Environment environment;

    public Interpreter() {
        this.environment = new Environment();
    }

    public void interpret(ASTNode node) {
        if (node instanceof BinaryOperationNode) {
            interpretBinaryOperation((BinaryOperationNode) node);
        } else if (node instanceof NumberNode) {
            interpretNumber((NumberNode) node);
        } else if (node instanceof StringNode) {
            interpretString((StringNode) node);
        }
    }

    private void interpretBinaryOperation(BinaryOperationNode node) {
        ASTNode leftOperand = node.getLeftOperand();
        ASTNode rightOperand = node.getRightOperand();

        interpret(leftOperand);

        if (!(leftOperand instanceof NumberNode)) {
            throw new IllegalArgumentException("Left operand must be of type NumberNode.");
        }

        if (rightOperand instanceof BinaryOperationNode) {
            interpretBinaryOperation((BinaryOperationNode) rightOperand);
            rightOperand = ((BinaryOperationNode) rightOperand).getRightOperand();
        }

        interpret(rightOperand);

        if (!(rightOperand instanceof NumberNode)) {
            throw new IllegalArgumentException("Right operand must be of type NumberNode.");
        }

        NumberNode rightNumberNode = (NumberNode) rightOperand;
        NumberNode leftNumberNode = (NumberNode) leftOperand;

        if (node.getOperator().getType() == TokenType.PLUS) {
            int result = leftNumberNode.getValue() + rightNumberNode.getValue();
            System.out.println("Result of addition: " + result);
        } else if (node.getOperator().getType() == TokenType.MINUS) {
            int result = leftNumberNode.getValue() - rightNumberNode.getValue();
            System.out.println("Result of subtraction: " + result);
        }
    }

    private void interpretNumber(NumberNode node) {
        System.out.println("Interpreting NumberNode: " + node.getValue());
    }

    private void interpretString(StringNode node) {
        System.out.println("Interpreting StringNode: " + node.getValue());
    }
}