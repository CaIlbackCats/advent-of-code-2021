package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileHandler {
    
    public static List<String> createListFromInput(Path path){
        try{
            return Files.readAllLines(path);
        }catch(IOException io){
            System.out.println(io.getMessage());
            return Collections.emptyList();
        }
    }
}
