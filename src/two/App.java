package one;

public class App {
    public static void main(String[] args) throws Exception {

        Dive dive = new Dive();
        long start = System.nanoTime();
        dive.solve();
        long end = System.nanoTime();
        long result = (end-start)/1000000;
        System.out.println("Duration:\t"+result);

    }
}
