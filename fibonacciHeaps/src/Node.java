class Node{
    int val, degree;
    Node parent, child;
    Node left, right;

    public Node (int val) {
        this.val = val;
        left = right = this;
    }

    public void delete() {
        left.right = right;
        right.left = left;
    }

    public void link(Node node) {
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

    public void insert(Node node) {
        node.left = left;
        node.right = this;
        left.right = node;
        left = node;
    }


}