package eight;

public enum SegmentPattern {
    
    ZERO("abcefg"),
    ONE("cf"),
    TWO("acdeg"),
    THREE("acdfg"),
    FOUR("bcdf"),
    FIVE("abdfg"),
    SIX("abdefg"),
    SEVEN("acf"),
    EIGHT("abcdefg"),
    NINE("abcdfg");

    private String pattern;

    private SegmentPattern(String pattern){
        this.pattern = pattern;
    }

    public String getPattern(){
        return this.pattern;
    }
}
