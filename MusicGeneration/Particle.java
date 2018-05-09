
/**
 * This class are used to generate particle.
 */

public class Particle {
    double [] Position;
    double [] V;
    double [] myBest;
    int score;

    public Particle(int n){
        Position = new double[n];
        V = new double[n];
        myBest = new double[n];
        score = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {

        }
        for (int i = 0; i < V.length; i++){
            V[i] = -0.5 + Math.random();
            Position[i] = 48 + 48 * Math.random();
        }
    }
}
