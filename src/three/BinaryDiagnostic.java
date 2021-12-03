package three;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import utils.FileHandler;

public class BinaryDiagnostic {

    private static String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 3";

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
        //TODO refactor?
        List<Integer> results = new ArrayList<>();
        List<String> rows = FileHandler.createListFromInput(path);
        int[] sums = IntStream.range(0, rows.get(0).length()).map(n->0).toArray();
        rows.forEach(row -> {
        for(int i=0; i<row.length(); i++){
          sums[i] += Integer.parseInt(row.charAt(i)+"");
        }});
        String gammaRate="";
        String epsilonRate="";
        for(int i=0;i<sums.length;i++){
          String current = (rows.size()-sums[i]<sums[i])?"1":"0";
          gammaRate += current;
          epsilonRate += (current.equals("1"))?"0":"1";
        }
        int gammaInt = Integer.valueOf(gammaRate,2);
        int epsilonInt = Integer.valueOf(epsilonRate,2);
        results.add(gammaInt*epsilonInt);
        //TODO part 2
        return Map.entry(path.getFileName().toString(),results);
    }  

    
}
