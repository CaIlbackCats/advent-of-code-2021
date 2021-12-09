package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AdventSolver<T,U> implements SolutionSolver<U>{

    protected T processedInput;

    protected Stream<Path> paths;

    protected InputProcessor<T> inputProcessor;

    protected AdventSolver(DailyFilePath path,InputProcessor<T> inputProcessor){
        try {
            this.paths = Files.walk(path.getPath());
            this.inputProcessor = inputProcessor;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solve(){        
        Map<String,List<U>> results = new HashMap<>();
        results = this.paths.filter(Files::isRegularFile).map(this::process).collect(Collectors.toMap(Entry::getKey,Entry::getValue));   
        results.entrySet().forEach(System.out::println);
    }

    private Entry<String, List<U>> process(Path path){
        List<U> results = new ArrayList<>();
        this.processedInput = processInput(path);
        results.add(findPartOneResult());
        results.add(findPartTwoResult());

        return Map.entry(path.getFileName().toString(),results);
    };

    private T processInput(Path path){
       return this.inputProcessor.process(FileHandler.createListFromInput(path));
    };
    
}
