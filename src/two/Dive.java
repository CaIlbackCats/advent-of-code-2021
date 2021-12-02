package two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import utils.FileHandler;

public class Dive {

    private static String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 2";
    private static final String FORWARD = "forward";
    private static final String UP = "up";
    private static final String DOWN = "down";


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
        Map<String,Integer> map = new HashMap<>();
        int depth = FileHandler.createListFromInput(path).stream().map(line -> fillMap(line, map)).reduce(Integer::sum).orElse(-1);
        int horizontal = map.get(FORWARD);
        results.add(depth*horizontal);
        return Map.entry(path.getFileName().toString(),results);
    }

    private int fillMap(String line, Map<String,Integer> map){
      //clean version
    
       String [] separated = line.split(" ");
       String key = separated[0];
       Integer value = Integer.valueOf(separated[1]);
      if(map.get(key)==null){
        map.put(key, value);
      } else{
        Integer sumValue = map.get(key)+ value;
        map.put(key, sumValue);
      }
      if(key.equals(FORWARD)){
        return ((map.get(DOWN)==null?0:map.get(DOWN))-(map.get(UP)==null?0:map.get(UP)))*value;
      }
      return 0;
      // oneline version
      //map.put(separated[0],map.get(separated[0])==null?Integer.parseInt(separated[1]):map.get(separated[0])+Integer.parseInt(separated[1]));
      
    }

    

    
}
