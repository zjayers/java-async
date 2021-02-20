package concurrency.thread.status;

public class DownloadStatusWithSynchronized
        extends Status {

//    @Override
//    public synchronized void incrementTotalBytes() {
//        super.incrementTotalBytes();
//    }

    // This is equivalent to

//    @Override
//    public void incrementTotalBytes() {
//
//        // Set up synchronized block with a monitor object
//        synchronized (this) {
//            super.incrementTotalBytes();
//        }
//    }

    private final Object totalBytesLock = new Object();
    private final Object totalFilesLock = new Object();

    @Override
    public void incrementTotalBytes() {
        // Set up synchronized block with a monitor object
        synchronized (totalBytesLock) {
            super.incrementTotalBytes();
        }
    }

    @Override
    public void incrementTotalFiles() {
        // Set up synchronized block with a monitor object
        synchronized (totalFilesLock) {
            super.incrementTotalFiles();
        }
    }
}
