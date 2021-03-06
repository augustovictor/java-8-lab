package cap2;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        User u4 = new User("U4", 10, false);
        User u1 = new User("U1", 10, false);
        User u3 = new User("U3", 20, false);
        User u2 = new User("U2", 30, false);

        List<User> users = new ArrayList<User>(Arrays.asList(u1, u4, u3, u2));
        //
        // users.forEach(u -> System.out.println(u.getName()));
        // users.forEach(u -> System.out.println(u.isModerator()));
        // users.forEach(u -> u.setModerator(true));
        // users.forEach(u -> System.out.println(u.isModerator()));

        // Validator<String> validateZipcode = v -> v.matches("[0-9]{5}-[0-9]{3}");
        // System.out.println(validateZipcode.validate("04143-0101"));

        Consumer<User> printMessage = u -> System.out.println("Print pre message...");
        Consumer<User> printName = u -> System.out.println(u.getName());

        users.forEach(printMessage.andThen(printName));

        users.removeIf(u -> u.getPoints() > 30);
        printList(users);

        System.out.println("Order by name");
        // users.sort(Comparator.comparing(u -> u.getName())); // Alternative 1
        users.sort(Comparator.nullsLast(Comparator.comparing(User::getName))); // Alternative 2
        printList(users);

        System.out.println("Order by points");
        // Use comparingInt to avoid autoboxing
        // users.sort(Comparator.comparingInt(u -> u.getPoints())); // Alternative 1
        users.sort(Comparator.comparingInt(User::getPoints).thenComparing(User::getName));
        printList(users);

        System.out.println("Order by points DESC");
        users.sort(Comparator.comparingInt(User::getPoints).thenComparing(User::getName).reversed());
        printList(users);

        List<String> words = Arrays.asList("Great title", "Short title", "Almost a title");
        System.out.println(words.toString());
        words.sort(Comparator.naturalOrder());
        System.out.println(words.toString());
        words.sort(Comparator.reverseOrder());
        System.out.println(words.toString());

        System.out.println("Make all users moderators");
        // users.forEach(u -> u.setModerator(true)); // Alternative 1
        users.forEach(User::makeModerator); // Alternative 2 (preferred)
        // Consumer<User> makeModerator = User::makeModerator; // Alternative 3
        // users.forEach(makeModerator);
        printList(users);


        System.out.println("Increase u2 points by 1");
        Runnable increaseU2PointsByOne = u2::increasePoints; // Invoking by method reference
        increaseU2PointsByOne.run();
        printList(users);

        System.out.println("Increase u3 points by 5");
        Runnable increaseU3Points = () -> u3.increasePoints(5); // Invoking by lambda
        increaseU3Points.run();
        printList(users);


        // Constructor reference
        System.out.println("Constructor reference");
        Supplier<User> userSupplier = User::new; // There is also use a constructor reference with an array if needed. E.g., int[]::new
        System.out.println(userSupplier.get());

        Function<String, User> userCreator = User::new; // Java also provides an interface to instantiate by 2 args. But we can extend it and make it as many as we want;
        User u5 = userCreator.apply("Victor");
        System.out.println(u5);

        System.out.println("Infinite streams");
        // This is a lazy generation. That means we only generate values when requested
        Random rand = new Random(0);
        // boxed returns us a Stream<Integer> so we can invoke collect on it. Otherwise we'd have to call .toArray
        List<Integer> integerList = IntStream
                .generate(() -> rand.nextInt())
                .limit(4)
                .boxed()
                .collect(toList());

        printList(integerList);

        System.out.println("Flat map");
        List<List> indexes = new ArrayList<>();
        indexes.add(Arrays.asList(1,2,3)); // [1,2,3]
        indexes.add(Arrays.asList(Arrays.asList(2,2,2), Arrays.asList(3,3,3))); // [[2,2,2], [3,3,3]]

        indexes.stream().flatMap(a -> a.stream()).forEach(System.out::println);

    }

    public static void printList(List list) {
        System.out.println("----------------");
        list.forEach(System.out::println);
        System.out.println("----------------");
    }
}
