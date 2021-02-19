package streams.collectors;

import streams.model.Movie;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20),
            new Movie("d", 10),
            new Movie("e", 15),
            new Movie("f", 20)
    );

    public static void main(String[] args) {
        // To List
        List<Movie> movieList = movies.stream()
                                      .filter(movie -> movie.getLikes() > 10)
                                      .collect(Collectors.toList());

        // To Set
        Set<Movie> movieSet = movies.stream()
                                    .filter(movie -> movie.getLikes() > 10)
                                    .collect(Collectors.toSet());

        // To Map
        Map<String, Movie> movieMap = movies.stream()
                                            .filter(movie -> movie.getLikes() > 10)
                                            .collect(Collectors.toMap(Movie::getTitle, Function.identity()));

        Integer totalLikes = movies.stream()
                                   .filter(movie -> movie.getLikes() > 10)
                                   .collect(Collectors.summingInt(Movie::getLikes));

        // Summarizing
        IntSummaryStatistics intSummaryStatistics = movies.stream()
                                                          .filter(movie -> movie.getLikes() > 10)
                                                          .collect(Collectors.summarizingInt(Movie::getLikes));

        // Collect with delimiter
        String movieNames = movies.stream()
                                  .filter(movie -> movie.getLikes() > 10)
                                  .map(Movie::getTitle)
                                  .collect(Collectors.joining(","));

        System.out.println(movieList);
        System.out.println(movieMap);
        System.out.println(totalLikes);
        System.out.println(intSummaryStatistics);
        System.out.println(movieNames);
    }

}
