package five;

import utils.InputProcessorService;
import utils.SolutionPrinter;

public class App {
    public static void main(String[] args) throws Exception {
        SolutionPrinter.print(new HydroThermalVenture(inputs -> InputProcessorService.convertInputToStringArray(inputs)));
    }
}
