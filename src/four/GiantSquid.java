package four;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import utils.AdventSolver;
import utils.Constants;
import utils.DailyFilePath;
import utils.InputProcessor;

public class GiantSquid extends AdventSolver<Bingo,Integer> {


    private List<Integer> alreadyBingoed = new ArrayList<>();

    protected GiantSquid(InputProcessor<Bingo> inputProcessor) {
        super(DailyFilePath.FOUR,inputProcessor);
    }

    @Override
    public Integer findPartOneResult() {
        return getBingo(false);
    }

    @Override
    public Integer findPartTwoResult() {
        return getBingo(true);
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
            boolean isRowBingo = rowCounts==Constants.TABLE_LENGTH;
            boolean isColumnBingo = columnCounts==Constants.TABLE_LENGTH;
            boolean isBingo = isRowBingo || isColumnBingo;
            if(isBingo){
                this.alreadyBingoed.add(currentTable);
                return isBingo;
            }
        }
        return false;
    }
    
}
