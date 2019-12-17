public class FibHeaps {
    private Wheel<Integer> wheel;

    public FibHeaps(){
        wheel = null;
    }
    public FibHeaps emptyH() {
        return new FibHeaps();
    }

    public boolean isEmptyH() {	return wheel == null || wheel.isEmptyW(); }

    public void insertH(int value) {
        if(isEmptyH()) {
            if (wheel == null) wheel = new Wheel<>();
            wheel.insertW(value);
            return;
        }
        wheel.insertW(value);
        if( wheel.headW() < value)
            wheel.moveRight();
    }

    public int minimumH() { return isEmptyH() ? 0 : wheel.headW(); }

    public int extractH() {
        if(isEmptyH()) return 0;

        int min = wheel.headW();
        while (wheel.getStart().child != null) {
            Node<Integer> child = wheel.getStart().child;
            wheel.getStart().child.delete();
            if(wheel.getStart().child == wheel.getStart().child.right) wheel.getStart().child = null;
            else wheel.getStart().child = wheel.getStart().child.right;
            wheel.getStart().insert(child);
            child.parent = null;
        }

        wheel.deleteStart();

        if (wheel.getStart() == wheel.getEnd()) {
            wheel = null;
            return min;
        }

        consolidate();
        return min;
    }

    public void consolidate() {
        Node<Integer> NArray[] = new Node[ (int)(Math.log(wheel.getCount()) / Math.log(2)) + 1];
        while (wheel != null) {

            Node<Integer> x = wheel.getStart();
//            wheel.deleteStart();
            wheel.getStart().delete();

            int d = x.degree;
            for (; NArray[d] != null; d++) {
                if(NArray[d].getData() < x.getData()) {
                    Node<Integer> temp = x;
                    x = NArray[d];
                    NArray[d] = temp;
                }
                x.link(NArray[d]);
                NArray[d] = null;
            }
            NArray[d] = x;
        }

        for(Node<Integer> n : NArray) {
            if (n == null) continue;
            if(isEmptyH()) {
                wheel = new Wheel<>();
                wheel.insertW(n.getData());
                continue;
            }
            wheel.insertW(n.getData());
            if(n.getData() < wheel.headW())
                wheel.moveRight();
        }
    }

}
