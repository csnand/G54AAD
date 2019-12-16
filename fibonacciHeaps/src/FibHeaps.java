public class FibHeaps {
    private Wheel wheel;
    private int degree;

    public FibHeaps () {
        this(null, 0);
    }

    public FibHeaps (Wheel w, int degree) {
        wheel = w;
        this.degree = degree;
    }

    public FibHeaps emptyH () {
        return new FibHeaps();
    }

    public Boolean isEmptyH () {
        return wheel == null || wheel.isEmptyW();
    }

    public void insertH (Object object) {
        if (wheel == null || minimumH() == null) {
            wheel = new Wheel();
            wheel.insertW(object);
            degree = wheel.getDegree();
            return;
        }

        Wheel w = new Wheel();
        w.insertW(object);
        wheel.insertW(w);
        if ((Double) object >= (Double) minimumH()) {
            wheel.rightW();
        }
        degree = wheel.getDegree();
    }

    public Object minimumH () {
        if (isEmptyH()) return null;
        if (wheel.headW() instanceof Wheel){
            return ((Wheel) wheel.headW()).headW();
        }
        return wheel.headW();
    }

    public Integer extractH () {
        return  null;
    }

    private void consolidate(){

    }


}
