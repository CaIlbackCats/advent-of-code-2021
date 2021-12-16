package eleven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import five.Coordinate;
import utils.AdventSolver;
import utils.Constants;
import utils.DailyFilePath;
import utils.InputProcessorService;

public class DumboOctopus extends AdventSolver<Map<Coordinate,Integer>,Integer> {

    protected DumboOctopus() {
        super(DailyFilePath.ELEVEN, intputs -> InputProcessorService.covertInputToCoordinateMap(intputs));
    }

    @Override
    public Integer findPartOneResult() {
        return getNumberOfFlashes(false,copyMap());
    }

    @Override
    public Integer findPartTwoResult() {
        return getNumberOfFlashes(true,copyMap());
    }

    private int getNumberOfFlashes(boolean allFlashed, Map<Coordinate,Integer> coordMap){
        int numberOfFlashes =0;
        int step =0;
        boolean end = false;
        while ((allFlashed)?!end:step<Constants.MAX_OCTOPUS_STEP){
            List<Coordinate> flashed = new ArrayList<>();
            advanceStep(coordMap, flashed);
            if(allFlashed && flashed.size()==coordMap.size()){
                end = true;
            }else{
                numberOfFlashes+=flashed.size();
            }
            step ++;
        }
        return (allFlashed)?step:numberOfFlashes;
    }
    private void advanceStep(Map<Coordinate,Integer> coordMap, List<Coordinate> flashed){
        coordMap.entrySet().stream().map(Map.Entry::getKey)
                                    .forEach(coord -> {incrementEnergy(coord, coordMap); 
                                                       advanceStepRecursive(flashed, coord, coordMap);});
        flashed.stream().forEach(coord ->coordMap.put(coord, 0));
    }

    private void advanceStepRecursive(List<Coordinate> flashed,Coordinate current, Map<Coordinate,Integer> coordMap){
        int currentEnergy = coordMap.get(current);
        if(currentEnergy>Constants.ENERGY_LEVEL_LIMIT){
            if(!flashed.contains(current)){
                flashed.add(current);
                coordMap.put(current, 0);
            }
            List<Coordinate> neighbours = current.getExtendedNeighbours().stream()
                                                                        .filter(coord -> coordMap.get(coord)!=null)
                                                                        .collect(Collectors.toList());
            for (Coordinate neighbour : neighbours) {
                if(!flashed.contains(neighbour)){
                    incrementEnergy(neighbour,coordMap);
                    advanceStepRecursive(flashed, neighbour,coordMap);
                }
            }
        }
    }

    private void incrementEnergy(Coordinate coordinate,Map<Coordinate,Integer> coordMap){
        int newValue = coordMap.get(coordinate)+1;
        coordMap.put(coordinate, newValue);
    }

    private Map<Coordinate,Integer> copyMap(){
        return this.processedInput.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,entry -> entry.getValue()));
    }


    
}
