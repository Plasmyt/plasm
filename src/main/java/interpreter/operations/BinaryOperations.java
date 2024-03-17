package interpreter.operations;

import parser.nodes.NumberNode;

public class BinaryOperations {
    public static int add(NumberNode left, NumberNode right) {
        return left.getValue() + right.getValue();
    }

    public static int subtract(NumberNode left, NumberNode right) {
        return left.getValue() - right.getValue();
    }
}