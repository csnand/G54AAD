public class DCLinkedList {
    protected Node start;
    protected Node end ;

    public DCLinkedList () {
        start = null;
        end = null;
    }

    public boolean isEmpty() {
        return start == null;
    }

    public void insertAtStart(Object object) {
        Node node = new Node(object);
        if (isEmpty()) {
            node.setNext(node);
            node.setPrev(node);
            start = node;
            end = start;
            return;
        }

        node.setPrev(end);
        end.setNext(node);
        start.setPrev(node);
        node.setNext(start);
        start = node;
    }

    public void insertAtEnd(Object object) {
        Node node = new Node(object);
        if (isEmpty()) {
            node.setNext(node);
            node.setPrev(node);
            start = node;
            end = start;
            return;
        }

        node.setPrev(end);
        end.setNext(node);
        start.setPrev(node);
        node.setNext(start);
        end = node;
    }

    public void moveRight() {
        if (isEmpty()) return;
        start = start.getNext();
        end = end.getPrev();
    }

    public void moveLeft () {
        if (isEmpty()) return;
        start = start.getPrev();
        end = end.getNext();
    }

    public void display() {
        System.out.print("\nCircular Doubly Linked List = ");
        Node ptr = start;
        if (isEmpty()) {
            System.out.print("empty\n");
            return;
        }
        if (start.getNext() == start) {
            System.out.print(start.getData()+ " <-> "+ptr.getData().toString()+ "\n");
            return;
        }
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getNext();
        while (ptr.getNext() != start) {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getNext();
        }
        System.out.print(ptr.getData()+ " <-> ");
        ptr = ptr.getNext();
        System.out.print(ptr.getData()+ "\n");
    }
    
}
