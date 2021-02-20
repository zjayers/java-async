package concurrency.thread.tasks;

public class DownloadFileTask
        extends FileTask {
    public DownloadFileTask(int fileNumber) {
        super(fileNumber, "Downloading file %d on thread: %s%n%n", "Downloaded file %d on thread: %s%n%n");
    }

    @Override
    public void run() {
        printStartMessage();
        sleep();
        printEndMessage();
    }
}
