import java.util.ArrayList;

public class Dijkstra {
    private ArrayList<Vertex> allVertices;
    private Double[][] aMatrix;
    private ArrayList<Vertex> openList;
    private ArrayList<Vertex> closedList;

    //this implementation takes n * n array of edge objects as input
    //and immediately converted to n * n array of doubles
    public Dijkstra(ArrayList<Vertex> allVertices, ArrayList<ArrayList<Edge>> aMatrix){
        this.allVertices = allVertices;
        this.aMatrix = toDoubleMatrix(aMatrix);
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
    }

    //the function will run dijkstra algorithm and return
    // list of double indicates the distance from origin to every vertex
    public Double[] execDijkstra(int src){
        Double[] dist = new Double[allVertices.size()];

        for (int i = 0; i < dist.length; i++) {
            if (i == src){
                dist[i] = 0.0;
            } else {
                dist[i] = Double.MAX_VALUE;
            }
            openList.add(allVertices.get(i));
        }

        while (openList.size() > 0){
            Vertex u = minDist(dist);
            openList.remove(u);
            closedList.add(u);
            //relaxation
            for (int i = 0; i < allVertices.size(); i++)
                if (openList.contains(allVertices.get(i)) &&
                    aMatrix[allVertices.indexOf(u)][i] != Double.MAX_VALUE &&
                    dist[allVertices.indexOf(u)] != Double.MAX_VALUE &&
                    dist[allVertices.indexOf(u)] + aMatrix[allVertices.indexOf(u)][i] < dist[i]){
                        dist[i] = dist[allVertices.indexOf(u)] + aMatrix[allVertices.indexOf(u)][i];
                }
        }
        return dist;
    }

    private Vertex minDist(Double[] dist){
        Double min = Double.MAX_VALUE;
        Vertex minV = null;

        for (int i = 0; i < allVertices.size(); i++){
            if (openList.contains(allVertices.get(i)) && dist[i] <= min){
                min = dist[i];
                minV = allVertices.get(i);
            }
        }
        return minV;
    }

    public void shortestPath(){
        Double[] dist = execDijkstra(0);
        System.out.printf("Dijkstra's Algorithm \nShortest Paths from source:\n");
        for (int i = 0; i < dist.length; i++){
            System.out.printf("%d --> %d : %s\n", 0, i, Double.toString(dist[i]));
        }
    }

    private Double[][] toDoubleMatrix(ArrayList<ArrayList<Edge>> aMatrix){
        Double[][] aMatrixDouble = new Double[aMatrix.size()][aMatrix.size()];
        for (int i = 0; i < aMatrixDouble.length; i++){
            for (int j = 0; j < aMatrixDouble.length; j++){
                if (aMatrix.get(i) == null || aMatrix.get(i).get(j) == null){
                    aMatrixDouble[i][j] = Double.MAX_VALUE;
                } else {
                    aMatrixDouble[i][j] = aMatrix.get(i).get(j).getWeight();
                }
            }
        }
        return aMatrixDouble;
    }
}