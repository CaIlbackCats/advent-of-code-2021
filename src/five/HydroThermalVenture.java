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

public class HydroThermalVenture extends AdventSolver<List<Coordinate>> {

    private static final String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/sample";

    protected HydroThermalVenture() {
        super(INPUT_FOLDER);
    }

    @Override
    protected int findPartOneResult() {
        Map<Coordinate, Integer> resultMap = this.processedInput.stream().collect(Collectors.toMap((key) -> key, (value) -> 0,(existing, replacement) -> 2));
       return (int) resultMap.values().stream().filter(number -> number==2).count();
      //  return (int) resultMap.values().stream().filter(v -> 0<v).count();
    }

    @Override
    protected int findPartTwoResult() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected List<Coordinate> processInput(Path path) {
        return FileHandler.createListFromInput(path).stream().map(row->(row.replace(" -> ", ",")).split(",")).map(Coordinate::getInBetweenPoints).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private <U,R> R getMergeValue(U existing, U replacement){
        Integer number = (int) existing +1;
        return (R) number;
    }
    
}
