package seven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.AdventSolver;
import utils.DailyFilePath;
import utils.InputProcessorService;

public class TreacheryOFWhales extends AdventSolver<List<Integer>,Integer> {

    private Map<Integer,Integer> crabPositionMap = new HashMap<>();

    protected TreacheryOFWhales() {
        super(DailyFilePath.SEVEN, inputs -> InputProcessorService.convertInputSplitToIntList(inputs));
    }

    @Override
    public Integer findPartOneResult() {
        this.crabPositionMap = initMap(this.processedInput);
        return calculateFuelEfficient(false);
    }

    @Override
    public Integer findPartTwoResult() {
        return calculateFuelEfficient(true);
    }

    private Map<Integer,Integer> initMap(List<Integer> inputs){
       int min = inputs.stream().reduce(Integer::min).orElse(-1);
       int max = inputs.stream().reduce(Integer::max).orElse(-1);
       Map<Integer,Integer> crabMap = inputs.stream().collect(Collectors.toMap((input) -> input, (value) -> 1,(existing,replacement)-> existing+1));
        for (int i = min; i < max; i++) {
            if(crabMap.get(i)==null){
                crabMap.put(i, 0);
            }
        }
        return crabMap;
    }

    private int calculateFuelEfficient(boolean incrementFuel){
       return crabPositionMap.entrySet().stream()
                                        .map(entry -> crabPositionMap.entrySet().stream()
                                                                    .map(entry2 -> calculateFuelCost(incrementFuel, entry, entry2)*entry2.getValue())
                                                                    .reduce(Integer::sum)
                                                                    .orElse(-1))
                                        .reduce(Integer::min)
                                        .orElse(-1); 
     /*   int minFuel=-1;
        for (Map.Entry<Integer,Integer> entry : this.crabPositionMap.entrySet()) {
            int currentLevel = entry.getKey();
            int sum =0;
            for (Map.Entry<Integer,Integer> entry2 : this.crabPositionMap.entrySet()) {
                int difference = Math.abs(currentLevel - entry2.getKey());
                int stepCost =(incrementFuel)?((difference==0)?0: IntStream.range(1, difference+1).sum()):difference;
               sum += stepCost *entry2.getValue();
            }
            if(minFuel==-1 || sum<minFuel){
                minFuel=sum;
            }
        }
        return minFuel; */
    }

    private int calculateFuelCost(boolean incrementFuel, Map.Entry<Integer,Integer> entry, Map.Entry<Integer,Integer> entry2){
        int difference = Math.abs(entry.getKey() - entry2.getKey());
        return (incrementFuel)?((difference==0)?0: IntStream.range(1, difference+1).sum()):difference;
    }
    
}
