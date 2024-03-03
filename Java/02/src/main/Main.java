package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {

    private static int outerStaticNum;
    private int outerNum;

    public static void main(String[] args) {
        // Functional interfaces, lambda expression
        lambda();

        // Optional
        optional();

        // Stream
        stream();

        // Map
        map();
    }

    // lambda
    public static void lambda() {
        // we don't need to override the default method
        Formula formula = (a, b) -> a * b;
        int result1 = formula.multiplication(2, 3);
        int result2 = formula.sum(1, 4);

        // lambda expression
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (a, b) -> a.compareTo(b));

        // functional interface
        Converter<String, Integer> converterStringToInteger = (s) -> Integer.valueOf(s);
        Integer result3 = converterStringToInteger.convert("123");

        // method reference
        Converter<String, Integer> converterStringToInteger1 = Integer::valueOf;
        Integer result4 = converterStringToInteger1.convert("123");
        StringUtil stringUtil = new StringUtil();
        Converter<String, String> converterStringToS = stringUtil::startsWith;
        String result5 = converterStringToS.convert("String");
        Converter<String, Integer> converterStringToLength = StringUtil::length;
        Integer result6 = converterStringToLength.convert("String");
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("John", "Doe");

        // reference to outer variable
        final int num = 1; // variable should be final
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        stringConverter.convert(2);
        // num = 3; // compilation error

        // reference to class variables
        // testScopes();

        /* functional interface examples:
        java.lang
          Runnable
        java.util
          Comparator
        java.util.function
          Predicate
          Function
          Supplier
          Consumer
         */
    }

    // reference to class variables
    public void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

    // Optional
    public static void optional() {
        Optional<String> optional = Optional.of("String");
        var result7 = optional.isPresent();
        var result8 = optional.isEmpty();
        optional.orElse("orElse");
        optional.ifPresent(
            (s) -> System.out.println(s.charAt(0))
        );
    }

    // Stream
    public static void stream() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        // filter
        stringCollection
            .stream()
            .filter((s) -> s.startsWith("a"))
            .forEach(System.out::println);

        // sorting
        stringCollection
            .stream()
            .sorted()
            .filter((s) -> s.startsWith("a"))
            .forEach(System.out::println);

        // map
        stringCollection
            .stream()
            .map(String::toUpperCase)
            .sorted((a, b) -> b.compareTo(a))
            .forEach(System.out::println);

        // match
        boolean anyStartsWithA =
            stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true
        boolean allStartsWithA =
            stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);      // false
        boolean noneStartsWithZ =
            stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);      // true

        // count
        long startsWithB =
            stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();
        System.out.println(startsWithB);    // 3

        // reduce
        Optional<String> reduced =
            stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2); // ?
        reduced.ifPresent(System.out::println); // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

        // parallel stream - ?
    }

    // map
    public static void map() {
        Map<Integer, String> map = new HashMap<>();

        // put if absent
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        // for each
        map.forEach((id, val) -> System.out.println(val));

        // computeIfPresent
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        // computeIfAbsent
        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true
        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33

        // remove
        map.remove(3, "val3");
        map.get(3);             // val33
        map.remove(3, "val33");
        map.get(3);             // null

        // getOrDefault
        map.getOrDefault(42, "not found");  // not found

        // merge
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat
    }
}
