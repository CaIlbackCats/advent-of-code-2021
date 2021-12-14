package nine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import five.Coordinate;
import utils.AdventSolver;
import utils.DailyFilePath;
import utils.InputProcessorService;

public class SmokeBasin extends AdventSolver<Map<Coordinate,Integer>,Integer>{

    protected SmokeBasin() {
        super(DailyFilePath.NINE,inputs -> InputProcessorService.covertInputToCoordinateMap(inputs));
    }

    @Override
    public Integer findPartOneResult() {
        return getLowestSum();
    }

    @Override
    public Integer findPartTwoResult() {
        return getBasinSize();
    }

    private int getLowestSum(){
        List<Integer> lowestPoints = new ArrayList<>();
        for (Map.Entry<Coordinate,Integer> entry : this.processedInput.entrySet()) {
            Coordinate currentCoordinate = entry.getKey();
            List<Coordinate> neighbours = currentCoordinate.getNeighbours();
           int lowestValue = neighbours.stream()
                                                .filter(coord -> this.processedInput.get(coord)!=null)
                                                .map(coord -> this.processedInput.get(coord))
                                                .min(Integer::compare)
                                                .orElse(-1);
            if(entry.getValue()< lowestValue){
                lowestPoints.add(entry.getValue());
            }
        }
        return lowestPoints.stream().reduce(Integer::sum).orElse(-1)+lowestPoints.size();
    }

    private int getBasinSize(){
        List<Coordinate> checkedCoords = new ArrayList<>();
       List<Integer> results = this.processedInput.entrySet().stream().map(entry -> calculateBasinRecursively(checkedCoords, entry.getKey())).collect(Collectors.toList());
       Collections.sort(results);
       int listSize = results.size();
        return results.get(listSize-1)*results.get(listSize-2)*results.get(listSize-3);
    }
    private int calculateBasinRecursively(List<Coordinate> coords, Coordinate current){
        if(this.processedInput.get(current)==null || this.processedInput.get(current)==9 || coords.contains(current)){
            return 0;
        }
        int size =0;
        int currentValue = this.processedInput.get(current);
        List<Coordinate> neighbours = current.getNeighbours();
        if(this.processedInput.get(current)!=null && currentValue != 9 && !coords.contains(current)){
            coords.add(current);
            size++;
        }
        size += neighbours.stream().filter(neighbour -> this.processedInput.get(neighbour)!=null).map(neighbour -> calculateBasinRecursively(coords,neighbour)).reduce(Integer::sum).orElse(-1);
        return size;
    }    
}
