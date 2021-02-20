package executive.example;

import executive.task.LongRunningTask;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class FlightService {

    public Stream<CompletableFuture<Quote>> getQuotes() {
        var sites = List.of("site1", "site2", "site3");
        return sites.stream().map(this::getQuote);
    }

    public CompletableFuture<Quote> getQuote(String siteName) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.printf("Getting Quote From: %s%n", siteName);
            LongRunningTask.simulateRandom();
            return new Quote(siteName, new Random().nextInt(100));
        });
    }
}
