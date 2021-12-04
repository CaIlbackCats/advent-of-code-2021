package one;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.AdventSolver;
import utils.FileHandler;

public class SonarSweep extends AdventSolver<List<Integer>> {

    private static String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 1";

    public SonarSweep(){
       super(INPUT_FOLDER);
    }

    @Override
    protected int findPartOneResult(){
        return countIncreasedWindow(this.processedInput, 1);
    }
    @Override
    protected int findPartTwoResult(){
        return countIncreasedWindow(this.processedInput, 3);
    }

    @Override
    protected List<Integer> processInput(Path path){
        return FileHandler.createListFromInput(path).stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    private Integer countIncreasedWindow(List<Integer> inputs, Integer window){
        List<Integer> windowed = IntStream.range(window, inputs.size()+1).map(id -> inputs.subList(id-window, id).stream().reduce(0, Integer::sum)).boxed().collect(Collectors.toList());
        return IntStream.range(1, windowed.size()).map(id -> (windowed.get(id)>windowed.get(id-1))?1:0).sum();
    }
}
