public class Node<T> {
    private T object;
    public int degree;
    public Node<T> left, right;
    public Node<T> parent, child;
    public Node(T o) {
        object = o;
        left = right = this;
    }
    public T getData() {
        return object;
    }
    public void delete() {
        left.right = right;
        right.left = left;
    }

    public void link(Node<T> node) {
        node.delete();
        if(child == null) {
            child = node;
            node.parent = this;
            degree++;
            return;
        }

        child.insert(node);
        node.parent = this;
        degree++;
    }

    public void insert(Node<T> node) {
        node.left = left;
        node.right = this;
        left.right = node;
        left = node;
    }
}