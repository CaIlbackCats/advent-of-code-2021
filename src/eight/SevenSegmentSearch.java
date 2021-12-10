package eight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import utils.AdventSolver;
import utils.Constants;
import utils.DailyFilePath;
import utils.InputProcessor;

public class SevenSegmentSearch extends AdventSolver<List<String>,Integer> {

    protected SevenSegmentSearch(InputProcessor<List<String>> inputProcessor) {
        super(DailyFilePath.EIGHT, inputProcessor);
    }

    @Override
    public Integer findPartOneResult() {
        // TODO Auto-generated method stub
        return getEasyDigits();
    }

    @Override
    public Integer findPartTwoResult() {
        // TODO Auto-generated method stub
        return null;
    }

    private int getEasyDigits(){
       return (int) this.processedInput.stream()
                                .map(input -> input.substring(input.indexOf("|")+2))
                                .map(this::getLength)
                                .flatMap(Collection::stream)                                
                                .filter(size -> Constants.EASY_DIGIT_LIST.contains(size))
                                .count();
    }

    private List<Integer> getLength(String input){
       return Arrays.stream(input.split(" ")).map(line -> line.length()).collect(Collectors.toList());
    }
    
}
