package cap9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Path, Long> linesPerFile = new HashMap<>();

        Stream<String> strings = Files.list(Paths.get("./"))
                .filter(p -> p.toString().endsWith(".txt"))
                .collect(Collectors.toMap(
                        p -> p,
                        p -> lines(p).count()
                ));
    }
}
