package streams.peek;

import streams.model.Movie;

import java.util.List;
import java.util.Locale;

public class PeekDemo {
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
              .filter(movie -> movie.getLikes() > 10)
              .peek(x -> System.out.println("Filtered:" + x))
              .map(Movie::getTitle)
              .peek(x -> System.out.println("Mapped:" + x))
              .forEach(x -> System.out.println("Final:" + x.toUpperCase(Locale.ROOT)));
    }
}
