package six;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.AdventSolver;
import utils.DailyFilePath;
import utils.FileHandler;

public class Lanternfish extends AdventSolver<List<BreedingCycle>> {

    private static final int PART_ONE_DAY_COUNTER = 80;
    private static final int PART_TWO_DAY_COUNTER = 256;
    

    protected Lanternfish() {
        super(DailyFilePath.SIX);
    }

    @Override
    protected int findPartOneResult() {
       return (int) getNumberOfFishAfterDay(PART_ONE_DAY_COUNTER, new ArrayList<>(this.processedInput));
    }

    @Override
    protected int findPartTwoResult() {
        return  (int) getNumberOfFishAfterDay(PART_TWO_DAY_COUNTER, new ArrayList<>(this.processedInput));
    }

    @Override
    protected List<BreedingCycle> processInput(Path path) {        
        return FileHandler.createListFromInput(path).stream()
                                                    .map(line -> Arrays.stream(line.split(","))
                                                                        .map(Integer::valueOf)
                                                                        .map(BreedingCycle::new)
                                                                        .collect(Collectors.toList()))
                                                    .flatMap(Collection::stream)
                                                    .collect(Collectors.toList());
    }

    private long getNumberOfFishAfterDay(int dayCounter, List<BreedingCycle> cycles){
        int addNewCounter =0;
        for (int i = 0; i < dayCounter; i++) {
            List<BreedingCycle> newBreeds = IntStream.range(0, addNewCounter).mapToObj(index -> new BreedingCycle()).collect(Collectors.toList());
            cycles.addAll(newBreeds);
            addNewCounter=0;
            for (int j = 0; j < cycles.size(); j++) {
                BreedingCycle bc = cycles.get(j);
                bc.breed();
                if(bc.getSpawn()==0){
                    addNewCounter++;
                }   
            }
        }
        return cycles.size();
    }
    
}
