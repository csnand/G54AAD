import java.util.Collections;
import java.util.ArrayList;

public class Dijkstra {
    private Vertex fromV;
    private Vertex toV;
    private Graph graph;
    private ArrayList<Path> openList;
    private ArrayList<Path> closedList;

    public Dijkstra(Vertex fromV, Vertex toV, Graph graph){
        this.fromV = fromV;
        this.toV = toV;
        this.graph = graph;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        openList.add(new Path(this.fromV));
    }

    public void shortestPath(){
        Path result = execDijkstra();
        System.out.printf("%d --> %d :  %.2f\n", fromV.getCurrentVertex(),
                                                 toV.getCurrentVertex(),
                                                 result == null ? Integer.MAX_VALUE : result.getCost());
    }

    private Path execDijkstra(){
        while (openList.size() > 0){
            // if current Vertex equals to
            // the destination, return directly
            Path firstPath = openList.get(0);
            openList.remove(0);
            if (firstPath.getCurrentVertex() == toV) {
                return firstPath;
            }

            //explore current vertex
            for (Edge e : firstPath.getCurrentVertex().getEdges()){
                //make a copy of path to be explored
                Path currentPath = new Path(firstPath);
                Vertex nextV = e.toV();
                //if the vertex is already been explored
                //and still need to be explored
                //update the cost accordingly
                if (isInOpenList(nextV)){
                    int removeV = 0;
                    for (int i = 0; i < openList.size(); i++){
                        Path temp = openList.get(i);
                        if (temp.getCurrentVertex() == e.toV()){
                            removeV = i;
                            break;
                        }
                    }
                    openList.remove(removeV);
                    openList.add(new Path(firstPath, e));
                } else if (isInClosedList(nextV)){
                    //if the vertex is been explored and finished exploring, continue
                } else {
                    //if the vertex is never been discovered before, add to open list
                    openList.add(new Path(firstPath, e));
                }
            }
            //update open closed list after finishing exploring for each vertex
            closedList.add(firstPath);
            Collections.sort(openList, new Path.PathComparator());
        }
        return null;
    }

    private boolean isInPath(ArrayList<Path> paths, Vertex v){
        for (Path p : paths){
            if (p.getCurrentVertex() == v){
                return true;
            }
        }
        return false;
    }

    private boolean isInOpenList(Vertex v){
        return isInPath(openList, v);
    }

    private boolean isInClosedList(Vertex v){
        return isInPath(closedList, v);
    }
}
