package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum DailyFilePath {

    ONE("src/resources/Day 1"),
    TWO("src/resources/Day 2"),
    THREE("src/resources/Day 3"),
    FOUR("src/resources/Day 4"),
    FIVE("src/resources/Day 5"),
    SIX("src/resources/Day 6"),
    SEVEN("src/resources/Day 7"),
    EIGHT("src/resources/Day 8"),
    NINE("src/resources/Day 9"),
    TEN("src/resources/Day 10"),
    ELEVEN("src/resources/Day 11");


    private Path folderPath;

    private DailyFilePath(String folderPath){
      this.folderPath = Paths.get(folderPath);
    }

    public Path getPath(){
        return this.folderPath;
    }
    
}
