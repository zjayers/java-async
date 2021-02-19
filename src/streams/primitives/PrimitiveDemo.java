package streams.primitives;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveDemo {
    public static void main(String[] args) {

        // Prints 1 to 4
        List<Integer> range = IntStream.range(1, 5)
                                       .boxed()
                                       .collect(Collectors.toList());
        System.out.println(range);

        // Prints 1 to 5
        List<Integer> rangeClosed = IntStream.rangeClosed(1, 5)
                                             .boxed()
                                             .collect(Collectors.toList());

        System.out.println(rangeClosed);
    }
}
