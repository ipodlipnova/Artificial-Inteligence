import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

/**
 * This class are used to generate tonality and to start PSO algorithms.
 */

public class Generator {
    static  String tonic = "C";
    static  String scale = "major";
    static Note [] gamma = new Note[7];
    static Chord [] chords = new Chord[3];
    public Generator() {
        if (scale == "major") {
            gamma[0] = new Note(tonic);
            gamma[1] = new Note(gamma[0].getValue() + 2);
            gamma[2] = new Note(gamma[1].getValue() + 2);
            gamma[3] = new Note(gamma[2].getValue() + 1);
            gamma[4] = new Note(gamma[3].getValue() + 2);
            gamma[5] = new Note(gamma[4].getValue() + 2);
            gamma[6] = new Note(gamma[5].getValue() + 2);
            chords[0] = new Chord(gamma[0], Chord.MAJOR_INTERVALS);
            chords[1] = new Chord(gamma[3], Chord.MAJOR_INTERVALS);
            chords[2] = new Chord(gamma[4], Chord.MAJOR_INTERVALS);
        }
        if (scale == "minor") {
            gamma[0] = new Note(tonic);
            gamma[1] = new Note(gamma[0].getValue() + 2);
            gamma[2] = new Note(gamma[1].getValue() + 1);
            gamma[3] = new Note(gamma[2].getValue() + 2);
            gamma[4] = new Note(gamma[3].getValue() + 2);
            gamma[5] = new Note(gamma[4].getValue() + 1);
            gamma[6] = new Note(gamma[5].getValue() + 2);
            chords[0] = new Chord(gamma[0], Chord.MINOR_INTERVALS);
            chords[1] = new Chord(gamma[3], Chord.MINOR_INTERVALS);
            chords[2] = new Chord(gamma[4], Chord.MINOR_INTERVALS);
        }
        long time = System.currentTimeMillis();
        PSO1.pso(16);
        time = System.currentTimeMillis() - time;
        System.out.println(time + " ms");
        time = System.currentTimeMillis();
        PSO2.pso(32);
        time = System.currentTimeMillis() - time;
        System.out.println(time + " ms");
    }
}
