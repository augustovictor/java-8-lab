# java-8-lab

- Consumer: Functional interface that can be used as the assignment for a lambda expression or method reference;
    - `T` is the type of the input to the operation;
    - Has two methods:
        - `void accept(T t)`:  Abstract method that performs this operation on the given argument;
        - `default Consumer<T> andThen(Consumer <? super T> after)`: Default method that returns a composed Consumer that performs, in sequence, this operation followed by `after` operation;
- Predicate: Functional interface that allows us to test objects of a given type;
- Default method: Interface methods that receive code implementation in its params;
- `@FunctionalInterface`: Interface that has **one** `abstract` method only and this can be used through lambda;
    - It can have other methods but they should be `default`;# java-8-lab
- Terminal operations: findAny, collect, forEach;
- Stateful operation: Need to process the whole stream even though its terminal operation does not demand it;