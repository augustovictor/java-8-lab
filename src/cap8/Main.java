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

    }
}
