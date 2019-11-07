import java.util.Vector;

public class Graph {
    public Vector<Vertex> vertices;
    public Vector<Edge> edges;

    public Graph(String graph) {
        vertices = new Vector<>();
        edges = new Vector<>();
        parseGraph(graph);
    }

    public void adjacencyList() {

    }

    public void adjacencyGraph() {

    }

    public Vertex searchVertex(int vertex) {
        for (Vertex v : vertices) {
            if (v.getCurrentVertex() == vertex) {
                return v;
            }
        }
        return null;
    }

    private void parseGraph(String graph){
        String[] graphList = graph.split("\n");
        for (String list : graphList) {
            String[] arr = list.split(" ");
            System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
        }
    }

}
