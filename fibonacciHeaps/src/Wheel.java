public class Wheel {
    private DCLinkedList dcLinkedList ;

    public Wheel () {
        dcLinkedList = new DCLinkedList();
    }

    public Wheel emptyW () {
        return new Wheel();
    }
    
    public boolean isEmptyW() {
        return dcLinkedList.isEmpty();
    }

    public void rightW() {

    }

    public void leftW() {

    }

    public Object headW() {
        if (isEmptyW()) return null;
        return dcLinkedList.start.getData();
    }

    public void insertW (Object o) {

    }

    public Object extractW () {
        return null;
    }

}
