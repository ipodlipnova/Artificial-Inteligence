/**
 * Created by Asus on 17.09.2017.
 */
public class Field {
    public static final int SIZE = 9;
    private static boolean isDead = false;

    /**
     * This method is used to start a new game
     */
    public static void startNewGame() {
        isDead = false;
        Red_Riding_Hood.CreatePosition();
        Red_Riding_Hood.berries = 6;

        do {
            Wolf.CreatePosition();
            Bear.CreatePosition();
            Wood_Cutter.CreatePosition();
            Granny.CreatePosition();
        } while (Point.inRange(Granny.position, Wolf.range) || Point.inRange(Granny.position, Bear.range) ||
                Point.inRange(Wood_Cutter.position, Wolf.range) || Point.inRange(Wood_Cutter.position, Bear.range) ||
                Point.inRange(Red_Riding_Hood.position, Wolf.range) ||
                Point.inRange(Red_Riding_Hood.position, Bear.range) || Point.isEqual(Wolf.position, Bear.position) ||
                Point.isEqual(Wolf.position, Granny.position) || Point.isEqual(Granny.position, Bear.position) ||
                Point.isEqual(Granny.position, Wood_Cutter.position));
    }

    /**
     * This method is used to print field
     */
    public static void PrintField() {
        char[][] arr = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = '-';
            }
        }
        arr[Wolf.position.x][Wolf.position.y] = 'W';
        arr[Granny.position.x][Granny.position.y] = 'G';
        arr[Bear.position.x][Bear.position.y] = 'B';
        arr[Red_Riding_Hood.position.x][Red_Riding_Hood.position.y] = 'R';
        arr[Wood_Cutter.position.x][Wood_Cutter.position.y] = 'C';
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++)
                System.out.print(arr[j][i]);
            System.out.println();
        }
    }

    /**
     * This method checks if there is Wolf near Red Riding Hood
     */
    public static void checkWolf() {
        if(Point.inRange(Red_Riding_Hood.position, Wolf.range)) isDead = true;
    }

    /**
     * This method checks if there is Bear near Red Riding Hood
     */
    public static void checkBear() {
        if(Point.inRange(Red_Riding_Hood.position, Bear.range) && Red_Riding_Hood.berries > 0) Red_Riding_Hood.berries -= 2;
    }

    /**
     * This method checks if Red Riding Hood is in the Wood Cutter's cell
     */
    public static void checkWoodCutter() {
        if(Point.isEqual(Red_Riding_Hood.position, Wood_Cutter.position)) Red_Riding_Hood.berries = 6;
    }

    /**
     * This method is used to check if the game is finished
     */
    public static boolean gameFinished() {
        return Point.isEqual(Red_Riding_Hood.position, Granny.position) || isDead;
    }

    /**
     * This method is used to check if the game is failed
     */
    public static boolean isFail() {
        return isDead || (gameFinished() && Red_Riding_Hood.berries < 6);
    }
}
