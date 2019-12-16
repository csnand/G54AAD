class Node {
    private Object object;
    private Node left, right;

    public Node() {
        this(null, null, null);
    }

    public Node(Object object) {
        this(object, null, null);
    }

    public Node(Object o, Node l, Node r) {
        object = o;
        right = r;
        left = l;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Object getData() {
        return object;
    }



}