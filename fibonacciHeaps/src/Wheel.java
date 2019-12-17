public class Wheel<T> {
    private Node<T> start, end;
    public int count;

    public Wheel () {
        start = null;
        end = null;
        count = 0;
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

    public T headW() {
        if (isEmptyW()) return null;
        return start.getData();
    }

    public void insertW (T o) {
        insertAtEnd(o);
        moveLeft();
    }

    public T extractW () {
        T headW = headW();
        deleteStart();
        return headW;
    }

    public Node<T> extractNodeW(){
        if (isEmptyW()) return null;
        Node<T> node = start;
        deleteStart();
        return start;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }


//----------- wheel operations end -----------


//----------- doubly circular linked list operations start -----------

    public boolean isEmpty() {
        return start == null;
    }

    public void insertAtEnd(T object) {
        Node<T> node = new Node(object);
        if (isEmpty()) {
            node.right = node;
            node.left = node;
            start = node;
            end = start;
            count++;
            return;
        }

        node.left =  end;
        end.right = node;
        start.left = node;
        node.right = start;
        end = node;
        count++;
    }

    public void deleteStart () {
        if (count == 1) {
            start = null;
            end = null;
            count = 0;
            return;
        }
        start = start.right;
        start.left = end;
        end.right = start;
        count--;
    }

    public void moveRight() {
        if (isEmpty()) return;
        start = start.right;
        end = end.left;
    }

    public void moveLeft () {
        if (isEmpty()) return;
        start = start.left;
        end = end.right;
    }

//----------- doubly circular linked list operations end -----------
}
