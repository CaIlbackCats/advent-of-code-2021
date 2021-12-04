package three;

import java.nio.file.Path;
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
      int[] sums = IntStream.range(0, this.processedInput.get(0).length()).map(n->0).toArray();
        this.processedInput.forEach(row -> {
        for(int i=0; i<row.length(); i++){
          sums[i] += Integer.parseInt(row.charAt(i)+"");
        }});
        String gammaRate="";
        String epsilonRate="";
        for(int i=0;i<sums.length;i++){
          String current = (this.processedInput.size()-sums[i]<sums[i])?"1":"0";
          gammaRate += current;
          epsilonRate += (current.equals("1"))?"0":"1";
        }
        int gammaInt = Integer.valueOf(gammaRate,2);
        int epsilonInt = Integer.valueOf(epsilonRate,2);
      return gammaInt*epsilonInt;
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

    
}
