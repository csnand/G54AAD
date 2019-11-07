import java.util.Comparator;
import java.util.LinkedList;

public class Path {
    private double cost;
    private Vertex currentVertex;
    private LinkedList<Edge> path;

    public Path(Vertex initV){
        path = new LinkedList<>();
        currentVertex = initV;
        cost = 0;
    }

    public Path(Path p){
        cost = p.cost;
        path = new LinkedList<>();
        path.addAll(p.path);
    }

    public Path(Path p, Edge e){
        cost = p.cost;
        path = new LinkedList<>();
        path.addAll(p.path);
        path.addLast(e);
        currentVertex = e.toV();
        cost += e.getWeight();
    }

    class PathComparator implements Comparator<Path>{

        @Override
        public int compare(Path p1, Path p2) {
            if (p1.cost == p2.cost){
                return 0;
            }

            return p1.cost > p2.cost ? 1 : -1;
        }
    }
}
