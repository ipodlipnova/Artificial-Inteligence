/**
 * Created by Asus on 07.09.2017.
 */
public class Point {
    int x;
    int y;

    /**
     * Default constructor
     */
    public Point() {
        x = 0;
        y = 0;
    }

    /**
     * Constructor
     */
    public Point(int v1, int v2){
        x = v1;
        y = v2;
    }

    /**
     * This method check if points are equal
     */
    public static boolean isEqual(Point p1, Point p2){
        if (p1.x == p2.x && p1.y == p2.y)
            return true;
        else
            return false;
    }

    /**
     * This method check if point belong to a range
     */
    public static boolean inRange(Point p, Point[] arr) {
        for (int i = 0; i < arr.length; i++){
            if (Point.isEqual(arr[i], p)) {
                return true;
            }
        }
        return false;
    }

    public Point clone() {
        return new Point(x, y);
    }


    public int hashCode() {
        return x * 10 + y;
    }

    public boolean equals(Object o) {
        return o instanceof Point && Point.isEqual((Point) o, this);
    }
}
