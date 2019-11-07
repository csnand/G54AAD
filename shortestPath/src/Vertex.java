import java.util.Comparator;
import java.util.Vector;

public class Vertex {
    private int currentVertex;
    private Vector<Edge> toVertices;

    public Vertex(int currentVertex) {
        this.currentVertex = currentVertex;
        toVertices = new Vector<>();
    }

    public void addEdge(Edge e) {
        this.toVertices.add(e);
    }

    public int getCurrentVertex() {
        return currentVertex;
    }

    public Vector<Edge> getEdges() {
        return toVertices;
    }

    class VertexComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex v1, Vertex v2) {
            return v1.getCurrentVertex() - v2.getCurrentVertex();
        }
    }

}
