package three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import utils.AdventSolver;
import utils.DailyFilePath;

public class BinaryDiagnostic extends AdventSolver<List<String>,Integer> {

    public BinaryDiagnostic(){
      super(DailyFilePath.THREE,inputs -> inputs);
    }

    @Override
    public Integer findPartOneResult() {
      int[] sums = getBitFromCommonCount(this.processedInput, true);
      return getConsumption(sums);
    }

    @Override
    public Integer findPartTwoResult() {
      int generator = getGeneratorOrScrubberRating(new ArrayList<>(this.processedInput), true);
      int scrubber = getGeneratorOrScrubberRating(new ArrayList<>(this.processedInput), false);
      return generator*scrubber;
    }

    //TODO refactor
    private int getGeneratorOrScrubberRating(List<String> diagnostics, boolean generator){
      int index =0;
      while(diagnostics.size()>1){
          int [] bitValues = getBitFromCommonCount(diagnostics, generator);
          int currentChar = bitValues[index];
          for (int i = 0; i < diagnostics.size() && diagnostics.size()>1; i++) {
              String currentLine = diagnostics.get(i);
              if(Integer.valueOf(currentLine.charAt(index)+"")!=currentChar){
                  diagnostics.remove(i);
                  i= -1;
              }
          }
          index++;
      }
    return Integer.valueOf(diagnostics.stream().findFirst().orElseThrow(),2);
    }

  private int [] getBitFromCommonCount(List<String> diagnostics, boolean common){
    int [] countArray= new int [this.processedInput.get(0).length()];
    diagnostics.forEach(line -> addValueToSumArray(countArray, line));
    if(common){
       return Arrays.stream(countArray).map(number -> (diagnostics.size()-number<=number)?1:0).toArray();
    }else{
      return Arrays.stream(countArray).map(number -> (diagnostics.size()-number<=number)?0:1).toArray();
    }
  }

    private void addValueToSumArray(int [] array,String line){
      IntStream.range(0, line.length()).forEach(index ->array[index]+= Integer.parseInt(line.charAt(index)+""));
    }

    private int getConsumption(int [] sums){
      StringBuilder gammaRate = new StringBuilder();
      StringBuilder epsilonRate = new StringBuilder();
      Arrays.stream(sums).forEach(number -> {gammaRate.append(number); epsilonRate.append((number==1)?0:1);});
      return Integer.valueOf(gammaRate.toString(),2)*Integer.valueOf(epsilonRate.toString(),2);
    }

    
}
