package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

        int[] count = {0};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                count[0] = count[0] + 1;
                System.out.println("inside:" + count[0]);
            }
        };
        new Thread(runnable).start();
        System.out.println("outside" + count[0]);
        runnable.run();

        Consumer<String[]> cons = new Consumer<String[]>() {
            @Override
            public void accept(String[] strings) {
                strings.toString();
            }
        };

        arrays.forEach(cons);

        var user = new User();
        var addressOpt = user.getAddress();
        if (addressOpt == null) {
            var newAddress = new Address();
            user.setAddress(newAddress);
        }

        var address = user.getAddress();
        address.setCity("New York");
        address.setStreet("Wall street");
        address.setHouseNumber(12);

        user.address(a -> {
            a.setCity("New York");
            a.setStreet("Wall street");
            a.setHouseNumber(12);
        });
    }
}
