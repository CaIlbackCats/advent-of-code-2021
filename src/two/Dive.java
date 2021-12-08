package two;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import utils.AdventSolver;
import utils.DailyFilePath;
import utils.FileHandler;

public class Dive extends AdventSolver<Map<String,Integer>> {

    private static final String FORWARD = "forward";
    private static final String UP = "up";
    private static final String DOWN = "down";

    private int depth;
    private int aim;
    private int horizontal;

    public Dive(){
      super(DailyFilePath.TWO);
    }
    
    @Override
    protected int findPartOneResult() {
      return this.depth*this.horizontal;
    }

    @Override
    protected int findPartTwoResult() {
      return this.aim*this.horizontal;
    }

    @Override
    protected Map<String,Integer> processInput(Path path) {
      Map<String,Integer> map = new HashMap<>();
      this.aim = FileHandler.createListFromInput(path).stream().map(line -> fillMap(line, map)).reduce(Integer::sum).orElse(-1);
      this.horizontal = map.get(FORWARD);
      this.depth = map.get(DOWN)-map.get(UP);
      return map;
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
