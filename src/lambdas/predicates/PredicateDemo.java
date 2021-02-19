package lambdas.predicates;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateDemo {

    private static final Predicate<String> isLengthGreaterThan5 = str -> str.length() > 5;
    private static final Predicate<String> startsWithLeftBrace = str -> str.startsWith("{");
    private static final Predicate<String> endsWithRightBrace = str -> str.endsWith("}");
    private static final Predicate<String> hasLeftAndRightBrace = startsWithLeftBrace.and(endsWithRightBrace);
    private static final Consumer<String> testInputLength = input -> System.out.printf(
            "String '%s' length greater than 5? %b%n",
            input,
            isLengthGreaterThan5.test(input));

    private static final Consumer<String> testForBraces = input -> System.out.printf(
            "String '%s' starts with '{' and ends with '}'? %b%n%n",
            input,
            hasLeftAndRightBrace.test(input));

    public static void main(String[] args) {
        List.of("Hello}", "{Hello World", "{Hi There}")
            .forEach(testInputLength.andThen(testForBraces));
    }


}
