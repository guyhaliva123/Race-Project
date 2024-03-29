package utilities;

public class Point {
    private static final int MAX_X = 1000000;
    private static final int MIN_X = 0;
    private static final int MAX_Y = 3000;
    private static final int MIN_Y = 0;

    private double x ;
    private double y ;

    /**
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * @param other
     */
    public Point(Point other){
        if (other!=null)
        {
            this.setX(other.x);
            this.setY(other.y);
        }
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * @param x
     * @return
     */
    public boolean setX(double x) {
        if (x >= 0 && MAX_X - x >= 0) {
            this.x = x;
            return true;
        }else return false;
    }

    /**
     * @param y
     * @return
     */
    public boolean setY(double y){
        if (y >= 0 && MAX_Y - y >= 0) {
            this.y = y;
            return true;
        }else return false;

    }
}
