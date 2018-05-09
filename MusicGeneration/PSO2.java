import org.jfugue.theory.*;

/**
 * This class are used to generate sequence of notes for melody.
 */
public class PSO2 {
    static double [] globalBest;
    static Note [] ideal = new Note[32];
    static Particle [] flock = new Particle[10000];
    static int globalBestScore = Integer.MAX_VALUE;
    static int generation;

    /**
     * This method are used to measure how close the generated particle to an ideal sequence.
     */

    public static int fitnessFunction(Particle part){
        int sum;
        sum = 0;
        int counter = 0;
        //check if the note is equal to one of notes from the corresponding chord.
        for (int i = 0; i < part.Position.length; i++) {
            Note [] notes = PSO1.chords[i/2].getNotes();
            int minDist1 = Math.min(Math.abs((((int)part.Position[i])%12 - notes[0].getValue()%12)), Math.abs(((int)part.Position[i])%12 - (notes[0].getValue()%12+12)));
            int minDist2 = Math.min(Math.abs((((int)part.Position[i])%12 - notes[1].getValue()%12)), Math.abs(((int)part.Position[i])%12 - (notes[1].getValue()%12+12)));
            int minDist3 = Math.min(Math.abs((((int)part.Position[i])%12 - notes[2].getValue()%12)), Math.abs(((int)part.Position[i])%12 - (notes[2].getValue()%12+12)));
            sum +=Math.min(Math.min(minDist1, minDist2), minDist3);
        }
        //Check that there are no repetitions of the same note.
        counter = 0;
        for (int i = 0; i < part.Position.length - 1; i++) {
            if (Math.abs((int) part.Position[i]) % 12 == Math.abs((int) part.Position[i + 1]) % 12)
                counter++;
            else counter = 0;
            if (counter > 1)
                sum += counter;
        }
        //check if the last note is tonic.
        sum +=Math.min(Math.abs(((int)part.Position[31])%12 - new Note(Generator.tonic).getValue()%12), Math.abs(((int)part.Position[31])%12 - (new Note(Generator.tonic).getValue()%12+12)));
        return sum;
    }

    /**
     * This is the PSO loop.
     */

    public static void pso(int n){
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
                ideal[i] = new Note(Math.abs((int)globalBest[i])%12 + 60);
            System.out.println(ideal[i].toString());
        }
        PSO1.pat.add("V1");
        PSO1.pat.add(ideal);
        System.out.println(generation + " generations");
    }

    /**
     * This method are used to update velocity.
     */

    public static void updateVelocity(Particle part){
        for (int j = 0; j<part.V.length; j++){
            part.V[j] = 0.4*part.V[j] + 0.45*Math.random()*(part.myBest[j] - part.Position[j]) +
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
