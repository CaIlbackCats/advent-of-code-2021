package one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import utils.FileHandler;

public class SonarSweep {

    private static String INPUT_FOLDER = "E:\\AdventOfCode\\Kozos\\advent-of-code-2021\\src\\resources\\Day 1";
    

    void solve() throws IOException{

        Map<String,List<Integer>> results = new HashMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(INPUT_FOLDER))) {
            results = paths.filter(Files::isRegularFile).map(this::process).collect(Collectors.toMap(Entry::getKey,Entry::getValue));
        }catch(Exception e){
            e.printStackTrace();
        }
   
        results.entrySet().forEach(System.out::println);
    }

    private Entry<String, List<Integer>> process(Path path){
        List<Integer> results = new ArrayList<>();

        List<Integer> inputs = FileHandler.createIntListFromInput(path);
        results.add(countIncreasedWindow(inputs,1));
        results.add(countIncreasedWindow(inputs,3));

        return Map.entry(path.getFileName().toString(),results);
    }

    private Integer countIncreasedWindow(List<Integer> inputs, Integer window){
        List<Integer> windowed = IntStream.range(window, inputs.size()+1).map(id -> inputs.subList(id-window, id).stream().reduce(0, Integer::sum)).boxed().collect(Collectors.toList());
        return IntStream.range(1, windowed.size()).map(id -> (windowed.get(id)>windowed.get(id-1))?1:0).sum();
    }
}
