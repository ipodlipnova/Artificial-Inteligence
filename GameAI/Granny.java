/**
 * Created by Asus on 07.09.2017.
 */
public class Granny {
    static Point position = new Point();

    /**
     * This method creates position of Granny
     */
    public static void CreatePosition(){
        do {
            position.x =(int) (Math.random()*(Field.SIZE));
            position.y =(int) (Math.random()*(Field.SIZE));
        } while (Granny.position.x == 0 && Granny.position.y == 0);
    }
}
