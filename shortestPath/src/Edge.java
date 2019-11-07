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

}
