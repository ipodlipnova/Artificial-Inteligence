import org.jfugue.pattern.Pattern;
import org.jfugue.theory.*;


/**
 * This class are used to generate sequence of chords.
 */

public class PSO1 {
    static double [] globalBest;
    static Chord [] chords;
    static Particle [] flock = new Particle[4000];
    static int globalBestScore = Integer.MAX_VALUE;
    static int generation;
    static Pattern pat = new Pattern("V0");

    /**
     * This method are used to measure how close the generated particle to an ideal sequence.
     */

    public static int fitnessFunction(Particle part){
        int sum;
        int counter;
        sum = 0;
        counter = 0;
        // check if the base note of chord is tonic, subdominant or dominant.
        for (int i = 0; i < part.Position.length; i++) {
            int minDistT = Math.min(Math.abs(((int)part.Position[i])%12 - Generator.gamma[0].getValue()%12), Math.abs(((int)part.Position[i])%12 - (Generator.gamma[0].getValue()%12+12)));
            int minDistS = Math.min(Math.abs(((int)part.Position[i])%12 - Generator.gamma[3].getValue()%12), Math.abs(((int)part.Position[i])%12 - (Generator.gamma[3].getValue()%12+12)));
            int minDistD = Math.min(Math.abs(((int)part.Position[i])%12 - Generator.gamma[4].getValue()%12), Math.abs(((int)part.Position[i])%12 - (Generator.gamma[4].getValue()%12+12)));
            sum +=Math.min(Math.min(minDistT, minDistS), minDistD);
        }
        //check that there are no repetition of the same chords longer than 3.
        for (int i = 0; i < part.Position.length - 1; i++){
            if (Math.abs((int)part.Position[i])%12 == Math.abs((int)part.Position[i+1])%12)
                counter++;
            else counter = 0;
            if (counter > 3)
                sum +=counter;
        }
        //check if the last chord is tonic triad.
        sum +=Math.min(Math.abs(((int)part.Position[15])%12 - new Note(Generator.tonic).getValue()%12), Math.abs(((int)part.Position[15])%12 - (new Note(Generator.tonic).getValue()%12+12)));
        return sum;
    }

    /**
     * This is the PSO loop.
     */

    public static void pso(int n){
        chords = new Chord[n];
        globalBest = new double[n];
        for (int i = 0; i < flock.length; i++) {
            flock[i] = new Particle(n);
        }
        while (globalBestScore != 0) {
            for (int i = 0; i<flock.length; i++) {
                if (fitnessFunction(flock[i]) < flock[i].score){
                    flock[i].score = fitnessFunction(flock[i]);
                    for (int j = 0; j<flock[i].Position.length; j++) {
                        flock[i].myBest[j] = flock[i].Position[j];
                    }
                }
            }
            for (int i = 0; i < flock.length; i++) {
                if (flock[i].score < globalBestScore) {
                    globalBestScore = flock[i].score;
                    for (int j = 0; j < flock[i].Position.length; j++) {
                        globalBest[j] = flock[i].myBest[j];
                    }
                }
                updateVelocity(flock[i]);
                updateParticle(flock[i]);
            }
            generation++;
        }
        for (int i = 0; i<n; i++){
            if (Generator.scale == "major")
                chords[i] = new Chord(new Chord(new Note(Math.abs((int)globalBest[i])%12 + 48),Chord.MAJOR_INTERVALS).toString()+"h");
            else chords[i] = new Chord(new Chord(new Note(Math.abs((int)globalBest[i])%12 + 48),Chord.MINOR_INTERVALS).toString()+"h");
            System.out.println(chords[i].toString());
        }

        pat.add(chords).setTempo(120);
        System.out.println(generation + " generations");
    }

    /**
     * This method are used to update velocity.
     */

    public static void updateVelocity(Particle part){
            for (int j = 0; j<part.V.length; j++){
                part.V[j] = 0.45 * part.V[j] + 0.45*Math.random()*(part.myBest[j] - part.Position[j]) +
                        0.9*Math.random()*(globalBest[j] - part.Position[j]);
            }
        }

    /**
     * This method are used to update particle.
     */

    public static void updateParticle(Particle part){
            for (int j = 0; j<part.V.length; j++){
                part.Position[j] += part.V[j];
            }
    }
}
