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

    public String toString() {
        return super.toString();
    }

}
