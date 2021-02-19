package lambdas.operators;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class OperatorDemo {
    private static final IntBinaryOperator add = Integer::sum;
    private static final IntUnaryOperator square = (a) -> a * a;

    public static void main(String[] args) {
        int sum = add.applyAsInt(1, 2);
        int squared = square.applyAsInt(sum);
        System.out.printf("Squared Result: %d%n", squared);
    }
}
