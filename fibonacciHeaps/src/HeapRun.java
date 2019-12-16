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
        heapRun.parse(args[0]);


    }

    public void parse(String path) {
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
        System.out.println(ops);
    }
}
