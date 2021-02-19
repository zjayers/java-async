package streams.partition;

import streams.model.Movie;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionDemo {

    private static final List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("b", 15),
            new Movie("c", 20),
            new Movie("d", 10),
            new Movie("e", 15),
            new Movie("f", 20)
    );

    public static void main(String[] args) {
        Map<Boolean, String> partitions = movies.stream()
                                                .collect(Collectors.partitioningBy(movie -> movie.getLikes() >= 20,
                                                        Collectors.mapping(Movie::getTitle, Collectors.joining(","))));
        System.out.println(partitions);
    }
}
