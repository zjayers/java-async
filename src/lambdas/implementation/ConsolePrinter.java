package lambdas.implementation;

import lambdas.interfaces.Printer;

public class ConsolePrinter
        implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
