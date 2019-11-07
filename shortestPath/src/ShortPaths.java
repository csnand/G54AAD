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
        String preParsedGraph = s.readFile(Paths.get("src/test/testGraph.txt").toAbsolutePath().toString());

        Graph g = new Graph(preParsedGraph);
    }

    // this function will read test file, convert graph from tuples to
    // INT INT DOUBLE format and return the result
    public String readFile(String path) {

        String testGraph = "";
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                testGraph += sc.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return testGraph.replace(")(", "\n").replace("(", "").replace(")", "").replace(",", " ");

    }
}
