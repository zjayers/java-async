package streams.reduce;

import streams.model.Movie;

import java.util.Comparator;
import java.util.List;

public class ReduceDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20),
            new Movie("d", 10),
            new Movie("e", 15),
            new Movie("f", 20)
    );

    public static void main(String[] args) {
        System.out.println("-- Count --");
        count();

        System.out.println("\n-- Any Match --");
        anyMatch();

        System.out.println("\n-- All Match --");
        allMatch();

        System.out.println("\n-- None Match --");
        noneMatch();

        System.out.println("\n-- Find First --");
        findFirst();

        System.out.println("\n-- Find Any --");
        findAny();

        System.out.println("\n-- Max --");
        max();

        System.out.println("\n-- Min --");
        min();

        System.out.println("\n-- Add All Likes --");
        addAllLikes();

    }

    private static void count() {
        long count = movies.stream()
                           .count();
        System.out.println(count);
    }

    private static void anyMatch() {
        boolean movieNamesMatch = movies.stream()
                                        .anyMatch(movie -> movie.getTitle()
                                                                .equals("a"));
        System.out.println(movieNamesMatch);
    }

    private static void allMatch() {
        boolean movieNamesMatch = movies.stream()
                                        .allMatch(movie -> movie.getTitle()
                                                                .equals("a"));
        System.out.println(movieNamesMatch);
    }

    private static void noneMatch() {
        boolean movieNamesMatch = movies.stream()
                                        .noneMatch(movie -> movie.getTitle()
                                                                 .equals("g"));
        System.out.println(movieNamesMatch);
    }

    private static void findFirst() {
        movies.stream()
              .findFirst()
              .ifPresent(System.out::println);
    }

    private static void findAny() {
        movies.stream()
              .findAny()
              .ifPresent(System.out::println);
    }

    private static void max() {
        movies.stream()
              .max(Comparator.comparingInt(Movie::getLikes))
              .ifPresent(System.out::println);
    }

    private static void min() {
        movies.stream()
              .min(Comparator.comparingInt(Movie::getLikes))
              .ifPresent(System.out::println);
    }

    private static void addAllLikes() {
        movies.stream()
              .mapToInt(Movie::getLikes)
              .reduce(Integer::sum)
              .ifPresent(System.out::println);
    }
}
