package interpreter.operations;

import parser.nodes.NumberNode;

public class ComparisonOperations {
    public static boolean lessThan(NumberNode left, NumberNode right) {
        return left.getValue() < right.getValue();
    }

    public static boolean lessThanOrEqual(NumberNode left, NumberNode right) {
        return left.getValue() <= right.getValue();
    }

    public static boolean greaterThan(NumberNode left, NumberNode right) {
        return left.getValue() > right.getValue();
    }

    public static boolean greaterThanOrEqual(NumberNode left, NumberNode right) {
        return left.getValue() >= right.getValue();
    }

    public static boolean equalTo(NumberNode left, NumberNode right) {
        return left.getValue() == right.getValue();
    }
}