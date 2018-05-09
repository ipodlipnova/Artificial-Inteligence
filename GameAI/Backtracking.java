import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by Asus on 17.09.2017.
 */
public class Backtracking {
    private static Point pos;
    private static boolean[][] visited = new boolean[Field.SIZE][Field.SIZE];
    private static ArrayList<Point> history;
    private static boolean ignoreObstructions;

    /**
     * This method start new algorithm when we failed in previous
     */
    public static void startAgain() {
        pos = new Point(0, 0);
        history = new ArrayList<>();
        for(int i = 0; i < Field.SIZE; i++)
            for(int j = 0; j < Field.SIZE; j++)
                visited[i][j] = false;
        visited[0][0] = true;
        ignoreObstructions = false;
        history.add(pos.clone());
    }

    private static void moveBack() {
        if(history.size() < 2) {
            ignoreObstructions = true;
            return;
        }
        Point last1 = history.get(history.size() - 1);
        Point last2 = history.get(history.size() - 2);
        history.remove(history.size() - 1);
        if(last2.x < last1.x) {
            Red_Riding_Hood.LeftStep();
            pos.x--;
        } else if(last2.x > last1.x) {
            Red_Riding_Hood.RightStep();
            pos.x++;
        } else if(last2.y < last1.y) {
            Red_Riding_Hood.UpStep();
            pos.y--;
        } else if(last2.y > last1.y) {
            Red_Riding_Hood.DownStep();
            pos.y++;
        }
    }

    /**
     * This method choose the next step of Red Riding Hood
     */
    public static void step() {
        if(ignoreObstructions) {
            Point granny = Red_Riding_Hood.whereGrannyIs();
            if(granny.x > 0) {
                Red_Riding_Hood.RightStep();
                pos.x++;
            } else if(granny.y > 0) {
                Red_Riding_Hood.DownStep();
                pos.y++;
            } else if(granny.x < 0) {
                Red_Riding_Hood.LeftStep();
                pos.x--;
            } else if(granny.y < 0) {
                Red_Riding_Hood.UpStep();
                pos.y--;
            }
            return;
        }
        if(Red_Riding_Hood.isWolfDetected() || Red_Riding_Hood.isBearDetected())
            moveBack();
        else if(pos.x < Field.SIZE - 1 && !visited[pos.x + 1][pos.y]) {
            Red_Riding_Hood.RightStep();
            pos.x++;
            history.add(pos.clone());
        } else if(pos.x > 0 && !visited[pos.x - 1][pos.y]) {
            Red_Riding_Hood.LeftStep();
            pos.x--;
            history.add(pos.clone());
        } else if(pos.y < Field.SIZE - 1 && !visited[pos.x][pos.y + 1]) {
            Red_Riding_Hood.DownStep();
            pos.y++;
            history.add(pos.clone());
        } else if(pos.y > 0 && !visited[pos.x][pos.y - 1]) {
            Red_Riding_Hood.UpStep();
            pos.y--;
            history.add(pos.clone());
        } else {
            moveBack();
        }
        visited[pos.x][pos.y] = true;
    }
};
