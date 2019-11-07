import java.util.Vector;

public class Graph {
    private Vector<Vertex> allVertices;
    private Vector<Edge> allEdges;
    private Vertex origin;

    public Graph(String graph) {
        allVertices = new Vector<>();
        allEdges = new Vector<>();
        parseGraph(graph);
    }

    public Vector adjacencyList() {
        return  null;
    }

    public Vector adjacencyMatrix() {
        return  null;
    }

    public Vector fromListToMatrix(Vector list) {
        return null;
    }

    public Vector fromMatrixToList(Vector matrix) {
        return null;
    }

    public void testConversion(){
    }

    public Vector<Vertex> getAllVertices(){
        return allVertices;
    }

    public Vector<Edge> getAllEdges(){
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
