package lambdas;

import lambdas.interfaces.Printer;

public class LambdaDemo {

    public static void main(String[] args) {

        // Anonymous Class Implementation
        greet(new Printer() {
            @Override
            public void print(String message) {
                System.out.println(message);
            }
        });

        // Lambda Expression
        greet((String message) -> System.out.println(message));

        // Lambda Expression - simplified
        greet(message -> System.out.println(message));

        // Lambda expressions stored in functional interface object
        Printer printImplementation = message -> System.out.println(message);
        greet(printImplementation);

        // Method Reference
        greet(System.out::println);

        // Method Reference stored in functional interface object
        Printer methodRefImplementation = System.out::println;
        greet(methodRefImplementation);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World");
    }
}
