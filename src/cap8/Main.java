package cap8;

import cap2.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cap7.Main.loadUsers;

public class Main {
    public static void main(String[] args) {
        List<User> users = loadUsers(20);
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
    }
}
