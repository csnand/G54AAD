import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HeapRun {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java HeapRun pathToTestFile");
            return;
        }

        HeapRun heapRun = new HeapRun();
        String[] tokens = heapRun.parse(args[0]);
        heapRun.runHeap(tokens);

    }

    public void runHeap (String[] tokens) {
        FibonacciHeaps fibonacciHeaps = new FibonacciHeaps();
//        FibHeaps fibonacciHeaps = new FibHeaps();
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "insert":
                    fibonacciHeaps.insertH(Integer.parseInt(tokens[i+1]));
                    i++;
                    break;
                case "minimum": System.out.println("minimum: " + fibonacciHeaps.minimumH()); break;
                case "extract": fibonacciHeaps.extractH(); break;
            }
        }
    }

    public String[] parse(String path) {
        String ops = "";
        try  {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String s = "";
            while ((s = bf.readLine()) != null){
                ops += s;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ops.trim().split(" ");
    }
}
