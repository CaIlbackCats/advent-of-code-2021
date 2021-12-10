package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum DailyFilePath {

    ONE("/workspace/advent-of-code-2021/src/resources/Day 1"),
    TWO("/workspace/advent-of-code-2021/src/resources/Day 2"),
    THREE("/workspace/advent-of-code-2021/src/resources/Day 3"),
    FOUR("/workspace/advent-of-code-2021/src/resources/Day 4"),
    FIVE("/workspace/advent-of-code-2021/src/resources/Day 5"),
    SIX("/workspace/advent-of-code-2021/src/resources/Day 6"),
    SEVEN("/workspace/advent-of-code-2021/src/resources/Day 7"),
    EIGHT("/workspace/advent-of-code-2021/src/resources/Day 7");
    private Path folderPath;

    private DailyFilePath(String folderPath){
      this.folderPath = Paths.get(folderPath);
    }

    public Path getPath(){
        return this.folderPath;
    }
    
}
