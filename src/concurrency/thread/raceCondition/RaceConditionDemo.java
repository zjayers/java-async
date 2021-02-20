package concurrency.thread.raceCondition;

import concurrency.thread.status.*;
import concurrency.thread.tasks.DownloadFileTaskWithStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RaceConditionDemo {
    public static void main(String[] args) {

        // This method will not correctly update the DownloadStatus object
        System.out.println("-- Thread Race With Issue --");
        ThreadRaceWithIssue();

        // These method will correctly update the DownloadStatus object
        System.out.println("\n-- Thread Race With Confinement --");
        ThreadRaceWithConfinement();

        System.out.println("\n-- Thread Race With Locks --");
        ThreadRaceWithLocks();

        System.out.println("\n-- Thread Race With Synchronization --");
        ThreadRaceWithSynchronization();

        System.out.println("\n-- Thread Race With Volatiles --");
        ThreadRaceWithVolatiles();

        System.out.println("\n-- Thread Race With Atomics --");
        ThreadRaceWithAtomics();

        System.out.println("\n-- Thread Race With Atomic Adders --");
        ThreadRaceWithAdders();

        System.out.println("\n-- Thread Race With Synchronized Collection --");
        ThreadRaceWithSynchronizedCollection();

        System.out.println("\n-- Thread Race With Concurrent Collection --");
        ThreadRaceWithConcurrentCollection();
    }

    private static void ThreadRaceWithIssue() {
        runThreadRace(new DownloadStatus());
    }

    private static void ThreadRaceWithConfinement() {
        List<Thread> threads = new ArrayList<>();
        List<DownloadFileTaskWithStatus> downloadFileTasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            var task = new DownloadFileTaskWithStatus(i, new DownloadStatus(), 10_000);
            downloadFileTasks.add(task);

            var thread = new Thread(task);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Combine total bytes in all downloads
        String bytesKey = "Bytes";
        String filesKey = "Files";

        Map<String, Integer> totalDownloads = new HashMap<>() {{
            put(bytesKey, 0);
            put(filesKey, 0);
        }};


        downloadFileTasks.stream()
                         .map(DownloadFileTaskWithStatus::getStatus)
                         .forEach(status -> {
                             totalDownloads.merge(bytesKey, status.getTotalBytes(), Integer::sum);
                             totalDownloads.merge(filesKey, status.getTotalFiles(), Integer::sum);
                         });


        System.out.printf("-- Total Bytes Downloaded: %d%n", totalDownloads.get(bytesKey));
        System.out.printf("-- Total Files Downloaded: %d%n", totalDownloads.get(filesKey));
    }

    private static void ThreadRaceWithLocks() {
        runThreadRace(new DownloadStatusWithLock());
    }

    private static void ThreadRaceWithSynchronization() {
        runThreadRace(new DownloadStatusWithSynchronized());
    }

    private static void ThreadRaceWithVolatiles() {
        var downloadStatus = new DownloadStatusWithVolatiles();

        Thread downloadThread = new Thread(new DownloadFileTaskWithStatus(1, downloadStatus, 1_000_000_000));
        Thread statusCheckThread = new Thread(() -> {

            System.out.println("-- Waiting for thread completion...");
            int count = 0;

            while (!downloadStatus.isDone()) {
                System.out.printf("-- While loop executions: %d%n", ++count);

                // Wait until the thread notifies it is complete
                synchronized (downloadStatus) {
                    try {
                        downloadStatus.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.printf("-- Total Bytes Downloaded: %d%n", downloadStatus.getTotalBytes());
            System.out.printf("-- Total Files Downloaded: %d%n", downloadStatus.getTotalFiles());
        });

        downloadThread.start();
        statusCheckThread.start();

        try {
            downloadThread.join();
            statusCheckThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void ThreadRaceWithAtomics() {
        runThreadRace(new DownloadStatusWithAtomics());
    }

    private static void ThreadRaceWithAdders() {
        runThreadRace(new DownloadStatusWithAdders());
    }

    private static void ThreadRaceWithSynchronizedCollection() {
        runCollectionRace(Collections.synchronizedMap(new HashMap<>()));
    }

    private static void ThreadRaceWithConcurrentCollection() {
        runCollectionRace(new ConcurrentHashMap<>());
    }

    private static void runCollectionRace(Map<String, Integer> collection) {
        Thread thread1 = new Thread(() -> {
            collection.put("a", 1);
            collection.put("b", 2);
            collection.put("c", 3);
        });

        Thread thread2 = new Thread(() -> {
            collection.put("d", 4);
            collection.put("e", 5);
            collection.put("f", 6);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(collection);
    }

    private static void runThreadRace(Status downloadStatus) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            var thread = new Thread(new DownloadFileTaskWithStatus(i, downloadStatus, 10_000));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("-- Total Bytes Downloaded: %d%n", downloadStatus.getTotalBytes());
        System.out.printf("-- Total Files Downloaded: %d%n", downloadStatus.getTotalFiles());

    }
}
