package streams.grouping;

import streams.model.Movie;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20),
            new Movie("d", 10),
            new Movie("e", 15),
            new Movie("f", 20)
    );

    public static void main(String[] args) {

        Map<Integer, String> sortedMap = new LinkedHashMap<>();

        movies.stream()
              .collect(
                      Collectors.groupingBy(Movie::getLikes,
                              Collectors.mapping(
                                      Movie::getTitle,
                                      Collectors.joining(
                                              ","))
                      ))
              .entrySet()
              .stream()
              .sorted(Map.Entry.comparingByKey())
              .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        System.out.println(sortedMap);
    }
}
