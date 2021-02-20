package executive.future;

import executive.task.LongRunningTask;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FutureDemo {
    public static void main(String[] args) {
        System.out.println("-- Blocking Future Call --");
        runBlockingFuture();

        System.out.println("\n-- Non Blocking Future Call --");
        nonBlockingFuture();

        System.out.println("\n-- Non Blocking Future With Exceptions --");
        nonBlockingFutureWithExceptions();

        System.out.println("\n-- Non Blocking Future With Transforms --");
        nonBlockingFutureWithTransform();

        System.out.println("\n-- Non Blocking Future With Compose --");
        nonBlockingFutureWithCompose();

        System.out.println("\n-- Non Blocking Future With Combine --");
        nonBlockingFutureWithCombine();

        System.out.println("\n-- Wait For All Complete Futures --");
        waitForAllCompleteFutures();

        System.out.println("\n-- Wait For First Complete Future --");
        waitForFirstCompleteFuture();

        System.out.println("\n-- Handle Future Timeouts --");
        handleFutureTimeout();

    }

    private static void runBlockingFuture() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        AtomicLong startTime = new AtomicLong();
        long endTime;

        try {

            Future<Integer> future = service.submit(() -> {
                startTime.set(System.currentTimeMillis());
                System.out.println("- Starting Blocking Future...");
                LongRunningTask.simulate();
                return 1;
            });

            System.out.println("- Foreground Task 1 Complete");
            System.out.println("- Foreground Task 2 Complete");
            System.out.println("- Foreground Task 3 Complete");

            Integer futureResult = future.get();

            endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime.get();

            System.out.printf("- Completable Future Result: %d%n", futureResult);
            System.out.printf("- Thread was blocked for: %d milliseconds%n", executionTime);
            System.out.println("- Foreground Task 4 Complete");
            System.out.println("- Foreground Task 5 Complete");
            System.out.println("- Foreground Task 6 Complete");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    private static void nonBlockingFuture() {

        AtomicLong startTime = new AtomicLong();
        long endTime;

        Supplier<Integer> task = () -> {
            startTime.set(System.currentTimeMillis());
            System.out.println("- Starting Non Blocking Future...");
            LongRunningTask.simulate();
            return 1;
        };

        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(task)
                .thenAccept((value) -> System.out.printf("- Completable Future Result: %d%n", value))
                .thenRun(() -> System.out.println("- Future is complete and disposed"));

        System.out.println("- Foreground Task 1 Complete");
        System.out.println("- Foreground Task 2 Complete");
        System.out.println("- Foreground Task 3 Complete");

        endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime.get();

        System.out.printf("- Thread was blocked for: %d milliseconds%n", executionTime);
        System.out.println("- Foreground Task 4 Complete");
        System.out.println("- Foreground Task 5 Complete");
        System.out.println("- Foreground Task 6 Complete");

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void nonBlockingFutureWithExceptions() {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting data...");
            throw new IllegalStateException();
        });

        try {
            CompletableFuture<Object> exceptionHandledFuture = future.exceptionally(ex -> List.of(1, 2, 3));
            Object result = exceptionHandledFuture.get();

            System.out.printf("Future Result: %s%n", result);
        } catch (InterruptedException | ExecutionException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    private static void nonBlockingFutureWithTransform() {

        Supplier<List<Integer>> createDataList = () -> List.of(1, 2, 3);
        Function<List<Integer>, Integer> extractFirstElement = (nums) -> nums.get(0);
        Consumer<Integer> print = System.out::println;

        CompletableFuture.supplyAsync(createDataList)
                         .thenApply(extractFirstElement)
                         .thenAccept(print);
    }

    private static void nonBlockingFutureWithCompose() {

        Supplier<CompletableFuture<String>> getEmailFromDatabaseAsync = () -> CompletableFuture.supplyAsync(() -> "test@test.com");
        Function<String, CompletableFuture<List<String>>> getBlogPostsByEmailAsync = (email) -> CompletableFuture.supplyAsync(
                () -> List.of("Blog 1", "Blog 2", "Blog 3"));

        getEmailFromDatabaseAsync.get()
                                 .thenCompose(getBlogPostsByEmailAsync)
                                 .thenAccept(System.out::println);
    }

    private static void nonBlockingFutureWithCombine() {

        Supplier<String> getUSDPriceAsString = () -> "20USD";
        Supplier<Float> getUSDExchangeRate = () -> 0.9f;

        CompletableFuture<Integer> getUSDFuture = CompletableFuture.supplyAsync(getUSDPriceAsString)
                                                                   .thenApply(str -> str.replace("USD", ""))
                                                                   .thenApply(Integer::parseInt);

        CompletableFuture<Float> getExchangeRateFuture = CompletableFuture.supplyAsync(getUSDExchangeRate);


        getUSDFuture.thenCombine(getExchangeRateFuture, (priceInUSD, exchangeRate) -> priceInUSD * exchangeRate)
                    .thenAccept(System.out::println);
    }

    private static void waitForAllCompleteFutures() {
        var first = CompletableFuture.supplyAsync(() -> 1);
        var second = CompletableFuture.supplyAsync(() -> 2);
        var third = CompletableFuture.supplyAsync(() -> 3);

        CompletableFuture<Void> all = CompletableFuture.allOf(first, second, third);

        all.thenRun(() -> {

            try {
                System.out.printf("Result of Future 1: %d%n", first.get());
                System.out.printf("Result of Future 2: %d%n", second.get());
                System.out.printf("Result of Future 3: %d%n", third.get());
                System.out.println("All tasks complete");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        });
    }

    private static void waitForFirstCompleteFuture() {

        var slowFuture = CompletableFuture.supplyAsync(() -> {
            LongRunningTask.simulate();
            return "Slow Link Finished First";
        });

        var fastFuture = CompletableFuture.supplyAsync(() -> "Fast Link Finished First");

        CompletableFuture<Object> future = CompletableFuture.anyOf(slowFuture, fastFuture);

        future.thenAccept(System.out::println);

    }

    private static void handleFutureTimeout() {

        var timeoutInSeconds = 1;
        var timeoutMessage = "Date Retrieval Timed Out";

        var longRunningFuture = CompletableFuture.supplyAsync(() -> {
            LongRunningTask.simulate();
            return "Date Retrieved";
        });

        CompletableFuture<Void> timedOutFuture = longRunningFuture.completeOnTimeout(timeoutMessage,
                timeoutInSeconds,
                TimeUnit.SECONDS)
                                                                  .thenAccept(System.out::println);

        try {
            timedOutFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
