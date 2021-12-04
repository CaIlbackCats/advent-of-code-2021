package utils;

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
import java.util.stream.Stream;

public abstract class AdventSolver<T> {

    protected T processedInput;

    protected Stream<Path> paths;

    protected AdventSolver(String inputFolder){
        try {
            this.paths = Files.walk(Paths.get(inputFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solve(){        
        Map<String,List<Integer>> results = new HashMap<>();
        results = this.paths.filter(Files::isRegularFile).map(this::process).collect(Collectors.toMap(Entry::getKey,Entry::getValue));   
        results.entrySet().forEach(System.out::println);
    }

    private Entry<String, List<Integer>> process(Path path){
        List<Integer> results = new ArrayList<>();
        
        this.processedInput = processInput(path);
        results.add(findPartOneResult());
        results.add(findPartTwoResult());

        return Map.entry(path.getFileName().toString(),results);
    };

    protected abstract int findPartOneResult();

    protected abstract int findPartTwoResult();

    protected abstract T processInput(Path path);
    
}
