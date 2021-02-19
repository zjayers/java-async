package lambdas.interfaces;

/**
 * Example of a functional interface
 * Functional interfaces contain only ONE abstract method
 * Functional interfaces may also have a {@code default} method
 */
public interface Printer {
    void print(String message);

    default void printToConsole(String message) {
        System.out.println(message);
    }
}
