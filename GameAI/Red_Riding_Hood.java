/**
 * Created by Asus on 07.09.2017.
 */
public class Red_Riding_Hood {
    public static Point position = new Point();
    static Point [] BearRange = new Point[4];
    static Point [] WolfRange = new Point[8];
    static int berries;

    /**
     * This method creates position of Red Riding Hood
     */
    public static void CreatePosition(){
        position.x = 0;
        position.y = 0;
        BearRange[0] = new Point(position.x - 1, position.y);
        BearRange[1] = new Point(position.x, position.y + 1);
        BearRange[2] = new Point(position.x + 1, position.y);
        BearRange[3] = new Point(position.x, position.y - 1);
        WolfRange[0] = new Point(position.x - 2, position.y);
        WolfRange[1] = new Point(position.x - 1, position.y);
        WolfRange[2] = new Point(position.x, position.y + 2);
        WolfRange[3] = new Point(position.x, position.y + 1);
        WolfRange[4] = new Point(position.x + 2, position.y);
        WolfRange[5] = new Point(position.x + 1, position.y);
        WolfRange[6] = new Point(position.x - 2, position.y);
        WolfRange[7] = new Point(position.x - 1, position.y);
    }

    /**
     * This method is used to make a left step
     */
    public static void LeftStep() {
        if(position.x <= 0) return;
        position.x -= 1;
        for (int i = 0; i < BearRange.length; i++)
                BearRange[i].x -= 1;
        for (int i = 0; i < WolfRange.length; i++)
            WolfRange[i].x -= 1;
    }

    /**
     * This method is used to make a right step
     */
    public static void RightStep() {
        if(position.x >= Field.SIZE - 1) return;
        position.x += 1;
        for (int i = 0; i < BearRange.length; i++)
            BearRange[i].x += 1;
        for (int i = 0; i < WolfRange.length; i++)
            WolfRange[i].x += 1;
    }

    /**
     * This method is used to make an up step
     */
    public static void UpStep() {
        if(position.y <= 0) return;
        position.y -= 1;
        for (int i = 0; i < BearRange.length; i++)
            BearRange[i].y -= 1;
        for (int i = 0; i < WolfRange.length; i++)
            WolfRange[i].y -= 1;
    }

    /**
     * This method is used to make a down step
     */
    public static void DownStep() {
        if(position.y >= Field.SIZE - 1) return;
        position.y += 1;
        for (int i = 0; i < BearRange.length; i++)
            BearRange[i].y += 1;
        for (int i = 0; i < WolfRange.length; i++)
            WolfRange[i].y += 1;
    }

    /**
     * This method check where Granny is
     */
    public static Point whereGrannyIs() {
        return new Point(Granny.position.x - position.x, Granny.position.y - position.y);
    }

    /**
     * This method check if Red Riding Hood range and Wolf range are overlap
     */
    public static boolean isWolfDetected() {
        return (Range.areOverlap(WolfRange, Wolf.range));
    }

    /**
     * This method check if Red Riding Hood range and Bear range are overlap
     */
    public static boolean isBearDetected() {
        return (Range.areOverlap(BearRange, Bear.range));
    }
}
