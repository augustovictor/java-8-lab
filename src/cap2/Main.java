package cap2;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("U1", 10, false);
        User u3 = new User("U3", 20, false);
        User u2 = new User("U2", 30, false);

        List<User> users = new ArrayList<User>(Arrays.asList(u1, u3, u2));
        //
        // users.forEach(u -> System.out.println(u.getName()));
        // users.forEach(u -> System.out.println(u.isModerator()));
        // users.forEach(u -> u.setModerator(true));
        // users.forEach(u -> System.out.println(u.isModerator()));

        // Validator<String> validateZipcode = v -> v.matches("[0-9]{5}-[0-9]{3}");
        // System.out.println(validateZipcode.validate("04143-0101"));

        Consumer<User> printMessage = u -> System.out.println("Print pre message...");
        Consumer<User> printName = u -> System.out.println(u.getName());

        // users.forEach(printMessage.andThen(printName));

        users.removeIf(u -> u.getPoints() > 30);
        printList(users);

        System.out.println("Order by name");
        users.sort(Comparator.comparing(u -> u.getName()));
        printList(users);

        System.out.println("Order by points");
        // Use comparingInt to avoid autoboxing
        users.sort(Comparator.comparingInt(u -> u.getPoints()));
        printList(users);

        List<String> words = Arrays.asList("Great title", "Short title", "Almost a title");
        System.out.println(words.toString());
        words.sort(Comparator.naturalOrder());
        System.out.println(words.toString());
        words.sort(Comparator.reverseOrder());
        System.out.println(words.toString());

    }

    public static void printList(List list) {
        System.out.println("----------------");
        list.forEach(e -> System.out.println(e.toString()));
        System.out.println("----------------");
    }
}
