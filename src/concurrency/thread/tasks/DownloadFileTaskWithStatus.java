package concurrency.thread.tasks;

import concurrency.thread.status.Status;

public class DownloadFileTaskWithStatus
        extends FileTask {
    private final Status status;
    private final long downloadTime;

    public DownloadFileTaskWithStatus(int fileNumber, Status status, int downloadTime) {
        super(fileNumber);
        this.status = status;
        this.downloadTime = downloadTime;
    }

    @Override
    public void run() {
        for (int i = 0; i < downloadTime; i++) {
            status.incrementTotalBytes();

            if (i % 1000 == 0) {
                status.incrementTotalFiles();
            }
        }

        status.done();

        synchronized (status) {
            status.notify();
        }
    }

    public Status getStatus() {
        return status;
    }

}
