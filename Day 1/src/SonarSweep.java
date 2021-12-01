import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SonarSweep {

    private static String INPUT_FOLDER = "/workspace/advent-of-code-2021/resources/Day 1";
    

    void solve() throws IOException{

        Map<String,Integer> results = new HashMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(INPUT_FOLDER))) {

            results = paths.filter(Files::isRegularFile).map(this::process).collect(Collectors.toMap(Entry::getKey,Entry::getValue));

        }
   
        results.entrySet().forEach(System.out::println);
    }

    private Entry<String, Integer> process(Path path){
        return 0;
    }
}
