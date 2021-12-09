package five;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utils.AdventSolver;
import utils.DailyFilePath;
import utils.InputProcessor;

public class HydroThermalVenture extends AdventSolver<List<String []>,Integer> {

    protected HydroThermalVenture(InputProcessor<List<String[]>> inputProcessor) {
        super(DailyFilePath.FIVE,inputProcessor);
    }

    @Override
    public Integer findPartOneResult() {
        return getResultByDiagonal(false);
    }

    @Override
    public Integer findPartTwoResult() {
        return getResultByDiagonal(true);
    }

    private int getResultByDiagonal(boolean diagonal){
        List<Coordinate> inbetween = this.processedInput.stream().map(row -> Coordinate.getInBetweenPoints(row, diagonal)).flatMap(Collection::stream).collect(Collectors.toList());
        Map<Coordinate, Integer> resultMap = inbetween.stream().collect(Collectors.toMap((key) -> key, (value) -> 0,(existing, replacement) -> 2));
       return (int) resultMap.values().stream().filter(number -> number==2).count();
    }
    
}
