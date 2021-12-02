package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    
    public static List<Integer> createIntListFromInput(Path path){
        List<Integer> intList = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String oneLine;
            while((oneLine = bufferedReader.readLine())!=null){
                intList.add(Integer.valueOf(oneLine));
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return intList;
    }
}
