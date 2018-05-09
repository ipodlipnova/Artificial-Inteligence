import java.io.File;
import java.io.IOException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.player.Player;

 /*
  * Main class of the program.
  */

public class Main {

    public static void main(String[] args) throws IOException {
        Generator generator = new Generator();
        Player rp = new Player();
        rp.play(PSO1.pat);
        MidiFileManager.savePatternToMidi(PSO1.pat, new File("IrinaPodlipnova.midi"));
    }
}