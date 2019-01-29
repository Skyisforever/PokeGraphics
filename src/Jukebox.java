import java.io.File;
import javax.sound.sampled.*;

public class Jukebox {
	public static void playMusic(String file) {

		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(file));
			Clip test = AudioSystem.getClip();

			test.open(ais);
			test.start();

			while (test.isRunning())
				Thread.sleep(10);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}