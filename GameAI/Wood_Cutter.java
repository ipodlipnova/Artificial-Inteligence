/**
 * Created by Asus on 07.09.2017.
 */
public class Wood_Cutter {
    static Point position = new Point();
    static Point position1 = new Point();
    static Point position2 = new Point();

    /**
     * This method creates two possible positions of Wood Cutter
     * and choose one of them - position where Wood Cutter now
     */
    public static void CreatePosition(){
        do {
            position1.x = (int) (Math.random()*(Field.SIZE));
            position1.y = (int) (Math.random()*(Field.SIZE));
            position2.x = (int) (Math.random()*(Field.SIZE));
            position2.y = (int) (Math.random()*(Field.SIZE));
        } while ((position1.x == 0 && position1.y == 0) || (position2.x == 0 && position2.y == 0) || (position1.x == position2.x) && (position1.y == position2.y));
        if ((int) (Math.random()*2) == 0) {
            position.x = position1.x;
            position.y = position1.y;
        } else {
            position.x = position2.x;
            position.y = position2.y;
        }
    }
}
