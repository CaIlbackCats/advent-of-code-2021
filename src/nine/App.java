package nine;

import utils.InputProcessorService;
import utils.SolutionPrinter;

public class App {
    public static void main(String[] args) throws Exception {
        SolutionPrinter.print(new SmokeBasin(inputs -> InputProcessorService.covertInputToCoordinateMap(inputs)));
    }
}
