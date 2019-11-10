import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    private ArrayList<Vertex> allVertices;
    private ArrayList<Edge> allEdges;
    private Vertex origin;

    public Graph(String graph) {
        allVertices = new ArrayList<>();
        allEdges = new ArrayList<>();
        parseGraph(graph);
    }

    public ArrayList<ArrayList<Edge>> toAdjacencyList() {
        ArrayList<ArrayList<Edge>> aList = new ArrayList<>();
        Collections.sort(allVertices, new Vertex.VertexComparator());

        for (Vertex v : allVertices){
            Collections.sort(v.getEdges(), new Edge.EdgeToComparator());
            aList.add(v.getEdges());
        }
        return aList;
    }

    public void printAjacencyList(){
        ArrayList<ArrayList<Edge>> aList = toAdjacencyList();
        System.out.printf("Ajacency List \n");
        for (ArrayList<Edge> edges : aList){
            System.out.printf("%d -> [ ", edges.get(0).fromV().getCurrentVertex());
            for (int i = 0; i < edges.size(); i++){
                Edge e = edges.get(i);
                System.out.printf("(%d,%.2f)", e.toV().getCurrentVertex(), e.getWeight());
                System.out.printf("%s ", i + 1 == edges.size() ? "" : ",");
            }
            System.out.printf("] \n");
        }
    }

    public ArrayList<ArrayList<Edge>> toAdjacencyMatrix() {
        ArrayList<ArrayList<Edge>> aMatrix = new ArrayList<>();
        Collections.sort(allVertices, new Vertex.VertexComparator());
        for (Vertex v : allVertices){
            ArrayList<Edge> edges = new ArrayList<>();
            Collections.sort(v.getEdges(), new Edge.EdgeToComparator());
            //initialise array for one row
            for (int i = 0; i < allVertices.size(); i++){
                edges.add(null);
            }
            for (Edge e : v.getEdges()){
                edges.set(e.toV().getCurrentVertex(), e);
            }
            aMatrix.add(edges);
        }
        return aMatrix;
    }

    public void printAjacencyMatrix(){
        ArrayList<ArrayList<Edge>> aMatrix = toAdjacencyMatrix();
        //print table
        System.out.printf("Ajacency Matrix \n\t\t");
        for (Vertex v : allVertices){
            System.out.printf("%d  \t\t", v.getCurrentVertex());
        }
        System.out.printf("\n");

        //print matrix
        for (int i = 0; i < aMatrix.size(); i++){
            System.out.printf("%d\t\t", allVertices.get(i).getCurrentVertex());
            for (Edge e : aMatrix.get(i)){
                System.out.printf("%s \t", e == null ? ".\t" : Double.toString(e.getWeight()));
            }
            System.out.printf("\n");
        }
    }

    public ArrayList fromListToMatrix(ArrayList<ArrayList<Edge>> list) {
        return null;
    }

    public ArrayList fromMatrixToList(ArrayList<ArrayList<Edge>> matrix) {
        return null;
    }

    public void testConversion() {
    }

    public ArrayList<Vertex> getAllVertices() {
        return allVertices;
    }

    public ArrayList<Edge> getAllEdges() {
        return allEdges;
    }

    public Vertex getOrigin() {
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
                // vertex 0 will be the default origin
                if (newVFrom.getCurrentVertex() == 0) {
                    origin = newVFrom;
                }
            }
            Vertex newVTo = searchVertex(vertexTo);
            if (newVTo == null) {
                newVTo = new Vertex(vertexTo);
                allVertices.add(newVTo);
            }

            // add new edge to vertex and graph
            Edge newEdge = new Edge(newVFrom, newVTo, weight);
            allEdges.add(newEdge);

            newVFrom.addEdge(newEdge);
        }
    }

}
