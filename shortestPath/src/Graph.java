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

    //convert Graph class to Adjacency List
    //instead of storing data in list of pair
    //this implementation stores data in list of edge objects
    public ArrayList<ArrayList<Edge>> toAdjacencyList() {
        ArrayList<ArrayList<Edge>> aList = new ArrayList<>();
        Collections.sort(allVertices, new Vertex.VertexComparator());

        for (Vertex v : allVertices){
            Collections.sort(v.getEdges(), new Edge.EdgeToComparator());
            aList.add(v.getEdges());
        }
        return aList;
    }

    public void printAjacencyList(ArrayList<ArrayList<Edge>> aList){
//        ArrayList<ArrayList<Edge>> aList = toAdjacencyList();
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

    //convert Graph class to Adjacency Matrix
    //instead of storing data in list of n * n array of doubles
    //this implementation stores data in n * n array of edge objects
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

    public void printAjacencyMatrix(ArrayList<ArrayList<Edge>> aMatrix){
//        ArrayList<ArrayList<Edge>> aMatrix = toAdjacencyMatrix();
        //print table
        System.out.printf("Ajacency Matrix \n\t");
        for (Vertex v : allVertices){
            System.out.printf("%d  \t", v.getCurrentVertex());
        }
        System.out.printf("\n");

        //print matrix
        for (int i = 0; i < aMatrix.size(); i++){
            System.out.printf("%d\t", allVertices.get(i).getCurrentVertex());
            for (Edge e : aMatrix.get(i)){
                System.out.printf("%s \t", e == null ? "." : Double.toString(e.getWeight()));
            }
            System.out.printf("\n");
        }
    }

    public ArrayList fromListToMatrix(ArrayList<ArrayList<Edge>> list) {
        ArrayList<ArrayList<Edge>> aList = new ArrayList<>();
        aList.addAll(list);

        for (ArrayList<Edge> edges : aList){
            if (edges.size() == aList.size()){
                continue;
            }
            for (int i = 0; i < edges.size(); i++){
                if (edges.get(i) == null){
                    continue;
                }
                if (edges.get(i).toV().getCurrentVertex() != i){
                    edges.add(i, null);
                }
            }
            if (edges.size() != aList.size()){
                for (int i = 0, j = aList.size() - edges.size(); i < j; i++){
                    edges.add(null);
                }
            }
        }

        return aList;
    }

    public ArrayList fromMatrixToList(ArrayList<ArrayList<Edge>> matrix) {
        ArrayList<ArrayList<Edge>> aMatrix = new ArrayList<>();
        aMatrix.addAll(matrix);

        aMatrix.removeIf(edges -> edges == null);
        for (ArrayList<Edge> edges : aMatrix){
            edges.removeIf(edge -> edge == null);
        }
        return aMatrix;
    }

    //this function will print
    //true
    //true
    public void testConversion() {
        //the result of direct conversion from Graph class to Ajacency List
        //should equals to first convert to Ajacency Matrix then convert to Ajacency List
        System.out.println(toAdjacencyList().equals(fromMatrixToList(toAdjacencyMatrix())));
        //same idea as above
        System.out.println(toAdjacencyList().equals(fromListToMatrix(toAdjacencyList())));
    }

    public Vertex getOrigin(){
        return origin;
    }

    public ArrayList<Vertex> getAllVertices() {
        return allVertices;
    }

    public Vertex searchVertex(int vertex) {
        for (Vertex v : allVertices) {
            if (v.getCurrentVertex() == vertex) {
                return v;
            }
        }
        return null;
    }

    // convert edges into Graph class
    private void parseGraph(String graph) {
        //convert to array of egdes
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
