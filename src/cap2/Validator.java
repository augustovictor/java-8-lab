package cap2;

@FunctionalInterface
public interface Validator<T> {
    boolean validate(T t);
    // boolean m2(T t); // Causes an error since functional interfaces must have one abstract method only
}
