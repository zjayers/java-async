package executive.task;

import java.util.Random;

public class LongRunningTask {
    public static void simulate() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void simulateRandom() {
        try {
            Thread.sleep(new Random().nextInt(4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
