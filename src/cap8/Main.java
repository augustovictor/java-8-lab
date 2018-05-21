package cap8;

import cap2.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cap7.Main.loadUsers;

public class Main {
    public static void main(String[] args) {
        List<User> users = loadUsers(10);
        // System.out.println("Name of the two users with points above 70");
        // List<String> usersWithMostPoints = users.stream()
        //         .filter(u -> u.getPoints() > 70)
        //         .sorted(Comparator.comparingInt(User::getPoints))
        //         .map(User::getName)
        //         .collect(Collectors.toList())
        //         .subList(0, 2); // May cause nullPointer

        // usersWithMostPoints.forEach(System.out::println);

        users.stream()
                .filter(u -> u.getPoints() > 90)
                .peek(System.out::println)
                .findAny();

        users.stream()
                .sorted(Comparator.comparing(User::getName))
                .peek(System.out::println)
                .findAny();

        System.out.println("Reduction operations");
        double averagePontuation = users.stream()
                .mapToInt(User::getPoints)
                .average().orElse(0.0);
        System.out.println("Average points: " + averagePontuation);

        int pointsSum = users.stream().mapToInt(User::getPoints).sum();
        System.out.println("Points sum: " + pointsSum);

        int pointsProduct = users.stream().mapToInt(User::getPoints).reduce(1, (a, b) -> a * b);
        System.out.println("Points product: " + pointsProduct);

        System.out.println("Iterators in streams");
        // We cannot loop a stream twice with a terminal operation since it is marked as already being used.
        // We may want to use an Iterator to change stream objects.
        // We should not change object states if we're working them in parallel
        users.stream().iterator().forEachRemaining(System.out::println);
        users.get(0).setModerator(users.get(0).getPoints() > 70);
        System.out.println("Is there a moderator user? " + users.stream().anyMatch(User::isModerator));
        System.out.println("All users are moderators. " + users.stream().allMatch(User::isModerator));
        System.out.println("There is no user that is a moderator. " + users.stream().noneMatch(User::isModerator));

        System.out.println("Count elements in stream: " + users.stream().count());
        System.out.println("Do not consider the first three elements. " + users.stream().skip(3).count());
        System.out.println("Consider up to 5 elements. " + users.stream().limit(5).count());
        System.out.println("Skip the first 2 elements and consider the next 5. " + users.stream().skip(2).limit(5).count());

        Stream<String> stringStream = Stream.of("p1", "p2", "p3", "p4");
        stringStream.forEach(System.out::println);


    }
}
