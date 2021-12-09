package two;

import java.util.Map;

import utils.AdventSolver;
import utils.Constants;
import utils.DailyFilePath;
import utils.InputProcessor;

public class Dive extends AdventSolver<Map<String,Integer>,Integer> {

    public Dive(InputProcessor<Map<String,Integer>> inputProcessor){
      super(DailyFilePath.TWO,inputProcessor);
    }
    
    @Override
    public Integer findPartOneResult() {
      return this.processedInput.get(Constants.DEPTH)*this.processedInput.get(Constants.HORIZONTAL);
    }

    @Override
    public Integer findPartTwoResult() {
      return this.processedInput.get(Constants.AIM)*this.processedInput.get(Constants.HORIZONTAL);
    }
}
