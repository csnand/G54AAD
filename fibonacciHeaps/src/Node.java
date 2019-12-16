public class Node {
    public Object object;
    public Node left;
    public Node right;

    public Node () {
        this(null);
    }

    public Node (Object object) {
        this(object, null, null);
    }

    public Node (Object object, Node left, Node right) {
        this.object = object;
        this.left = left;
        this.right = right;
    }

}
