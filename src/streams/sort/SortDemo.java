package streams.sort;

import streams.model.Movie;

import java.util.Comparator;
import java.util.List;

public class SortDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20),
            new Movie("d", 10),
            new Movie("e", 15),
            new Movie("f", 20)
    );

    public static void main(String[] args) {

        System.out.println("Sort with Comparator lambda: By Title");
        movies.stream()
              .sorted(Comparator.comparing(Movie::getTitle))
              .forEach(System.out::println);

        System.out.println("Sort with Comparator lambda: By Title (DESC)");
        movies.stream()
              .sorted(Comparator.comparing(Movie::getTitle)
                                .reversed())
              .forEach(System.out::println);

        System.out.println("\nSort with Comparator lambda: By Likes");
        movies.stream()
              .sorted(Comparator.comparingInt(Movie::getLikes))
              .forEach(System.out::println);
    }
}
