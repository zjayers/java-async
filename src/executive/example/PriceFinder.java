package executive.example;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriceFinder {
    public static void main(String[] args) {
        var startTime = LocalTime.now();
        var flightService = new FlightService();

        Function<CompletableFuture<Quote>, CompletableFuture<Void>> printQuoteFuture = quoteCompletableFuture -> quoteCompletableFuture
                .thenAccept(System.out::println);

        var quoteFutures = flightService.getQuotes()
                                        .map(printQuoteFuture)
                                        .collect(Collectors.toList()).toArray(CompletableFuture[]::new);

        try {

            CompletableFuture.allOf(quoteFutures).thenRun(() -> {
                var endTime = LocalTime.now();
                Duration runtime = Duration.between(startTime, endTime);
                System.out.printf("Retrieved All Quotes In: %dms%n", runtime.toMillis());
            }).get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
