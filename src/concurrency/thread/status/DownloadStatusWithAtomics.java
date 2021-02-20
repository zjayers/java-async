package concurrency.thread.status;

import java.util.concurrent.atomic.AtomicInteger;

public class DownloadStatusWithAtomics
        extends Status {

    private final AtomicInteger totalBytes = new AtomicInteger();
    private final AtomicInteger totalFiles = new AtomicInteger();

    @Override
    public void incrementTotalBytes() {
        totalBytes.getAndIncrement();
    }

    @Override
    public void incrementTotalFiles() {
        totalFiles.getAndIncrement();
    }

    @Override
    public int getTotalBytes() {
        return totalBytes.get();
    }

    @Override
    public int getTotalFiles() {
        return totalFiles.get();
    }
}
