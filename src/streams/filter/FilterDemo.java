package streams.filter;

import streams.model.Movie;

import java.util.List;

public class FilterDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20)
    );

    public static void main(String[] args) {
        movies.stream()
              .filter(movie -> movie.getLikes() > 10)
              .forEach(System.out::println);
    }
}
