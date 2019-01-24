import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

public class AnimationLoader {
	Hashtable<String, ArrayList<AnimationData>> data = new Hashtable<String, ArrayList<AnimationData>>();

	public AnimationLoader(URL url) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String cur_line;
		try {
			while ((cur_line = in.readLine()) != null) {
				String line = cur_line.trim().replaceAll(" +", " ");
				if (line.equals("") || line.contains("#"))
					continue;
				String[] split = line.split(" ");
				if (!data.containsKey(split[0])) {
					data.put(split[0], new ArrayList<AnimationData>());
				}
				float encodedTime = Float.valueOf(split[1]);
				int startTime = (int) Math.floor(encodedTime);
				int endTime = (int) (encodedTime * 100 - startTime * 100);
				float dx = Float.valueOf(split[2]);
				float dy = Float.valueOf(split[3]);
				float angle = Float.valueOf(split[4]);
				data.get(split[0]).add(new AnimationData(startTime, endTime, dx, dy, angle));

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in animation file.");
		}
	}

	public float[] nextFrame(String name, int current_frame) {
		ArrayList<AnimationData> search = data.get(name);
		float[] deltas = { 0.0f, 0.0f, 0.0f };
		for (AnimationData a : search) {
			if (a.start <= current_frame && a.stop > current_frame) {
				deltas[0] = a.dx;
				deltas[1] = a.dy;
				deltas[2] = a.angle;
				return deltas;
			}
		}
		return deltas;
	}

	class AnimationData {
		int start;
		int stop;
		float dx;
		float dy;
		float angle;

		public AnimationData(int start, int stop, float dx, float dy, float angle) {
			this.start = start;
			this.stop = stop;
			this.dx = dx;
			this.dy = dy;
			this.angle = angle;
		}
	}
}
