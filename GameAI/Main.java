import java.lang.*;

public class Main {

    /**
     *
     * @param args
     * This is the main method of the program
     */
    public static void main(String[] args){

        int fails = 0;
        int steps = 0;
        long time = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            Field.startNewGame();
            Backtracking.startAgain();
            while (!Field.gameFinished()) {
                Backtracking.step();
                steps++;
                Field.checkWolf();
                Field.checkBear();
                Field.checkWoodCutter();
            }
            if(Field.isFail())
                fails++;
        }
        time = System.currentTimeMillis() - time;
        System.out.format("Backtracking:\n    Fails: %d\n    Steps: %f\n    Time: %f\n\n", fails, (double)steps/100, (double)time);
        fails = 0;
        steps = 0;
        time = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            Field.startNewGame();
            AStar.startAgain();
            while (!Field.gameFinished()) {
                AStar.step();
                steps++;
                Field.checkWolf();
                Field.checkBear();
                Field.checkWoodCutter();
            }
            if(Field.isFail())
                fails++;
        }
        time = System.currentTimeMillis() - time;
        System.out.format("A*:\n    Fails: %d\n    Steps: %f\n    Time: %f\n", fails, (double)steps/100, (double)time);
    }

}
