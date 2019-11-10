import java.util.Comparator;

public class Edge {
    private Vertex from;
    private Vertex to;
    private double weight;

    public Edge(Vertex from, Vertex to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Vertex fromV() {
        return from;
    }

    public Vertex toV() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    static class EdgeToComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.toV().getCurrentVertex() - e2.toV().getCurrentVertex();
        }
    }

}
