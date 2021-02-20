package executive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        createComplexThreadPool();
    }

    private static void createSimpleThreadPool() {
        ExecutorService service = Executors.newFixedThreadPool(2);

        try {
            service.submit(() -> System.out.println(Thread.currentThread().getName()));
        } finally {
            service.shutdown();
        }

    }

    private static void createComplexThreadPool() {
        ExecutorService service = Executors.newFixedThreadPool(2);

        try {
            for (int i = 0; i < 10; i++) {
                service.submit(() -> System.out.println(Thread.currentThread().getName()));
            }
        } finally {
            service.shutdown();
        }

    }
}
