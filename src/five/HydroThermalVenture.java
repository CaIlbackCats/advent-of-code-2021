package five;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import utils.AdventSolver;
import utils.FileHandler;

public class HydroThermalVenture extends AdventSolver<List<String []>> {

    private static final String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 5";

    protected HydroThermalVenture() {
        super(INPUT_FOLDER);
    }

    @Override
    protected int findPartOneResult() {
        return getResultByDiagonal(false);
    }

    @Override
    protected int findPartTwoResult() {
        return getResultByDiagonal(true);
    }

    @Override
    protected List<String []> processInput(Path path) {
        return FileHandler.createListFromInput(path).stream().map(row->(row.replace(" -> ", ",")).split(",")).collect(Collectors.toList());        
    }

    private int getResultByDiagonal(boolean diagonal){
        List<Coordinate> inbetween = this.processedInput.stream().map(row -> Coordinate.getInBetweenPoints(row, diagonal)).flatMap(Collection::stream).collect(Collectors.toList());
        Map<Coordinate, Integer> resultMap = inbetween.stream().collect(Collectors.toMap((key) -> key, (value) -> 0,(existing, replacement) -> 2));
       return (int) resultMap.values().stream().filter(number -> number==2).count();
    }
    
}
