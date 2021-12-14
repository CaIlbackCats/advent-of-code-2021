package utils;

public class SolutionPrinter {

    public static <T,U> void print(AdventSolver<T,U> adventSolver){
        long start = System.nanoTime();
        adventSolver.solve();
        long end = System.nanoTime();
        long result = (end-start)/1000000;
        System.out.println("Duration:\t"+result);
    }
    
}
