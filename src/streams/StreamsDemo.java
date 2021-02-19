package streams;

import streams.model.Movie;

import java.util.List;
import java.util.function.Consumer;

public class StreamsDemo {
    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20)
    );

    private static int count = 0;
    private static final Consumer<ProgrammingStyle> printCount = (programmingStyle) -> System.out.printf(
            "Count After %s Programming: %d%n",
            programmingStyle.name(),
            count);

    public static void main(String[] args) {
        countLikes_ImperativeProgramming();
        printCount.accept(ProgrammingStyle.IMPERATIVE);

        count = 0;

        countLikes_FunctionalProgramming();
        printCount.accept(ProgrammingStyle.FUNCTIONAL);
    }

    private static void countLikes_ImperativeProgramming() {
        for (Movie movie : movies) {
            if (movie.getLikes() > 10) {
                count++;
            }
        }
    }

    private static void countLikes_FunctionalProgramming() {
        long filteredCount = movies.stream()
                                   .filter(movie -> movie.getLikes() > 10)
                                   .count();

        count = (int) filteredCount;
    }

    private enum ProgrammingStyle {
        IMPERATIVE, FUNCTIONAL
    }
}
