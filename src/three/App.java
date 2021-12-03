package three;

public class App {
    public static void main(String[] args) throws Exception {

        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
        long start = System.nanoTime();
        binaryDiagnostic.solve();
        long end = System.nanoTime();
        long result = (end-start)/1000000;
        System.out.println("Duration:\t"+result);

    }
}
