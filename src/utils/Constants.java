package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Constants {
    
    public static final String FORWARD = "forward";
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String DEPTH = "depth";
    public static final String AIM = "aim";
    public static final String HORIZONTAL = "horizontal";
    public static final Integer TABLE_LENGTH = 5;    
    public static final List<Integer> EASY_DIGIT_LIST = Arrays.asList(2,3,4,7);
    public static final Map<String,Integer> ILLEGAL_CHARACTER_SCORE_MAP = Map.of(")",3,
                                                                              "]",57,
                                                                              "}",1197,
                                                                              ">",25137);

    public static final Map<String,Integer> CORRECT_SCORE_MAP = Map.of(")",1,
                                                                     "]",2,
                                                                     "}",3,
                                                                     ">",4);
    public static final Integer ENERGY_LEVEL_LIMIT = 9;
    public static final Integer MAX_OCTOPUS_STEP = 100;


}
