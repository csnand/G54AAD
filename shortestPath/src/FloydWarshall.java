import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;

public class FloydWarshall {
    private ArrayList<Vertex> allVertices;
    private ArrayList<ArrayList<Edge>> aMatrix;

    public FloydWarshall(ArrayList<Vertex> allVertices , ArrayList<ArrayList<Edge>> aMatrix){
        this.allVertices = allVertices;
        this.aMatrix = aMatrix;
    }

    public Double[][] execFloydWarshall(){
        Double[][] dist = new Double[aMatrix.size()][aMatrix.size()];
        //initialise dist matrix
        for (int i = 0; i < aMatrix.size(); i++){
            for (int j = 0; j < aMatrix.size(); j++){
                if (i == j){
                    dist[i][j] = 0.0;
                } else if (aMatrix.get(i) == null || aMatrix.get(i).get(j) == null){
                    dist[i][j] = Double.MAX_VALUE;
                } else {
                    dist[i][j] = aMatrix.get(i).get(j).getWeight();
                }
            }
        }

        for (int k = 0; k < aMatrix.size(); k++){
            for (int i = 0; i < aMatrix.size(); i++){
                for (int j = 0; j < aMatrix.size(); j++){
                    if (dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    public void shortestPath(){
        Double[][] aMatrix = execFloydWarshall();
        Collections.sort(allVertices, new Vertex.VertexComparator());

        //print table
        System.out.printf("Floyd-Warshall Algorithm \n\t\t");
        for (Vertex v : allVertices){
            System.out.printf("%d  \t\t", v.getCurrentVertex());
        }
        System.out.printf("\n");

        //print matrix
        for (int i = 0; i < aMatrix.length; i++){
            System.out.printf("%d\t\t", allVertices.get(i).getCurrentVertex());
            for (Double e : aMatrix[i]){
                System.out.printf("%s \t", e == Double.MAX_VALUE ? ".\t" : Double.toString(e));
            }
            System.out.printf("\n");
        }

    }
}