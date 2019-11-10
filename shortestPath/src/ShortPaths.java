import java.io.*;
import java.util.Collections;


public class ShortPaths {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java ShortPath pathToTestFile");
            return;
        }

        ShortPaths shortPaths = new ShortPaths();
        String preParsedGraph = shortPaths.readFile(args[0]);
        Graph g = new Graph(preParsedGraph);
        g.printAjacencyMatrix();

//        Dijkstra dijkstra = new Dijkstra();
//        dijkstra.shortestPath();

        FloydWarshall floydWarshall = new FloydWarshall(g.getAllVertices(), g.toAdjacencyMatrix());
        floydWarshall.shortestPath();
    }


    // this function will read test file, convert graph from tuples to
    // INT INT DOUBLE format and return the result
    public String readFile(String path) {

        String testGraph = "";
        try  {
            BufferedReader bf = new BufferedReader(new FileReader(path));

            String s = "";
            while ((s = bf.readLine()) != null){
                testGraph += s;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testGraph.replace(")(", "\n").replace("(", "").replace(")", "").replace(",", " ");
    }
}
