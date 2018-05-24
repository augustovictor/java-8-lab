package cap9;

import cap2.User;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cap7.Main.loadUsers;
import static java.nio.file.Files.lines;

public class Main {
    public static void main(String[] args) {
        List<User> users = loadUsers(10);
        Map<String, User> userAndName = users.stream()
                .collect(Collectors.toMap(
                        User::getName,
                        Function.identity()
                ));

        System.out.println(userAndName);
    }
}
