package streams.slice;

import streams.model.Movie;

import java.util.List;

public class SliceDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20),
            new Movie("d", 10),
            new Movie("e", 15),
            new Movie("f", 20)
    );

    public static void main(String[] args) {
        System.out.println("-- Limit --");
        limit();
        System.out.println("\n-- Skip --");
        skip();
        System.out.println("\n-- Take While --");
        takeWhile();
        System.out.println("\n-- Drop While --");
        dropWhile();
    }

    private static void limit() {
        movies.stream()
              .limit(1)
              .forEach(System.out::println);
    }

    private static void skip() {
        movies.stream()
              .skip(1)
              .forEach(System.out::println);
    }

    private static void takeWhile() {
        movies.stream()
              .takeWhile(movie -> movie.getLikes() <= 15)
              .forEach(System.out::println);
    }

    private static void dropWhile() {
        movies.stream()
              .dropWhile(movie -> movie.getLikes() <= 15)
              .forEach(System.out::println);
    }
}
