package interpreter.operations;

import parser.nodes.NumberNode;

public class UnaryOperations {
    public static int negate(NumberNode node) {
        return -node.getValue();
    }
}