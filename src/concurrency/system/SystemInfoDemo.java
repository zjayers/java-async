package concurrency.system;

public class SystemInfoDemo {
    public static void main(String[] args) {
        printThreadInfo();
    }

    private static void printThreadInfo() {
        // Print the active threads used by the process
        // This should show 2 threads, 1 thread for running code, 1 thread for the Garbage Collector
        System.out.println(Thread.activeCount());

        // Print the total number of available processors for the system
        System.out.println(Runtime.getRuntime()
                                  .availableProcessors());
    }
}
