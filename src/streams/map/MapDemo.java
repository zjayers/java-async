package streams.map;

import streams.model.Movie;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MapDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20)
    );

    public static void main(String[] args) {
        map();
        flatMap();
    }

    private static void map() {
        movies.stream()
              .mapToInt(Movie::getLikes)
              .forEach(System.out::println);
    }

    private static void flatMap() {
        Supplier<Stream<List<Integer>>> listStream = () -> Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));


        // Print out the lists
        listStream.get()
                  .forEach(System.out::println);

        // Print out the individual numbers in both lists
        listStream.get()
                  .flatMap(Collection::stream)
                  .forEach(System.out::println);
    }
}
