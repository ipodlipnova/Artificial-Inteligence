/**
 * Created by Asus on 09.09.2017.
 */
public class Range {

    /**
     * This method is used to check if ranges are overlaps
     */
    public static boolean areOverlap(Point[] arr1, Point[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (Point.isEqual(arr1[i], arr2[j]))
                    return true;
            }
        }
        return false;
    }
}
