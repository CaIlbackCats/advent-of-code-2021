package two;

import java.util.Map;

import utils.AdventSolver;
import utils.Constants;
import utils.DailyFilePath;
import utils.InputProcessorService;

public class Dive extends AdventSolver<Map<String,Integer>,Integer> {

    public Dive(){
      super(DailyFilePath.TWO,inputs -> InputProcessorService.convertInputToStringIntegerMap(inputs));
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
