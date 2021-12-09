package seven;

import utils.InputProcessorService;
import utils.SolutionPrinter;

public class App {
    public static void main(String[] args) throws Exception {
        SolutionPrinter.print(new TreacheryOFWhales(inputs -> InputProcessorService.convertInputSplitToIntList(inputs)));
    }
}
