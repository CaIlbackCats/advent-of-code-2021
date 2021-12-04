package three;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import utils.AdventSolver;
import utils.FileHandler;

public class BinaryDiagnostic extends AdventSolver<List<String>> {

    private static String INPUT_FOLDER = "/workspace/advent-of-code-2021/src/resources/Day 3";

    public BinaryDiagnostic(){
      super(INPUT_FOLDER);
    }

    //TODO refactor!
    @Override
    protected int findPartOneResult() {
      int[] sums = new int [this.processedInput.get(0).length()];
        this.processedInput.forEach(row -> addValueToSumArray(sums, row));
      return getConsumption(sums);
    }

    @Override
    protected int findPartTwoResult() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    protected List<String> processInput(Path path) {
      return FileHandler.createListFromInput(path);
    }

    private void addValueToSumArray(int [] array,String line){
      IntStream.range(0, line.length()).forEach(index ->array[index]+= Integer.parseInt(line.charAt(index)+""));
    }

    private int convertSumValueToBit(int value,boolean common){
      if(common){
        return (this.processedInput.size()-value<value)?1:0;
      }else{
        return (this.processedInput.size()-value>value)?1:0;
      }
    }

    private int getConsumption(int [] sums){
      StringBuilder gammaRate = new StringBuilder();
      StringBuilder epsilonRate = new StringBuilder();
      Arrays.stream(sums).forEach(number -> {gammaRate.append(convertSumValueToBit(number, true)); epsilonRate.append(convertSumValueToBit(number, false));});
      return Integer.valueOf(gammaRate.toString(),2)*Integer.valueOf(epsilonRate.toString(),2);
    }

    
}
