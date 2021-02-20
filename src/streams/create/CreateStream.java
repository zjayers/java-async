package streams.create;

import java.util.Arrays;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        CreateStreamFromArray();
        CreateStreamWithStreamOf();
        CreateLimitedStreamWithGenerate();
        CreateLimitedStreamWithIterate();
    }

    private static void CreateStreamFromArray() {
        int[] nums = {1, 2, 3};
        Arrays.stream(nums)
              .forEach(System.out::println);
    }

    private static void CreateStreamWithStreamOf() {
        Stream.of(1, 2, 3, 4)
              .forEach(System.out::println);
    }

    private static void CreateLimitedStreamWithGenerate() {
        Stream.generate(Math::random)
              .limit(10)
              .forEach(System.out::println);
    }

    private static void CreateLimitedStreamWithIterate() {
        Stream.iterate(1, n -> n + 1)
              .limit(10)
              .forEach(System.out::println);
    }
}
