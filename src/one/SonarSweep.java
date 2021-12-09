package one;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.AdventSolver;
import utils.DailyFilePath;
import utils.InputProcessor;

public class SonarSweep extends AdventSolver<List<Integer>,Integer> {

    public <T> SonarSweep(InputProcessor<List<Integer>> inputProcessor){
       super(DailyFilePath.ONE,inputProcessor);
    }

    @Override
    public Integer findPartOneResult(){
        return countIncreasedWindow(this.processedInput, 1);
    }
    @Override
    public Integer findPartTwoResult(){
        return countIncreasedWindow(this.processedInput, 3);
    }

    private Integer countIncreasedWindow(List<Integer> inputs, Integer window){
        List<Integer> windowed = IntStream.range(window, inputs.size()+1).map(id -> inputs.subList(id-window, id).stream().reduce(0, Integer::sum)).boxed().collect(Collectors.toList());
        return IntStream.range(1, windowed.size()).map(id -> (windowed.get(id)>windowed.get(id-1))?1:0).sum();
    }
}
