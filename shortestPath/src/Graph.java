import java.util.Vector;

public class Graph {
    public Vector<Vertex> allVertices;
    public Vector<Edge> allEdges;

    public Graph(String graph) {
        allVertices = new Vector<>();
        allEdges = new Vector<>();
        parseGraph(graph);
    }

    public void adjacencyList() {

    }

    public void adjacencyGraph() {

    }

    public Vertex searchVertex(int vertex) {
        for (Vertex v : allVertices) {
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
            int vertexName = Integer.parseInt(arr[0]);
            int vertexTo = Integer.parseInt(arr[1]);
            double weight = Double.parseDouble(arr[2]);

            // if vertex exists -> update the record
            // else create new
            Vertex newVFrom = searchVertex(vertexName);
            if (newVFrom == null) {
                newVFrom = new Vertex(vertexName);
                allVertices.add(newVFrom);
            }
            Vertex newVTo = searchVertex(vertexTo);
            if (newVTo == null) {
                newVTo = new Vertex(vertexTo);
                allVertices.add(newVTo);
            }
            
            //add new edge to vertex and graph
            Edge newEdge = new Edge(newVFrom, newVTo, weight);
            allEdges.add(newEdge);

            newVFrom.addEdge(newEdge);

//            System.out.printf("%d -> %d : %f\n", vertexName, vertexTo, weight);
        }
    }

}
