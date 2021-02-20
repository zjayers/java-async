package concurrency.thread.tasks;

public abstract class FileTask
        implements Runnable {
    private final int fileNumber;
    private final String currentThreadName;
    private String messageOnStart;
    private String messageOnComplete;

    protected FileTask(int fileNumber) {
        this.fileNumber = fileNumber;
        currentThreadName = Thread.currentThread().getName();
    }

    protected FileTask(int fileNumber, String messageOnStart, String messageOnComplete) {
        this.fileNumber = fileNumber;
        this.messageOnStart = messageOnStart;
        this.messageOnComplete = messageOnComplete;
        currentThreadName = Thread.currentThread().getName();
    }

    public void printStartMessage() {
        print(messageOnStart);
    }

    public void printEndMessage() {
        print(messageOnComplete);
    }

    private void print(String message) {
        System.out.printf(message, fileNumber, currentThreadName);
    }

    public void sleep() {
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
