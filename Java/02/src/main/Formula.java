package main;

public interface Formula {

    int multiplication(int a, int b);

    // default method
    default int sum(int a, int b) {
        return a + b;
    }
}
