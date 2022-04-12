package com.cgi.ectp.calculator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;

public class Calculator {

    private static Map<String, DoubleBinaryOperator> OPERATIONS = new LinkedHashMap<>();
    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("x", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
        OPERATIONS.put("!", (a, b) -> Double.max(a, b));
    }
    private static String USAGE = OPERATIONS.keySet().stream().collect(Collectors.joining("|","Usage: A[", "]B"));

    public static void main(final String[] pArgs) {
        try {
            if (pArgs.length != 3) {
                throw new RuntimeException(USAGE);
            }

            System.out.println(Optional.ofNullable(OPERATIONS.get(pArgs[1]))
                    .orElseThrow(()-> new RuntimeException(USAGE))
                    .applyAsDouble(Double.parseDouble(pArgs[0]),Double.parseDouble(pArgs[2])));
        } catch (final NumberFormatException e) {
            System.err.println("Cannot parse " + e.getMessage());
        } catch (final RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}