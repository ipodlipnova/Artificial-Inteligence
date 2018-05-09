/**
 * Created by Asus on 07.09.2017.
 */
public class Bear {
    static Point position = new Point();
    static Point [] range = new Point[8];

    /**
     * This method creates position of Bear
     */
    public static void CreatePosition(){
        do {
            position.x = (int) (Math.random() * (Field.SIZE));
            position.y = (int) (Math.random() * (Field.SIZE));
        } while (Bear.position.x == 0 && Bear.position.y == 0);

        range[0] = new Point(position.x - 1, position.y);
        range[1] = new Point(position.x - 1, position.y + 1);
        range[2] = new Point(position.x + 1, position.y);
        range[3] = new Point(position.x + 1, position.y + 1);
        range[4] = new Point(position.x + 1, position.y);
        range[5] = new Point(position.x + 1, position.y - 1);
        range[6] = new Point(position.x, position.y - 1);
        range[7] = new Point(position.x - 1, position.y - 1);

    }
}
