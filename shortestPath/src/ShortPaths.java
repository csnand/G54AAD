import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortPaths {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please input  path to a test file");
            return;
        }

        ShortPaths s = new ShortPaths();
        s.readFile(Paths.get("src/test/testGraph.txt").toAbsolutePath().toString());
    }

    public void readFile(String path) {

        String testGraph = "";
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()){
                testGraph += sc.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(testGraph.replace(")(", "\n")
                                    .replace("(", "")
                                    .replace(")", "")
                                    .replace(",", " "));

        ArrayList<int[]> graphArr = new ArrayList<>();
        String[] testGraphList = testGraph.split("\n");
        for (String list : testGraphList){
            String[] arr = list.split(" ");
            System.out.println(arr);
        }
    }
}
