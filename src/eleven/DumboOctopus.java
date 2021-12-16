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
        return getNumberOfFlashes();
    }

    @Override
    public Integer findPartTwoResult() {
        return null;
    }

    private int getNumberOfFlashes(){
        int numberOfFlashes =0;
        for (int i = 0; i < Constants.MAX_OCTOPUS_STEP; i++) {
            this.processedInput.entrySet().stream().map(Map.Entry::getKey).forEach(this::incrementEnergy);
            for (Map.Entry<Coordinate,Integer> entry : this.processedInput.entrySet()) {
                List<Coordinate> flashed = new ArrayList<>();
                advanceStepRecursive(flashed, entry.getKey());
                numberOfFlashes+=flashed.size();
            }
            System.out.println(i+"----------------------");
            printMap();
        }
        return numberOfFlashes;
    }

    private void advanceStepRecursive(List<Coordinate> flashed,Coordinate current){
        int currentEnergy = this.processedInput.get(current);
        if(currentEnergy>Constants.ENERGY_LEVEL_LIMIT){
            flashed.add(current);
            List<Coordinate> neighbours = current.getExtendedNeighbours();
            for (Coordinate neighbour : neighbours) {
                if(this.processedInput.get(neighbour)!=null && !flashed.contains(neighbour)){
                    incrementEnergy(neighbour);
                    advanceStepRecursive(flashed, neighbour);
                }
            }
            this.processedInput.put(current, 0);
        }
    }

    private void incrementEnergy(Coordinate coordinate){
        int newValue = this.processedInput.get(coordinate)+1;
            this.processedInput.put(coordinate, newValue);
    }

    private void printMap(){
        List<Coordinate> coords = this.processedInput.keySet().stream().collect(Collectors.toList());
        Collections.sort(coords);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(this.processedInput.get(new Coordinate(j, i))+" ");
            }
            System.out.println();
        }
    }


    
}
