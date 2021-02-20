package concurrency.thread.status;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatusWithLock
        extends Status {

    private final Lock lock = new ReentrantLock();

    @Override
    public void incrementTotalBytes() {
        lock.lock();

        try {
            super.incrementTotalBytes();
        } finally {
            lock.unlock();
        }

    }
}
