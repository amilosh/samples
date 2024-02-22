package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) {
        String[] array1 = {"мама", "мыла", "раму"};
        String[] array2 = {"я", "очень", "люблю", "java"};
        String[] array3 = {"мир", "труд", "май"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        Comparator<String[]> sortByLength = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        };

        Comparator<String[]> sortByLength2 = (o1, o2) -> o1.length - o2.length;

        arrays.sort(sortByLength);
        arrays.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        });
        arrays.sort((o1, o2) -> o1.length - o2.length);

        new Thread(() -> System.out.println("run")).start();

        Callable<Integer> c = () -> 42;
    }
}
