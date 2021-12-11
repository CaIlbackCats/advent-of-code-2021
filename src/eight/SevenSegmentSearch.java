package eight;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return getEasyDigits();
    }

    @Override
    public Integer findPartTwoResult() {
        return getHardDigits();
    }

    private int getEasyDigits(){
       return (int) this.processedInput.stream()
                                .map(input -> input.substring(input.indexOf("|")+2))
                                .map(input -> Arrays.stream(input.split(" ")).collect(Collectors.toList()))
                                .flatMap(Collection::stream)
                                .map(line -> line.length())                               
                                .filter(size -> Constants.EASY_DIGIT_LIST.contains(size))
                                .count();
    }

    private int getHardDigits(){
        int finalResult =0;
       List<List<String>> easyHardList = this.processedInput.stream().map(this::splitString).collect(Collectors.toList());
       for (List<String> easyHard : easyHardList) {
          String [] hard = easyHard.get(0).split(" | ");
         String one = Arrays.stream(hard).filter(segment -> segment.length()==2).findFirst().orElse("");
         String four = Arrays.stream(hard).filter(segment -> segment.length()==4).findFirst().orElse("");
         String seven = Arrays.stream(hard).filter(segment -> segment.length()==3).findFirst().orElse("");
         String eight = "abcdefg";
         String a = IntStream.range(0, seven.length())
                                .filter(index -> !one.contains(seven.charAt(index)+""))
                                .mapToObj(index -> seven.charAt(index)+"")
                                .collect(Collectors.toList()).get(0);
         String b = IntStream.range(0, eight.length())
                            .filter(index -> easyHard.get(0).chars()
                                                            .filter(current -> current == eight.charAt(index))
                                                            .count()==6)
                            .mapToObj(index -> eight.charAt(index)+"")
                            .collect(Collectors.toList()).get(0);
         String c = IntStream.range(0, eight.length())
                                .filter(index -> easyHard.get(0).chars()
                                                                .filter(current -> current == eight.charAt(index))
                                                                .count()==8 && eight.charAt(index)!=a.charAt(0))
                                .mapToObj(index -> eight.charAt(index)+"")
                                .collect(Collectors.toList()).get(0);
        String d =   IntStream.range(0, four.length())
                                    .filter(index -> !one.contains(four.charAt(index)+"") && four.charAt(index)!=b.charAt(0))
                                    .mapToObj(index -> four.charAt(index)+"")
                                    .collect(Collectors.toList()).get(0);
        String e = IntStream.range(0, eight.length())
                            .filter(index -> easyHard.get(0).chars()
                                                            .filter(current -> current == eight.charAt(index))
                                                            .count()==4)
                            .mapToObj(index -> eight.charAt(index)+"")
                            .collect(Collectors.toList()).get(0);
        String f = IntStream.range(0, one.length())
                            .filter(index -> !c.contains(one.charAt(index)+""))
                            .mapToObj(index -> one.charAt(index)+"")
                            .collect(Collectors.toList()).get(0);
        String g = IntStream.range(0, eight.length())
                            .filter(index -> easyHard.get(0).chars()
                                                            .filter(current -> current == eight.charAt(index))
                                                            .count()==7 && eight.charAt(index)!=d.charAt(0))
                            .mapToObj(index -> eight.charAt(index)+"")
                            .collect(Collectors.toList()).get(0);
        Map<String,String> solutionMap = new HashMap<>();
        solutionMap.put(a, "a");         
        solutionMap.put(b, "b");         
        solutionMap.put(c, "c");         
        solutionMap.put(d, "d");         
        solutionMap.put(e, "e");         
        solutionMap.put(f, "f");         
        solutionMap.put(g, "g"); 
        String [] easy = easyHard.get(1).split(" "); 
        String result = "";
        for (int i = 0; i < easy.length; i++) {
           String oneSegment = easy[i];
           StringBuilder sBuilder = new StringBuilder();
           IntStream.range(0,oneSegment.length()).mapToObj(index -> solutionMap.get(oneSegment.charAt(index)+"")).forEach(solution -> sBuilder.append(solution));
          char [] cArray = sBuilder.toString().toCharArray();
          Arrays.sort(cArray);
          for (SegmentPattern pattern : SegmentPattern.values()) {
              String partialResult = new String(cArray);
              if(partialResult.equals(pattern.getPattern())){
                result+=pattern.ordinal();
              }
          }
        }
        finalResult+= Integer.valueOf(result);
       }
        return finalResult;
    }


    private List<String> splitString(String input){
        String left = input.substring(0, input.indexOf("|")-1);
        String right = input.substring(input.indexOf("|")+2);
    return List.of(left,right);
    }
}

/*
a: 8
b: 6
c: 8
d: 7
e: 4
f: 9
g: 7

  0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg
*/
