import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import utils.FileHandler;

public class SonarSweep {

    private static String INPUT_FOLDER = "/workspace/advent-of-code-2021/advent/resources/Day 1";
    

    void solve() throws IOException{

        Map<String,Integer> results = new HashMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(INPUT_FOLDER))) {
            results = paths.filter(Files::isRegularFile).map(this::process).collect(Collectors.toMap(Entry::getKey,Entry::getValue));
        }catch(Exception e){
            e.printStackTrace();
        }
   
        results.entrySet().forEach(System.out::println);
    }

    private Entry<String, Integer> process(Path path){
        List<Integer> inputs = FileHandler.createIntListFromInput(path);
        int result = IntStream.range(1, inputs.size()).map(id -> (inputs.get(id)>inputs.get(id-1))?1:0).sum();
        return Map.entry(path.getFileName().toString(),result);
    }
}
