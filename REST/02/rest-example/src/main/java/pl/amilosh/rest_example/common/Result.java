package pl.amilosh.rest_example.common;

public class Result<T> {

    private final T result;

    private final Exception exception;

    private Result(T result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    // fabric method pattern here
    public static <R> Result<R> success(R result) {
        return new Result<>(result, null);
    }

    public static <R> Result<R> failure(Exception exception) {
        return new Result<>(null, exception);
    }

    public boolean isSuccess() {
        return this.exception == null;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public T getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }
}
