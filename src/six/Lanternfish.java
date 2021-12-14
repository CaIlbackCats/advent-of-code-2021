package six;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import utils.AdventSolver;
import utils.DailyFilePath;
import utils.InputProcessorService;

public class Lanternfish extends AdventSolver<List<Integer>,Long> {

    private static final int PART_ONE_DAY_COUNTER = 80;
    private static final int PART_TWO_DAY_COUNTER = 256;
    

    protected Lanternfish() {
        super(DailyFilePath.SIX,inputs -> InputProcessorService.convertInputSplitToIntList(inputs));
    }

    @Override
    public Long findPartOneResult() {
        Map<Integer,Long> lanternfishMap = initMap(this.processedInput);
        cycleMap(lanternfishMap, PART_ONE_DAY_COUNTER);
       return lanternfishMap.values().stream().reduce(Long::sum).orElse(-1l);
    }

    @Override
    public Long findPartTwoResult() {
        Map<Integer,Long> lanternfishMap = initMap(this.processedInput);
        cycleMap(lanternfishMap, PART_TWO_DAY_COUNTER);
       return lanternfishMap.values().stream().reduce(Long::sum).orElse(-1l);
    }

    private Map<Integer,Long> initMap(List<Integer> lanternfishes){
        Map<Integer,Long> lanternfishMap = new HashMap<>();
        IntStream.range(0, 9).forEach(counter -> lanternfishMap.put(counter, 0l));
        for (Integer current : lanternfishes) {
           long newValue = lanternfishMap.get(current)+1;
           lanternfishMap.put(current, newValue);
        }
        return lanternfishMap;
    }
    private void cycleMap(Map<Integer,Long> lanternfishMap, int counter){
        for (int i = 0; i < counter; i++) {
            long temp = lanternfishMap.get(0);
            lanternfishMap.put(0, lanternfishMap.get(1));
            lanternfishMap.put(1, lanternfishMap.get(2));
            lanternfishMap.put(2, lanternfishMap.get(3));
            lanternfishMap.put(3, lanternfishMap.get(4));
            lanternfishMap.put(4, lanternfishMap.get(5));
            lanternfishMap.put(5, lanternfishMap.get(6));
            lanternfishMap.put(6, temp+lanternfishMap.get(7));
            lanternfishMap.put(7, lanternfishMap.get(8));
            lanternfishMap.put(8, temp);
        }
    }    
}
