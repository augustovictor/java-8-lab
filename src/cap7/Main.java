package cap7;

import cap2.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static cap2.Main.printList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<User> users = loadUsers(10);
        printList(users);

        System.out.println("Making the 2 users with most points moderators");
        users.sort(Comparator.comparingInt(User::getPoints).reversed());
        users.subList(0, 2).forEach(User::makeModerator);
        printList(users);

        // We want to filter the users with more than 50 points.
        // To achieve that we have  to make conditional statements when filtering
        // However there is not filter method in Iterable objects. Neither Collection nor List.
        // Then we have to use streams
        System.out.println("Users with more than 50 points");
        users.stream().
                filter(u -> u.getPoints() > 50)
                .forEach(System.out::println);

        System.out.println("Users with more than 60 points");
        // List<User> sixtyPointsPlus = new ArrayList<>();
        // users.stream()
        //         .filter(u -> u.getPoints() > 60)
        //         .forEach(sixtyPointsPlus::add);
        // printList(sixtyPointsPlus);

        List<User> sixtyPointsPlus = users.stream()
                .filter(u -> u.getPoints() > 60)
                // We can determine the type of implementation by using Collectors.toCollection(HashSet::new) for example
                // .collect(toCollection(HashSet::new));
                .collect(toList());

        printList(sixtyPointsPlus);

        System.out.println("Listing names only");
        List<String> names = users.stream().map(User::getName).collect(toList());
        System.out.println(names.toString());

        // Avoiding autoboxing using streams
        System.out.println("Points only");
        IntStream points = users.stream().mapToInt(User::getPoints);
        System.out.println(points);

        System.out.println("Average: " + points.average().getAsDouble());




    }

    public static List<User> loadUsers(int amountOfUsers) {
        List<User> users = new ArrayList<>();
        Supplier<User> userCreator = User::new;

        for(int i = 0; i < amountOfUsers; i++) {
            User u = userCreator.get();
            u.setName("User " + i);
            users.add(u);
        }

        return users;
    }
}
