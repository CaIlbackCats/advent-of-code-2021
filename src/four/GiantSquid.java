package four;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utils.AdventSolver;
import utils.FileHandler;

public class GiantSquid extends AdventSolver<Bingo> {

    private static final Integer TABLE_LENGTH = 5;
    
    private static final String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 4";

    protected GiantSquid() {
        super(INPUT_FOLDER);
    }

    @Override
    protected int findPartOneResult() {
        List<Integer> draws = this.processedInput.getDrawnNumbers();
        Map<Integer,List<NumberLocation>> tableLocationMap = new HashMap<>();
        Map<Integer,List<NumberLocation>> bingoMap = this.processedInput.getBingoMap();
        boolean isBingo =false;
        while(!isBingo){
            for (int i = 0; i < draws.size() && !isBingo; i++) {
                 Integer drawnNumber = draws.get(i);
                 List<NumberLocation> locations = bingoMap.get(drawnNumber);
                 for (int j = 0; j < locations.size() && !isBingo; j++) {
                     NumberLocation currentLocation = locations.get(j);
                     isBingo = calculateBingo(tableLocationMap, currentLocation);
                     bingoMap.remove(currentLocation.getNumber());
                     if(isBingo){
                       int result =  bingoMap.values().stream()
                                         .flatMap(Collection::stream)
                                         .filter(location -> location.getTableNumber()==currentLocation.getTableNumber())
                                         .map(NumberLocation::getNumber)
                                         .reduce(Integer::sum)
                                         .orElse(-1);
                         return result * currentLocation.getNumber();
                     }
                 }
             }
        }
         return 0;
    }

    @Override
    protected int findPartTwoResult() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected Bingo processInput(Path path) {
        List<String> lines = FileHandler.createListFromInput(path);
        Bingo bingo = new Bingo();
        Map<Integer,List<NumberLocation>> bingoMap = new HashMap<>();
        int tableCounter=-1;
        int x = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if(tableCounter==-1){
                List<Integer> drawnNumbers =Arrays.stream(line.split(",")).map(Integer::valueOf).collect(Collectors.toList());
                bingo.setDrawnNumbers(drawnNumbers);
                tableCounter++;  
            }
            else if(line.equals("")){
                tableCounter++;
                x=0;
            }
            else if(!line.equals("") && tableCounter>-1){
                int currentTable = tableCounter;
                int currentX = x;
                List<Integer> splitLines = new ArrayList<>(Arrays.asList(line.split(" "))).stream().filter(oneChar -> !oneChar.equals("")).map(Integer::valueOf).collect(Collectors.toList());
                splitLines.stream().map(number -> new NumberLocation(currentTable,Integer.valueOf(number),currentX,splitLines.indexOf(number))).forEach(location -> addToMap(bingoMap,location));                
                x++;
            }
        }
        bingo.setBingoMap(bingoMap);
        return bingo;
    }

    private void addToMap(Map<Integer,List<NumberLocation>> bingoMap,NumberLocation numberLocation){
        Integer currentNumber = numberLocation.getNumber();
        if(bingoMap.get(currentNumber)==null){
            List<NumberLocation> numberLocations = new ArrayList<>();
            numberLocations.add(numberLocation);
            bingoMap.put(currentNumber, numberLocations);
        }else{
            List<NumberLocation> locations = bingoMap.get(currentNumber);
            locations.add(numberLocation);
            bingoMap.put(currentNumber, locations);
        }
    }

    private boolean calculateBingo(Map<Integer,List<NumberLocation>> tableLocationMap,NumberLocation numberLocation){
        Integer currentTable = numberLocation.getTableNumber();
        if(tableLocationMap.get(currentTable)==null){
            List<NumberLocation> numberLocations = new ArrayList<>();
            numberLocations.add(numberLocation);
            tableLocationMap.put(currentTable, numberLocations);
            return false;
        }else{
            List<NumberLocation> locations = tableLocationMap.get(currentTable);
            locations.add(numberLocation);
            tableLocationMap.put(currentTable, locations);
            return locations.stream().map(NumberLocation::getX).filter(x -> numberLocation.getX()==x).count()==TABLE_LENGTH;
        }
    }
    
}
