package five;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.FileHandler;
import java.util.stream.Collectors;

import utils.AdventSolver;

public class HydroThermalVenture extends AdventSolver<List<Coordinate>> {

    private static final String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 5";

    protected HydroThermalVenture() {
        super(INPUT_FOLDER);
    }

    @Override
    protected int findPartOneResult() {
        Map<Coordinate, Integer> resultMap = this.processedInput.stream().collect(Collectors.toMap(Function.identity(), 0, this::getMergeValue);
        return 0;
    }

    @Override
    protected int findPartTwoResult() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected List<Coordinate> processInput(Path path) {
        return FileHandler.createListFromInput(path).stream().map(row->(row.replace(" -> ", ",")).split()).map(Coordinate::getInBetweenPoints).collect(Collectors.toList());
    }

    private <U> Integer getMergeValue(U existing, U replacement){
        return existing+1;
    }
    
}
