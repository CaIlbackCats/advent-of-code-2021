package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import five.Coordinate;
import four.Bingo;
import four.NumberLocation;

public class InputProcessorService {

    
    public static List<Integer> convertInputToIntList(List<String> inputs){
        return inputs.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static Map<String,Integer> convertInputToStringIntegerMap(List<String> inputs){
        Map<String,Integer> processedMap = new HashMap<>();
        Map<String,Integer> map = new HashMap<>();
        int aim = inputs.stream().map(line -> fillMap(line, map)).reduce(Integer::sum).orElse(-1);
        int horizontal = map.get(Constants.FORWARD);
        int depth = map.get(Constants.DOWN)-map.get(Constants.UP);
        processedMap.put(Constants.AIM, aim);
        processedMap.put(Constants.HORIZONTAL, horizontal);
        processedMap.put(Constants.DEPTH, depth);
        return processedMap;
    }

    public static Bingo convertInputToBingo(List<String> inputs){
      Bingo bingo = new Bingo();
      Map<Integer,List<NumberLocation>> bingoMap = new HashMap<>();
      int tableCounter=-1;
      int x = 0;
      for (int i = 0; i < inputs.size(); i++) {
          String line = inputs.get(i);
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

    public static List<String []> convertInputToStringArray(List<String> inputs){
     return inputs.stream().map(row->(row.replace(" -> ", ",")).split(",")).collect(Collectors.toList());
    }

    public static List<Integer> convertInputSplitToIntList(List<String> inputs){
      return inputs.stream()
                    .map(line -> Arrays.stream(line.split(","))
                                        .map(Integer::valueOf)
                                        .collect(Collectors.toList()))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
    }
    public static Map<Coordinate,Integer> covertInputToCoordinateMap(List<String> inputs){
      Map<Coordinate,Integer> coordinateMap = new HashMap<>();
      for (int i = 0; i < inputs.size(); i++) {
        //x oszlop y sor
        List<String> values = Arrays.stream(inputs.get(i).split("")).collect(Collectors.toList());
        for (int j = 0; j < values.size(); j++) {
          Coordinate coordinate = new Coordinate(j, i);
          coordinateMap.put(coordinate, Integer.valueOf(values.get(j)));
        }
      }
      return coordinateMap;
    }

    private static int fillMap(String line, Map<String,Integer> map){
        //clean version
      
         String [] separated = line.split(" ");
         String key = separated[0];
         Integer value = Integer.valueOf(separated[1]);
        if(map.get(key)==null){
          map.put(key, value);
        } else{
          Integer sumValue = map.get(key)+ value;
          map.put(key, sumValue);
        }
        if(key.equals(Constants.FORWARD)){
          return ((map.get(Constants.DOWN)==null?0:map.get(Constants.DOWN))-(map.get(Constants.UP)==null?0:map.get(Constants.UP)))*value;
        }
        return 0;
        // oneline version
        //map.put(separated[0],map.get(separated[0])==null?Integer.parseInt(separated[1]):map.get(separated[0])+Integer.parseInt(separated[1]));      
      }

      private static void addToMap(Map<Integer,List<NumberLocation>> bingoMap,NumberLocation numberLocation){
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
}
