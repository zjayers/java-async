package concurrency.thread.status;

public abstract class Status {
    private int totalBytes;
    private int totalFiles;
    private boolean isDone;

    public void incrementTotalBytes() {
        totalBytes++;
    }

    public void incrementTotalFiles() {
        totalFiles++;
    }

    public int getTotalBytes() {
        return totalBytes;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }
}
