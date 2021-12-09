package utils;

import java.util.List;

public interface InputProcessor <T> {
    T process(List<String> inputs);
    
}
