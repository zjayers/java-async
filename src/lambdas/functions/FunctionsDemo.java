package lambdas.functions;

import java.util.function.Function;

public class FunctionsDemo {

    private static final Function<String, Integer> getStringLength = String::length;
    private static final Function<String, String> replaceColon = str -> str.replace(":", "=");
    private static final Function<String, String> addBracesAndColons = str -> String.format("{:%s:}", str);

    public static void main(String[] args) {

        String input = "Hello:World";

        Integer stringLength = getStringLength.apply(input);
        System.out.printf("Length of String: %d%n", stringLength);

        pipe(input);
        compose(input);
    }

    /**
     * @param input String to be piped through functions
     */
    private static void pipe(String input) {

        // Go through functions in left to right order
        String result = replaceColon
                .andThen(addBracesAndColons)
                .apply(input);

        System.out.printf("Piped Input: %s%n", result);
    }

    /**
     * @param input String to be composed through functions
     */
    private static void compose(String input) {
        // Go through functions in right to left order
        String result = replaceColon
                .compose(addBracesAndColons)
                .apply(input);

        System.out.printf("Composed Input: %s%n", result);
    }
}
