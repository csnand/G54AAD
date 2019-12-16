import javax.management.ObjectName;

public class DCLinkedList {
    protected Node start, end;
    private int size;

    public DCLinkedList () {
        start = null;
        end = null;
        size = 0;
    }

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
            size++;
            return;
        }

        node.setLeft(end);
        end.setRight(node);
        start.setLeft(node);
        node.setRight(start);
        start = node;
        size++;
    }

    public void insertAtEnd(Object object) {
        Node node = new Node(object);
        if (isEmpty()) {
            node.setRight(node);
            node.setLeft(node);
            start = node;
            end = start;
            size++;
            return;
        }

        node.setLeft(end);
        end.setRight(node);
        start.setLeft(node);
        node.setRight(start);
        end = node;
        size++;
    }

    public void deleteStart () {
        if (size == 1) {
            start = null;
            end = null;
            size = 0;
            return;
        }
        start = start.getRight();
        start.setLeft(end);
        end.setRight(start);
        size--;
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

    public int getSize () {
        return size;
    }

    public void display() {
        System.out.print("\nCircular Doubly Linked List = ");
        Node ptr = start;
        if (isEmpty()) {
            System.out.print("empty\n");
            return;
        }
        if (start.getRight() == start) {
            System.out.print(start.getData()+ " <-> "+ptr.getData().toString()+ "\n");
            return;
        }
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getRight();
        while (ptr.getRight() != start) {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getRight();
        }
        System.out.print(ptr.getData()+ " <-> ");
        ptr = ptr.getRight();
        System.out.print(ptr.getData()+ "\n");
        System.out.print("size: " + size);
    }
    
}
