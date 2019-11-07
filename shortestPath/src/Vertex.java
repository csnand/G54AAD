import java.util.Vector;

public class Vertex {
    private int currentVertex;
    private Vector<Edge> To;

    public Vertex(int currentVertex) {
        this.currentVertex = currentVertex;
    }

    public void addEdge(Edge e) {
        this.To.add(e);
    }

    public int getCurrentVertex() {
        return currentVertex;
    }

    public Vector<Edge> getTo() {
        return To;
    }

    public String toString() {
        return super.toString();
    }

}
