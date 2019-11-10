import java.util.Comparator;
import java.util.ArrayList;

public class Vertex {
    private int currentVertex;
    private ArrayList<Edge> toVertices;

    public Vertex(int currentVertex) {
        this.currentVertex = currentVertex;
        toVertices = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        this.toVertices.add(e);
    }

    public int getCurrentVertex() {
        return currentVertex;
    }

    public ArrayList<Edge> getEdges() {
        return toVertices;
    }

    static class VertexComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex v1, Vertex v2) {
            return v1.getCurrentVertex() - v2.getCurrentVertex();
        }
    }

}
