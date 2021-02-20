package concurrency.thread.status;

import java.util.concurrent.atomic.LongAdder;

public class DownloadStatusWithAdders
        extends Status {

    private final LongAdder totalBytes = new LongAdder();
    private final LongAdder totalFiles = new LongAdder();

    @Override
    public void incrementTotalBytes() {
        totalBytes.increment();
    }

    @Override
    public void incrementTotalFiles() {
        totalFiles.increment();
    }

    @Override
    public int getTotalBytes() {
        return totalBytes.intValue();
    }

    @Override
    public int getTotalFiles() {
        return totalFiles.intValue();
    }
}
