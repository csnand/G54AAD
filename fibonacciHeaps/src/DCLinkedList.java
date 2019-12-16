public class DCLinkedList {
    protected Node start;
    protected Node end ;
    public int size;

    public DCLinkedList () {
        start = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty() {
        return start == null;
    }

    public int getSize() {
        return size;
    }

    public void insertAtStart(int val) {
        Node nptr = new Node(val, null, null);
        if (start == null) {
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;
        } else {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;
        }
        size++ ;
    }

    public void insertAtEnd(int val) {
        Node nptr = new Node(val, null, null);
        if (start == null) {
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            start = nptr;
            end = start;
        } else {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            end = nptr;
        }
        size++;
    }

    public void insertAtPos(int val , int pos) {
        Node nptr = new Node(val, null, null);
        if (pos == 1) {
            insertAtStart(val);
            return;
        }
        Node ptr = start;
        for (int i = 2; i <= size; i++) {
            if (i == pos) {
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();
        }
        size++ ;
    }

    public void deleteAtPos(int pos) {
        if (pos == 1) {
            if (size == 1) {
                start = null;
                end = null;
                size = 0;
                return;
            }
            start = start.getLinkNext();
            start.setLinkPrev(end);
            end.setLinkNext(start);
            size--;
            return ;
        }
        if (pos == size) {
            end = end.getLinkPrev();
            end.setLinkNext(start);
            start.setLinkPrev(end);
            size-- ;
        }
        Node ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++) {
            if (i == pos) {
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();

                p.setLinkNext(n);
                n.setLinkPrev(p);
                size-- ;
                return;
            }
            ptr = ptr.getLinkNext();
        }
    }

    public void display() {
        System.out.print("\nCircular Doubly Linked List = ");
        Node ptr = start;
        if (size == 0) {
            System.out.print("empty\n");
            return;
        }
        if (start.getLinkNext() == start) {
            System.out.print(start.getData()+ " <-> "+ptr.getData().toString()+ "\n");
            return;
        }
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != start) {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ " <-> ");
        ptr = ptr.getLinkNext();
        System.out.print(ptr.getData()+ "\n");
    }
    
}
