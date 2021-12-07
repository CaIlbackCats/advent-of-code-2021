package four;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import utils.AdventSolver;
import utils.FileHandler;

public class GiantSquid extends AdventSolver<Bingo> {

    private static final Integer TABLE_LENGTH = 5;
    
    private static final String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 4";

    private List<Integer> alreadyBingoed = new ArrayList<>();

    protected GiantSquid() {
        super(INPUT_FOLDER);
    }

    @Override
    protected int findPartOneResult() {
        return getBingo(false);
    }

    @Override
    protected int findPartTwoResult() {
        return getBingo(true);
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

    
    private int getBingo(boolean lastBingo){
        List<Integer> draws = this.processedInput.getDrawnNumbers();
        Map<Integer,List<NumberLocation>> tableLocationMap = new HashMap<>();
        Map<Integer,List<NumberLocation>> bingoMap = this.processedInput.getBingoMap().entrySet().stream().collect(Collectors.toMap(Entry::getKey,entry -> new ArrayList<>(entry.getValue())));
        boolean isBingo =false;
        int tableCount = (int) bingoMap.values().stream().flatMap(Collection::stream).map(NumberLocation::getTableNumber).distinct().count();
        while(!isBingo){
            for (int i = 0; i < draws.size() && !isBingo; i++) {
                 Integer drawnNumber = draws.get(i);
                 List<NumberLocation> locations = bingoMap.get(drawnNumber);
                 if(locations!=null){
                    for (int j = 0; j < locations.size() && !isBingo; j++) {
                        NumberLocation currentLocation = locations.get(j);
                        boolean isTableBingo = calculateBingo(tableLocationMap, currentLocation);
                        bingoMap.remove(currentLocation.getNumber());
                        if(isTableBingo){
                        int result =  bingoMap.values().stream()
                                            .flatMap(Collection::stream)
                                            .filter(location -> location.getTableNumber()==currentLocation.getTableNumber())
                                            .map(NumberLocation::getNumber)
                                            .reduce(Integer::sum)
                                            .orElse(-1)* drawnNumber;
                            if(lastBingo){
                                isBingo = this.alreadyBingoed.size()==(tableCount);                            
                            }else{
                                isBingo = isTableBingo;
                            }
                            if(isBingo){
                                alreadyBingoed = new ArrayList<>();
                                return result;
                            }
                        }
                    }
                }
             }
        }
        return 0;        
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
        }else if(!this.alreadyBingoed.contains(currentTable)){
            List<NumberLocation> locations = tableLocationMap.get(currentTable);
            locations.add(numberLocation);
            tableLocationMap.put(currentTable, locations);
            int rowCounts = (int) locations.stream().map(NumberLocation::getX).filter(x -> numberLocation.getX()==x).count();
            int columnCounts = (int) locations.stream().map(NumberLocation::getY).filter(y -> numberLocation.getY()==y).count();
            boolean isRowBingo = rowCounts==TABLE_LENGTH;
            boolean isColumnBingo = columnCounts==TABLE_LENGTH;
            boolean isBingo = isRowBingo || isColumnBingo;
            if(isBingo){
                this.alreadyBingoed.add(currentTable);
                return isBingo;
            }
        }
        return false;
    }
    
}
