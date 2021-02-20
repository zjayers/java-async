package concurrency.thread.status;

public class DownloadStatusWithVolatiles
        extends Status {

    private volatile boolean isDoneVolatile;

    @Override
    public boolean isDone() {
        return isDoneVolatile;
    }

    @Override
    public void done() {
        isDoneVolatile = true;
    }
}
