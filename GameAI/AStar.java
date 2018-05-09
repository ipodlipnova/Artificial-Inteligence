import java.util.*;

/**
 * Created by Asus on 17.09.2017.
 */
public class AStar {
    private static Point pos, prev;
    private static boolean [][] barrier = new boolean[Field.SIZE][Field.SIZE];
    private static boolean cantFindPath;

    /**
     * This method start new algorithm when we failed in previous
     */
    public static void startAgain() {
        cantFindPath = false;
        pos = new Point(0, 0);
        for(int i = 0; i < Field.SIZE; i++)
            for(int j = 0; j < Field.SIZE; j++) {
                barrier[i][j] = false;
            }
    }

    /**
     * A* Algorithm realization
     */
    private static ArrayList<Point> aStar() {
        HashSet<Point> closedSet = new HashSet<>();
        HashSet<Point> openSet = new HashSet<>();
        openSet.add(pos.clone());
        HashMap<Point, Point> previous = new HashMap<>();
        int[][] gScore = new int[Field.SIZE][Field.SIZE];
        int[][] fScore = new int[Field.SIZE][Field.SIZE];
        for(int i = 0; i < Field.SIZE;i++) {
            for(int j = 0; j < Field.SIZE;j++) {
                gScore[i][j] = Integer.MAX_VALUE;
                fScore[i][j] = Integer.MAX_VALUE;
            }
        }
        while (!openSet.isEmpty()) {
            Point p = null; int scoremin = Integer.MAX_VALUE;
            for(Point p2 : openSet) {
                if(fScore[p2.x][p2.y] < scoremin || p == null) {
                    p = p2;
                    scoremin = fScore[p2.x][p2.y];
                }
            }
            if(Point.isEqual(Red_Riding_Hood.whereGrannyIs(),
                    new Point(p.x - pos.x, p.y - pos.y)))
                return reconsructPath(p, previous);
            openSet.remove(p);
            closedSet.add(p);

            Point[] neighbors = new Point[] { new Point(p.x + 1, p.y), new Point(p.x - 1, p.y),
                new Point(p.x, p.y + 1), new Point(p.x, p.y - 1)};
            for(Point p2 : neighbors) {
                if(p2.x < 0 || p2.x >= Field.SIZE || p2.y < 0 || p2.y >= Field.SIZE || barrier[p2.x][p2.y]
                    || closedSet.contains(p2))
                    continue;
                openSet.add(p2);
                int temp_gScore = gScore[p.x][p.y] + 1;
                if (temp_gScore >= gScore[p2.x][p2.y])
                    continue;
                previous.put(p2, p);
                gScore[p2.x][p2.y] = temp_gScore;
                fScore[p2.x][p2.y] = temp_gScore + heuristicCost(p2);
            }
        }
        return null;
    }

    /**
     *
     * Method of counting heuristic
     */
    private static int heuristicCost(Point p) {
        Point gran = Red_Riding_Hood.whereGrannyIs();
        gran = new Point(pos.x + gran.x, pos.y + gran.y);
        return Math.abs(p.x - gran.x) + Math.abs(p.y - gran.y);
    }

    private static ArrayList<Point> reconsructPath(Point p, HashMap<Point, Point> previous) {
        ArrayList<Point> arr = new ArrayList<>();
        arr.add(0, p);
        while (previous.containsKey(arr.get(0))) {
            arr.add(0, previous.get(arr.get(0)));
        }
        return arr;
    }

    private static void goBack() {
        if(prev.x < pos.x) {
            Red_Riding_Hood.LeftStep();
            pos.x--;
        } else if(prev.x > pos.x) {
            Red_Riding_Hood.RightStep();
            pos.x++;
        } else if(prev.y < pos.y) {
            Red_Riding_Hood.UpStep();
            pos.y--;
        } else if(prev.y > pos.y) {
            Red_Riding_Hood.DownStep();
            pos.y++;
        }
    }

    /**
     * This method choose the next step of Red Riding Hood
     */
    public static void step() {
        if(Red_Riding_Hood.isWolfDetected() || Red_Riding_Hood.isBearDetected()) {
            barrier[pos.x][pos.y] = true;
            if(prev != null && !barrier[prev.x][prev.y] && !cantFindPath) {
                goBack();
                return;
            }
        }
        ArrayList<Point> path = aStar();
        if(path == null) {
            cantFindPath = true;
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
        Point p = path.get(1);
        prev = pos.clone();
        if(p.x < pos.x) {
            Red_Riding_Hood.LeftStep();
            pos.x--;
        } else if(p.x > pos.x) {
            Red_Riding_Hood.RightStep();
            pos.x++;
        } else if(p.y < pos.y) {
            Red_Riding_Hood.UpStep();
            pos.y--;
        } else if(p.y > pos.y) {
            Red_Riding_Hood.DownStep();
            pos.y++;
        }
    }
}
