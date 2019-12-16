class Node {
    private Object object;
    private Node next, prev;

    public Node() {
        this(null, null, null);
    }

    public Node(Object object) {
        this(object, null, null);
    }

    public Node(Object o, Node n, Node p) {
        object = o;
        next = n;
        prev = p;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setData(Object o) {
        object = o;
    }

    public Object getData() {
        return object;
    }

}