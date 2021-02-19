package lambdas.suppliers;

import java.util.function.DoubleSupplier;
import java.util.stream.IntStream;

public class SupplierDemo {
    public static void main(String[] args) {
        DoubleSupplier getRandom = Math::random;
        Runnable printRandomNumber = () -> System.out.println(getRandom.getAsDouble());

        IntStream
                .range(1, 100)
                .forEach(i -> printRandomNumber.run());
    }
}
