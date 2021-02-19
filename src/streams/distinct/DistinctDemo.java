package streams.distinct;

import streams.model.Movie;

import java.util.List;

public class DistinctDemo {
    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20),
            new Movie("d", 10),
            new Movie("e", 15),
            new Movie("f", 20)
    );

    public static void main(String[] args) {
        movies.stream()
              .mapToInt(Movie::getLikes)
              .distinct()
              .forEach(System.out::println);
    }
}
