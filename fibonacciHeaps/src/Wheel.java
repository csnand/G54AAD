public class Wheel {
    protected Node start, end;
    protected Node parent, child;
    private int degree;

    public Wheel () {
        start = null;
        end = null;
        degree = 0;
    }

//----------- wheel operations start -----------
    public Wheel emptyW () {
        return new Wheel();
    }

    public boolean isEmptyW () {
        return isEmpty();
    }

    public void rightW() {
        if (isEmptyW()) return;
        moveRight();
    }

    public void leftW() {
        if (isEmptyW()) return;
        moveLeft();
    }

    public Object headW() {
        if (isEmptyW()) return null;
        return start.getData();
    }

    public void insertW (Object o) {
        insertAtEnd(o);
        moveLeft();
    }

    public Object extractW () {
        Object headW = headW();
        deleteStart();
        return headW;
    }



//----------- wheel operations end -----------


//----------- doubly circular linked list operations start -----------

    public boolean isEmpty() {
        return start == null;
    }

    public void insertAtStart(Integer object) {
        Node node = new Node(object);
        if (isEmpty()) {
            node.setRight(node);
            node.setLeft(node);
            start = node;
            end = start;
            degree++;
            return;
        }

        node.setLeft(end);
        end.setRight(node);
        start.setLeft(node);
        node.setRight(start);
        start = node;
        degree++;
    }

    public void insertAtEnd(Object object) {
        Node node = new Node(object);
        if (isEmpty()) {
            node.setRight(node);
            node.setLeft(node);
            start = node;
            end = start;
            degree++;
            return;
        }

        node.setLeft(end);
        end.setRight(node);
        start.setLeft(node);
        node.setRight(start);
        end = node;
        degree++;
    }

    public void deleteStart () {
        if (degree == 1) {
            start = null;
            end = null;
            degree = 0;
            return;
        }
        start = start.getRight();
        start.setLeft(end);
        end.setRight(start);
        degree--;
    }

    public void moveRight() {
        if (isEmpty()) return;
        start = start.getRight();
        end = end.getLeft();
    }

    public void moveLeft () {
        if (isEmpty()) return;
        start = start.getLeft();
        end = end.getRight();
    }

    public int getDegree() {
        return degree;
    }
//----------- doubly circular linked list operations end -----------
}
