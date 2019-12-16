import javax.print.DocFlavor;

public class FibonacciHeaps {
    private int degree;
    private Wheel wheel;

    public FibonacciHeaps (){
        this(null, 0);
    }

    public FibonacciHeaps (Wheel wheel, int degree) {
        this.wheel = wheel;
        this.degree = degree;
    }

    public FibonacciHeaps emptyhH() {
        return new FibonacciHeaps();
    }

    public boolean isEmptyH() {
        return (wheel == null || wheel.isEmptyW()) && degree == 0;
    }

    public void insertH(int value) {
        Node node = new Node (value);
        if (wheel == null) wheel = new Wheel();
        wheel.insertW(value);
        if ((wheel.headW() != null) && (Integer)node.getData() < (Integer)wheel.headW())
            wheel.moveRight();
    }

    public void minimumH() {
        System.out.println("minimum: "+ wheel.headW() == null ? "null" : wheel.headW());
    }

    public int extractH() {
        if(min == null) return 0;
        int minimum = (Integer)min.getData();

        while (min.child != null) {
            removeNode(min.child);
            Node child =min.child;
            if(min.child.right==min.child)
                min.child = null;
            else
                min.child = min.child.right;
            addNode(child,min);
            child.parent =null;
        }
        removeNode(min);

        if(min.right == min) min =null;
        else{
            min=min.right;
            consolidate();
        }

        num--;
        return minimum;
    }



    public void consolidate() {
        int maxDegree = (int)Math.floor(Math.log(num)/Math.log(2));
        Node[] cons =new Node[maxDegree+1];
        for(int i = 0; i<=maxDegree; i++)
            cons[i]=null;

        while (min !=null) {
            Node x = extractMinTree();
            int d =x.degree;
            while(cons[d]!=null) {
                if(((Integer)cons[d].getData()< (Integer)x.getData())){
                    Node temp=x;
                    x=cons[d];
                    cons[d]=temp;
                }

                link(cons[d],x);
                cons[d]=null;
                d++;
            }
            cons[d]=x;
        }

        for(int i=0 ; i<=maxDegree; i++) {
            if (cons[i] !=null) {
                if(min == null)
                    min = cons[i];
                else {
                    addNode(cons[i],min);
                    if((Integer)cons[i].getData() < (Integer)min.getData())
                        min = cons[i];
                }

            }
        }

    }

    public void link(Node node1,Node node2) {
        removeNode(node1);
        if(node2.child == null)
            node2.child =node1;
        else addNode(node1,node2.child);
        node1.parent = node2;
        node2.degree++;
    }
    public void addNode(Node node,Node min) {
        node.left=min.left;
        node.right=min;
        min.left.right=node;
        min.left=node;
    }
    public void removeNode(Node node) {
        node.left.right=node.right;
        node.right.left=node.left;
    }

    public Node extractMinTree() {
        Node p =min;
        if (p == p.right)
            min = null;
        else {
            removeNode(p);
            min = p.right;
        }
        p.left=p.right=p;
        return p;
    }

}
