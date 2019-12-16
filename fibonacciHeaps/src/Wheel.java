public class Wheel {
    private DCLinkedList dcLinkedList;
    private int degree;

    public Wheel () {
        this(0);
    }

    public Wheel (int degree) {
        dcLinkedList = new DCLinkedList();
        this.degree = degree;
    }


    public Wheel emptyW () {
        return new Wheel();
    }
    
    public boolean isEmptyW() {
        return dcLinkedList.isEmpty();
    }

    public void rightW() {
        if (isEmptyW()) return;
        dcLinkedList.moveRight();
    }

    public void leftW() {
        if (isEmptyW()) return;
        dcLinkedList.moveLeft();
    }

    public Object headW() {
        if (isEmptyW()) return null;
        return dcLinkedList.start.getData();
    }

    public void insertW (Object o) {
        dcLinkedList.insertAtEnd(o);
        dcLinkedList.moveLeft();
        degree = dcLinkedList.getSize();
    }

    public Object extractW () {
        Object headW = headW();
        dcLinkedList.deleteStart();
        degree = dcLinkedList.getSize();
        return headW;
    }

    public int getDegree() {
        return degree;
    }

}
