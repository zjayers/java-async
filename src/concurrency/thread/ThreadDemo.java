package concurrency.thread;

import concurrency.thread.tasks.DownloadFileTask;
import concurrency.thread.tasks.InterruptFileTask;

import java.util.stream.IntStream;

public class ThreadDemo {
    public static void main(String[] args) {
        // Print out the current Thread name
        System.out.printf("Main Thread Name: %s%n", Thread.currentThread().getName());

        runDownloadFileTask();
        runInterruptFileTask();
    }

    private static void runDownloadFileTask() {
        // Start a new thread to run the download task
        IntStream.rangeClosed(1, 10)
                 .boxed()
                 .forEach(fileNumber -> {
                     Thread thread = new Thread(new DownloadFileTask(fileNumber));
                     thread.start();
                 });
    }

    private static void runInterruptFileTask() {
        Thread thread = new Thread(new InterruptFileTask(1));
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }

}
