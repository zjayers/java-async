package lambdas.consumers;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3);

        Consumer<Integer> print = System.out::println;
        Consumer<Integer> square = (item) -> System.out.println(item * item);
        Consumer<Integer> cube = (item) -> System.out.println(item * item * item);

        list.forEach(print
                .andThen(square)
                .andThen(cube));
    }
}
