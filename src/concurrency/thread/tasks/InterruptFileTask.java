package concurrency.thread.tasks;

public class InterruptFileTask
        extends FileTask {

    public InterruptFileTask(int fileNumber) {
        super(fileNumber, "Downloading file %d on thread: %s%n%n", "Interrupted file %d on thread: %s%n%n");
    }

    @Override
    public void run() {
        printStartMessage();

        int maxVal = Integer.MAX_VALUE;

        for (var i = 0; i < maxVal; i++) {

            if (Thread.currentThread().isInterrupted()) break;

            System.out.printf("Downloading Byte: %d of %d%n", i, maxVal);
        }

        printEndMessage();
    }
}
