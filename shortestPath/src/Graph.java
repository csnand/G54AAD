import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> allVertices;
    private ArrayList<Edge> allEdges;
    private Vertex origin;

    public Graph(String graph) {
        allVertices = new ArrayList<>();
        allEdges = new ArrayList<>();
        parseGraph(graph);
    }

    public ArrayList adjacencyList() {
        return  null;
    }

    public ArrayList adjacencyMatrix() {
        return  null;
    }

    public ArrayList fromListToMatrix(ArrayList list) {
        return null;
    }

    public ArrayList fromMatrixToList(ArrayList matrix) {
        return null;
    }

    public void testConversion(){
    }

    public ArrayList<Vertex> getAllVertices(){
        return allVertices;
    }

    public ArrayList<Edge> getAllEdges(){
        return allEdges;
    }

    public Vertex getOrigin(){
        return origin;
    }


    public Vertex searchVertex(int vertex) {
        for (Vertex v : allVertices) {
            if (v.getCurrentVertex() == vertex) {
                return v;
            }
        }
        return null;
    }

    private void parseGraph(String graph) {
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
                if (newVFrom.getCurrentVertex() == 0){
                    origin = newVFrom;
                }
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
        }
    }

}
