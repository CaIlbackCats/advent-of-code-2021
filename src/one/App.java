package one;

public class App {
    public static void main(String[] args) throws Exception {

        SonarSweep sonarSweep = new SonarSweep();
        long start = System.nanoTime();
        sonarSweep.solve();
        long end = System.nanoTime();
        long result = (end-start)/1000000;
        System.out.println("Duration:\t"+result);

    }
}
