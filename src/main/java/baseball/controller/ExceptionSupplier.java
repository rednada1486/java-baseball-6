package baseball.controller;

@FunctionalInterface
public interface ExceptionSupplier<T> {
    T get() throws IllegalArgumentException;
}